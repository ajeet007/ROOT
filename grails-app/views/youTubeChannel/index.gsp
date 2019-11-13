<%--
  Created by IntelliJ IDEA.
  User: administrator
  Date: 14/11/14
  Time: 1:50 PM
  To change this template use File | Settings | File Templates.
--%>


<%@ page import="com.tan.Role; com.tan.User" contentType="text/html;charset=UTF-8" %>
<% response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
%>
<html>
<head><title>YouTube</title>
    <meta name='layout' content='main'/>
    <jq:resources/>
    <g:setProvider library="jquery"/>
    <g:urlMappings/>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'youtubeScript.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'jquery-ui.min.js')}"></script>
    <link rel="stylesheet" href="${resource(dir:'css',file:'jquery-ui.css')}" />
   %{-- <link rel="stylesheet" href="${resource(dir:'css',file:'jquery-ui-1.8.15.custom.css')}" />--}%
    <style>
    .right {
        float: right
    }
    .loading1 {
        display: none;
    }
    .validateTips {
        border: 1px solid transparent;
        padding: 0.3em;
    }
    </style>

</head>

<body>

<div id="div1" style="padding: 0px; margin: 0px; height: 500px; font-size: 12px; font-weight: inherit; color: #FFFFFF; text-decoration: none; background-color: #5776AC">
    <div style="left: 40%;top: 30%;position: absolute;">

        <g:form id="postComment" name="postComment" controller="youTubeChannel" action="sendMessage" >
            <table>
                <tr>
                    <td>
                        Sign in with your Google Account.
                    </td>
                </tr>
                <tr>
                    <td>
                        <g:textField name="youTubeUserName" id="youTubeUserName" placeholder="Email"></g:textField>
                    </td>
                </tr>
                <tr>
                    <td>
                        <g:passwordField name="youTubePassword" id="youTubePassword" placeholder="Password"></g:passwordField>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: center">
                        <g:submitButton name="loginYouTube" id="loginYouTube" value="Sign in"></g:submitButton>
                    </td>
                </tr>
            </table>
        </g:form>
    </div>
</div>
<g:render template="sendMessage"/>
<g:render template="loader"/>

</body>
</html>


