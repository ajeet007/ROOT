package com.tan.com.tan.master
import com.tan.User
import grails.plugins.springsecurity.Secured

class ChangePasswordController {

    def springSecurityService

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def index = {

    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def changePassword={

        if(springSecurityService.encodePassword(params.oldpassword)==session?.user?.password)  {
            if((params.password!=''|| params.password2!='')&&(params.password==params.password2))
            {
                def user = User.get(springSecurityService.principal.id)
                user.password=springSecurityService.encodePassword(params.password)
                user.save(flush:true)
                flash.message=message(code: 'spring.security.ui.resetPassword.success')
                redirect(action: "index")
            }
            else
            {

                flash.message=message(code: 'spring.security.ui.resetPassword.equal')
                redirect(action: "index")
            }
        }
        else{
            flash.message=message(code: 'spring.security.ui.wrongOldPassword')
            redirect(action: "index")
        }

    }

}
