<% response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
%>
<html>
<head>
    <meta name='layout' content='main'/>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'loginValidate.js')}"></script>
    <title><g:message code="springSecurity.login.title"/></title>
    <link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
    <g:urlMappings />
    <style type='text/css' media='screen'>

    #login {
        margin: 15px 0px;
        padding: 0px;
        text-align: center;
    }

    #login .inner {
        width: 340px;
        padding-bottom: 6px;
        margin: 60px auto;
        text-align: left;
        border: 1px solid #aab;
        background-color: #5776AC;
        -moz-box-shadow: 2px 2px 2px #eee;
        -webkit-box-shadow: 2px 2px 2px #eee;
        -khtml-box-shadow: 2px 2px 2px #eee;
        box-shadow: 2px 2px 2px #eee;
    }

    #login .inner .fheader {
        padding: 18px 26px 14px 26px;
        background-color: #5776AC;
        margin: 0px 0 14px 0;
        color: #2e3741;
        font-size: 18px;
        font-weight: bold;
    }

    #login .inner .cssform p {
        clear: left;
        margin: 0;
        padding: 4px 0 3px 0;
        padding-left: 105px;
        margin-bottom: 20px;
        height: 1%;
    }

    #login .inner .cssform input[type='text'] {
        width: 120px;
    }

    #login .inner .cssform label {
        font-weight: bold;
        float: left;
        text-align: right;
        margin-left: -105px;
        width: 110px;
        padding-top: 3px;
        padding-right: 10px;
    }

    #login #remember_me_holder {
        padding-left: 120px;
    }

    #login #submit {
        margin-left: 15px;
    }

    #login #remember_me_holder label {
        float: none;
        margin-left: 0;
        text-align: left;
        width: 200px
    }

    #login .inner .login_message {
        padding: 6px 25px 20px 25px;
        color: #c33;
    }

    #login .inner .text_ {
        width: 120px;
    }

    #login .inner .chk {
        height: 12px;
    }
    </style>
</head>

<body >

<tbody>
<tr>
    <td valign="middle" align="center" height="500">
        <table style="width:100%;height:100%">
            <tbody>
            <tr>
                <td>
                    <div id="div_login">
                        <table style="width:100%;height:100%;background-color:#5776AC;">
                            <tbody>
                            <tr>
                                <td style=" height:150px;background-color:#5776AC;"></td>
                                <td class="style5"
                                    style=" background-repeat: no-repeat;padding-bottom:105px;padding-left:50px;"
                                    rowspan="2">

                                </td>
                                <td style="background-color:#5776AC;"></td>
                            </tr>
                            <tr>
                                <td id="tdLogin" class="style6" valign="top"
                                    style="border-style: none; border-width: thin; margin-left: 2px; margin-top:25pt">
                                    <table width="100%">
                                        <form action='${postUrl}' method='POST' id='loginForm' class='cssform' autocomplete='off' name='loginForm'>
                                            <tbody>
                                            <div id="user_status"></div>
                                            <tr>
                                                <td width="100" align="right">
                                                    <font size="2" color="#000000" face="Verdana" title="UserId"
                                                          style="font-weight:bold;"><label for='username' style="color:#000000;" ><g:message code="springSecurity.login.userID.label"/>:</label></font>
                                                </td>
                                                <td align="left">
                                                    <input type='text' class='text_' name='j_username' id='username' style="border-style: solid; border-width: 1px;width:165px;"/>
                                                    <div id="loginName_status"></div>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td width="80" align="right">
                                                    <font size="2" color="#000000" face="Verdana" title="Pasword"
                                                          style="font-weight:bold;"><label for='password' style="color:black"><g:message code="Password"/>:</label></font>
                                                </td>
                                                <td align="left">
                                                    <input type='password' class='text_' name='j_password' id='password'   style="border-style: solid; border-width: 1px;width:165px;"/>
                                                    <div id='password_status'></div>
                                                </td>
                                            </tr>


                                            <tr>
                                                <td></td>
                                                <td align="left">
                                                    <input type='button' id="submit1" value='${message(code: "login")}'
                                                           style="width: 170px"/>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td></td>
                                                %{--  <td align="left">
                                               <span class="forgot-link">
                                               <g:link controller='register' action='forgotPassword'><g:message code='spring.security.ui.login.forgotPassword'/></g:link>
                                                </span>
                                               </td>  --}%
                                            </tr>
                                            <tr>

                                                <div id="flashMessage">${flash.message}</div>
                                            </tr>

                                            </tbody>
                                        </form>
                                    </table>
                                </td>
                                <td class="style7" style="background-color:#5776AC"></td>
                                <td><label style="font-size: 30px"><b>TANZILLA</b></label><label style="font-size: 12px;color: #FF9933"><b>©<a style="color: #FF9933" href="http://www.tangerinedigital.com/">TANGERINE DIGITAL</a></b></label></td>
                            </tr>
                            <tr>
                                <td style="background-color:#5776AC"></td>
                                <td class="style5" style="background-color:#5776AC;"></td>
                                <td style="background-color:#5776AC;"></td>
                            </tr>
                            </tbody>
                        </table>

                    </div>

                    <div id="div_mess" style="position:relative;"></div>
                </td>
            </tr>
            </tbody>
        </table>
    </td>
</tr>
</tbody>
</table>
<script type='text/javascript'>
    <!--
    (function() {
        document.forms['loginForm'].elements['j_username'].focus();
    })();
    // -->
</script>
</body>
</html>

