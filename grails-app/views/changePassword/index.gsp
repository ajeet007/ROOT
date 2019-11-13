<%--
  Created by IntelliJ IDEA.
  User: administrator
  Date: 26/6/14
  Time: 11:05 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<% response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
%>
<html>
<head><title>Change Password</title>
    <meta name='layout' content='main'/>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'changepassword.js')}"></script>
    <style type="text/css">
    .PopupPanel
    {
        border: solid 1px black;
        position: absolute;
        left: 50%;
        top: 50%;

        z-index: 100;

        height: 220px;
        margin-top: -200px;

        width: 600px;
        margin-left: -300px;

    }
        /* align="center"  style="margin-top: 50px;margin-right:25%;border:2px;border-style: solid;border-color:#000000;width: 800px" */
    </style>
</head>

<body>
<div id="div1" style="padding: 0px; margin: 0px; height: 500px; font-family: verdana; font-size: 12px; color: #FFFFFF; text-decoration: none; background-color: #5776AC">
%{-- <table style="border:0; cellpadding:0; cellspacing:0;" width="100%">
  <tr>
    <td bgcolor="#3E567D" >
    <div style="float: left; padding-left: 10px; width: 60px; height: 20px; border-left-style: solid; border-left-width: 1px; border-left-color: #C0C0C0;"><g:link controller="home" action="composead" params="[newBooking:'true']">Home</g:link></div>
     <div style="float: left; padding-left: 10px; width: 70px; height: 20px; border-left-style: solid; border-left-width: 1px; border-left-color: #C0C0C0;"><g:link controller="logout" >Logout</g:link></div>
 </tr>
 </table>--}%

    <g:if test="${flash.message}">
        <div class="message" align="center"><h4>${flash.message}</h4></div>
    </g:if>
    <div class="PopupPanel">
        <g:form controller="changePassword" action="changePassword" id="changePassword">
            <div align="center"><h3><g:message code='spring.security.ui.resetPassword.description'/></h3></div>
            <div id="validate" align="center"></div>
            <table align="center" style="height:100px;width:430px;">
                <tr>
                    <td style="font-size:15px"><g:message code='changePassword.currentPassword'
                                                          default="Current Password"/> &nbsp;</td>
                    <td><g:passwordField name="oldpassword" id='oldpassword'/></td>
                </tr>
                <tr>
                    <td style="font-size:15px"><g:message code='changePassword.newPassword'
                                                          default="New Password"/> &nbsp;</td>
                    <td><g:passwordField name="password" id='password'/></td>
                </tr>
                <tr>
                    <td style="font-size:15px"><g:message code='changePassword.confirmpassword'
                                                          default="Confirm New Password"/> &nbsp;</td>
                    <td><g:passwordField name="password2" id='password2'/></td>
                </tr>

            </table>

            <div align="center" style="margin-top:15px" ><input type="submit" id="changePassword" value="ChangePassword" class="X"  onsubmit="validateform()"/></div>

        </g:form>
    </div>
</div>

</body>
</html>


