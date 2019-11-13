

/**
 * Created with IntelliJ IDEA.
 * User: Ajeet Singh
 * Date: 24/6/14
 * Time: 6:12 PM
 * Description: to assign role and set url.
 */


import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.PrivateKey;

public class MyAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    protected String determineTargetUrl(HttpServletRequest request,
                                        HttpServletResponse response) {

        boolean hasuperAdmin=SpringSecurityUtils.ifAllGranted("ROLE_SUPERADMIN");
        boolean hasEditor=SpringSecurityUtils.ifAllGranted("ROLE_EDITOR");



  if(hasuperAdmin){
            return adminUrl;
        }
        else if (hasEditor){
            return editorUrl;
        }
        else{
            return super.determineTargetUrl(request, response);
        }
    }

    private String adminUrl;
    private  String editorUrl;



    public void setAdminUrl(String adminUrl){
        this.adminUrl = adminUrl;
    }
    public void setEditorUrl(String editorUrl){
        this.editorUrl = editorUrl;
    }
}
