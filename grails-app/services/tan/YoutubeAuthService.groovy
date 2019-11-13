package tan

import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method

import static groovyx.net.http.ContentType.URLENC

class YoutubeAuthService {

    def grailsApplication


    String createAuthorizeRequest() {
        String clientId = grailsApplication.config.youtube.clientId;
        String authUrl = grailsApplication.config.youtube.url.auth;
        String redirectUrl = grailsApplication.config.youtube.redirectUrl;
        String scope = grailsApplication.config.youtube.scope;
        String responseType = grailsApplication.config.youtube.responseType;
        String accessType = grailsApplication.config.youtube.accessType;

        String authRequest = authUrl + "?client_id=" + clientId + "&redirect_uri=" + redirectUrl + "&scope=" + scope + "&response_type=" + responseType +  "&access_type=" + accessType
        return authRequest;
    }

    def getAccessToken(String code){
        String clientId = grailsApplication.config.youtube.clientId;
        String clientSecret = grailsApplication.config.youtube.clientSecret;
        String tokenRequestUrl = grailsApplication.config.youtube.url.token;
        String redirectUrl = grailsApplication.config.youtube.redirectUrl;
        String grantType = "authorization_code";
        Map data = ['client_id':clientId,'client_secret':clientSecret,'code':code ,'redirect_uri':redirectUrl,'grant_type':grantType]
        HTTPBuilder httpBuilder = new HTTPBuilder(tokenRequestUrl);

        httpBuilder.request(Method.POST){
            requestContentType = URLENC
            body = data;
            response.success = {resp, reader ->
                return reader
            }
            response.failure = {response, reader ->
                return null
            }
        }
    }

}