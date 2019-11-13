
import org.codehaus.groovy.grails.plugins.springsecurity.SecurityFilterPosition
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import com.tan.User
import com.tan.UserRole
import com.tan.Role


class BootStrap {
    def authenticationProcessingFilter
    def concurrentSessionControlStrategy
    def springSecurityService
    def init = { servletContext ->

        /*********************************************/
         /*def adminUser = User.findByUsername('admin') ?: new User(
                             username: 'admin',
                             password: springSecurityService.encodePassword('root'),
                             enabled: true
                             ).save(failOnError: true)

                     if (!adminUser.authorities.contains('ROLE_SUPERADMIN')) {
                         UserRole.create adminUser, Role.findByAuthority('ROLE_SUPERADMIN')
                     }*/


          /******************************/
        SpringSecurityUtils.clientRegisterFilter('concurrencyFilter', SecurityFilterPosition.CONCURRENT_SESSION_FILTER)
        authenticationProcessingFilter.sessionAuthenticationStrategy = concurrentSessionControlStrategy



    }
    def destroy = {

    }

}

