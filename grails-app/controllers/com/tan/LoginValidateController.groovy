package com.tan

class LoginValidateController {
    def sessionRegistry
    def springSecurityService

    def userLogedinOrNot={
        println("in loginvalidate controller");

        //def agency=TblAgency.findByAgencyCode(params.agencyCode)
        //def user =User.findByLoginNameAndAgency(params?.loginname)
        def user =User.findByLoginName(params?.loginname)
        ArrayList  logedinUserId=  sessionRegistry.getAllPrincipals().collect { it.id }

        if( logedinUserId.contains(user?.id)){
            render "true"
        }
        else{
            render "false"
        }

        return ;
    }
    def userLoginOrNot={

        if(springSecurityService.isLoggedIn())
        {
            render 'true';
            return ;
        }
        else{
            render  'false';
            return ;
        }



    }

}
