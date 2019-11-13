/*
Developer Name  : Ajeet Singh
Date            : 16-July-2014
Reason          : 1. Modified to add user with same names for different agencies.
                  2. Modified to assign multiple roles to a single user.
*/
package com.tan.com.tan.master

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.ui.AbstractS2UiController
import groovy.text.SimpleTemplateEngine
import org.codehaus.groovy.grails.plugins.springsecurity.NullSaltSource
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.codehaus.groovy.grails.plugins.springsecurity.ui.RegistrationCode
import com.tan.User
import com.tan.Role
import com.tan.UserRole

class RegisterController extends AbstractS2UiController {

    // override default value from base class
    static defaultAction = 'index'

    // override default value from base class
    static allowedMethods = [register: 'POST']

    def mailService
    /*def messageSource*/
    def saltSource
    def mySpecialDatasource

    @Secured(['ROLE_SUPERADMIN'])

    def index = {

        def tbl_UserInstance = new User()
        def logInstance=User.findAll();
        [tbl_UserInstance:tbl_UserInstance,logInstance:logInstance]
    }


    def register = { RegisterCommand command ->
        try {
            StringTokenizer userRole = new StringTokenizer(params?.user_roles, ",");
            ArrayList<Long> arrayElement = new ArrayList<Long>();
            while (userRole.hasMoreTokens()) {
                def role = Role.findById(Integer.parseInt(userRole.nextToken()));
                arrayElement.add(role?.id);
            }
            ArrayList<Long> role1 = new ArrayList<Long>();
            role1.add(1);
            role1.add(2);
            ArrayList<Long> role2 = new ArrayList<Long>();
            role2.add(1);
            role2.add(3);
            ArrayList<Long> role3 = new ArrayList<Long>();
            role3.add(2);
            role3.add(3);
            ArrayList<Long> role4 = new ArrayList<Long>();
            role4.add(1);
            role4.add(2);
            role4.add(3);
            def subRole;
            if (arrayElement.sort().equals(role1.sort())) {
                subRole = Role.findById(6);
            } else if (arrayElement.sort().equals(role2.sort())) {
                subRole = Role.findById(7);
            } else if (arrayElement.sort().equals(role3.sort())) {
                subRole = Role.findById(8);
            } else if (arrayElement.sort().equals(role4.sort())) {
                subRole = Role.findById(5);
            } else {
                subRole = Role.findById(arrayElement[0]);
            }

            StringTokenizer roles = new StringTokenizer(params?.user_roles, ",");

            if (params.loginId == 'undefined') {

                def bookingUser = User.findByUsername(command?.username)
                if (!bookingUser) {
                    bookingUser = new User(
                            username: params?.loginName,
                            password: springSecurityService.encodePassword(command?.password),
                            loginName: command?.username,
                            enabled: true,
                            role: subRole
                    ).save(failOnError: true)

                    while (roles.hasMoreTokens()) {
                        UserRole.create bookingUser, Role.findById(Integer.parseInt(roles.nextToken()))
                    }
                }
            } else if (params.loginId != 'undefined') {

                try {
                    def bookingUser = User.findById(params.loginId)

                    bookingUser.username = params?.loginName;
                    bookingUser.loginName = command?.username;
                    bookingUser.role = Role.findById(subRole.id)
                    if ((params.password != '' && params.cpassword != "") && (params.password == params.cpassword)) {
                        bookingUser.password = springSecurityService.encodePassword(command?.password)
                    }
                    UserRole.removeAll(bookingUser);
                    bookingUser.save()
                    while (roles.hasMoreTokens()) {
                        UserRole.create bookingUser, Role.findById(Integer.parseInt(roles.nextToken()));

                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if(session.role?.authority == "ROLE_MANAGER" || session.role?.authority == "ROLE_MQCCW" || session.role?.authority == "ROLE_MCW" || session.role?.authority == "ROLE_MQC") {
                render User.findAllByRoleInList([Role.findById(1), Role.findById(2), Role.findById(6)], [sort: "username"]) as JSON
            } else if (session.role?.authority == "ROLE_SUPERADMIN") {
                render User.findAllByRoleInList([Role.findById(2)],[sort: "username"]) as JSON
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    def verifyRegistration = {

        def conf = SpringSecurityUtils.securityConfig
        String defaultTargetUrl = conf.successHandler.defaultTargetUrl

        String token = params.t

        def registrationCode = token ? RegistrationCode.findByToken(token) : null
        if (!registrationCode) {
            flash.error = message(code: 'spring.security.ui.register.badCode')
            redirect uri: defaultTargetUrl
            return
        }

        def user
        RegistrationCode.withTransaction { status ->
            user = lookupUserClass().findByUsername(registrationCode.username)
            if (!user) {
                return
            }
            user.accountLocked = false
            user.save(flush:true)
            def UserRole = lookupUserRoleClass()
            def Role = lookupRoleClass()
            for (roleName in conf.ui.register.defaultRoleNames) {
                UserRole.create user, Role.findByAuthority(roleName)
            }
            registrationCode.delete()
        }

        if (!user) {
            flash.error = message(code: 'spring.security.ui.register.badCode')
            redirect uri: defaultTargetUrl
            return
        }

        springSecurityService.reauthenticate user.username

        flash.message = message(code: 'spring.security.ui.register.complete')
        redirect uri: conf.ui.register.postRegisterUrl ?: defaultTargetUrl
    }

    def forgotPassword = {

        if (!request.post) {
            // show the form
            return
        }

        String username = params.loginName
        if (!username) {
            flash.error = message(code: 'spring.security.ui.forgotPassword.username.missing')
            redirect action: 'forgotPassword'
            return
        }

        String usernameFieldName = SpringSecurityUtils.securityConfig.userLookup.usernamePropertyName
        def user = lookupUserClass().findWhere((usernameFieldName): username)
        if (!user) {
            flash.error = message(code: 'spring.security.ui.forgotPassword.user.notFound')
            redirect action: 'forgotPassword'
            return
        }

        def registrationCode = new RegistrationCode(username: user."$usernameFieldName")
        registrationCode.save(flush: true)

        String url = generateLink('resetPassword', [t: registrationCode.token])

        def conf = SpringSecurityUtils.securityConfig
        def body = conf.ui.forgotPassword.emailBody
        if (body.contains('$')) {
            body = evaluate(body, [user: user, url: url])
        }
        mailService.sendMail {
            to user.username
            from conf.ui.forgotPassword.emailFrom
            subject conf.ui.forgotPassword.emailSubject
            html body.toString()
        }

        [emailSent: true]
    }

    def resetPassword = { ResetPasswordCommand command ->



        String token = params.t

        def registrationCode = token ? RegistrationCode.findByToken(token) : null
        if (!registrationCode) {
            flash.error = message(code: 'spring.security.ui.resetPassword.badCode')
            redirect uri: SpringSecurityUtils.securityConfig.successHandler.defaultTargetUrl
            return
        }

        if (!request.post) {
            return [token: token, command: new ResetPasswordCommand()]
        }

        command.username = registrationCode.username
        command.validate()

        if (command.hasErrors()) {
            return [token: token, command: command]
        }

        String salt = saltSource instanceof NullSaltSource ? null : registrationCode.username
        RegistrationCode.withTransaction { status ->
            def user = lookupUserClass().findByUsername(registrationCode.username)
            user.password = springSecurityUiService.encodePassword(command.password, salt)
            user.save()
            registrationCode.delete()
        }

        springSecurityService.reauthenticate registrationCode.username

        flash.message = message(code: 'spring.security.ui.resetPassword.success')

        def conf = SpringSecurityUtils.securityConfig
        String postResetUrl = conf.ui.register.postResetUrl ?: conf.successHandler.defaultTargetUrl
        redirect uri: postResetUrl

    }

    protected String generateLink(String action, linkParams) {
        createLink(base: "$request.scheme://$request.serverName:$request.serverPort$request.contextPath",
                controller: 'register', action: action,
                params: linkParams)
    }

    protected String evaluate(s, binding) {
        new SimpleTemplateEngine().createTemplate(s).make(binding)
    }

    static final passwordValidator = { String password, command ->
        if (command.username && command.username.equals(password)) {
            return 'command.password.error.username'
        }

        if (!checkPasswordMinLength(password, command) ||
                !checkPasswordMaxLength(password, command) ||
                !checkPasswordRegex(password, command)) {
            return 'command.password.error.strength'
        }
    }

    static boolean checkPasswordMinLength(String password, command) {
        def conf = SpringSecurityUtils.securityConfig

        int minLength = conf.ui.password.minLength instanceof Number ? conf.ui.password.minLength : 8

        password && password.length() >= minLength
    }

    static boolean checkPasswordMaxLength(String password, command) {
        def conf = SpringSecurityUtils.securityConfig

        int maxLength = conf.ui.password.maxLength instanceof Number ? conf.ui.password.maxLength : 64

        password && password.length() <= maxLength
    }

    static boolean checkPasswordRegex(String password, command) {
        def conf = SpringSecurityUtils.securityConfig

        String passValidationRegex = conf.ui.password.validationRegex ?:
            '^.*(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&]).*$'

        password && password.matches(passValidationRegex)
    }

    static final password2Validator = { value, command ->
        if (command.password != command.password2) {
            return 'command.password2.error.mismatch'
        }
    }


}
class RegisterCommand {
    String username
    String password
    String password2
    String role

    def grailsApplication

    static constraints = {
        username blank: false, nullable: false, validator: { value, command ->
            if (value) {
                def User = command.grailsApplication.getDomainClass(
                        SpringSecurityUtils.securityConfig.userLookup.userDomainClassName).clazz
                if (User.findByUsername(value)) {
                    return 'registerCommand.username.unique'
                }
            }
        }
        role blank: false,nullable: false
        password blank: false, nullable: false, validator: RegisterController.passwordValidator
        password2 validator: RegisterController.password2Validator
    }
}

class ResetPasswordCommand {
    String username
    String password
    String password2

    static constraints = {
        username nullable: false
        password blank: false, nullable: false, validator: RegisterController.passwordValidator
        password2 validator: RegisterController.password2Validator
    }
}
