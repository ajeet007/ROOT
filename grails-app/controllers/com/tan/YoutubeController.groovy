package com.tan

import tan.YoutubeAuthService

class YoutubeController {

    YoutubeAuthService youtubeAuthService


    def auth={
        response.sendRedirect(youtubeAuthService.createAuthorizeRequest());
    }

    def callBack(String code){
        // User did not rejected authorization of your project
        if(!code)
            redirect(action: 'Error Action')
        def object = youtubeAuthService.getAccessToken(code);

        if (object?.refresh_token !=null){
        TblYoutube.deleteRecord();
        new TblYoutube(
                expires_in: object?.expires_in,
                refresh_token: object?.refresh_token,
                access_token: object?.access_token,
                token_type: object?.token_type
        ).save(failOnError: true);
        }
        else{
            TblYoutube.updateAccessToken(object?.access_token);
        }


         println("::>>>>"+object);
        // Save your Youtube Tokens to DB
        // or hit data api to get User info using access token and then save

        // chain the request to your success handler.
        chain(controller: 'addChannel', action: 'channel');
    }
}
