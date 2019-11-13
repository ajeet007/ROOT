<%--
  Created by IntelliJ IDEA.
  User: administrator
  Date: 23/6/14
  Time: 12:40 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<% response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
%>
<html>
<head>
    <title>TAN - User Registration</title>
    <meta name="layout" content="main" />
</head>
<body>
<g:form class="simpleform" url="[controller:'user',action:'registration']">
    <fieldset>
        <legend>User Registration</legend>
        <p class="info">
            Complete the form below to create an account!
        </p>
        <g:hasErrors bean="${user}">
            <div class="errors">
                <g:renderErrors bean="${user}"/>
            </div>
        </g:hasErrors>
        <p>
            <label for="username">Username</label>
            <g:textField name="username" value="${user?.username}"
                         class="${hasErrors(bean:user,field:'username','errors')}"/>
        </p>
        <p>
            <label for="password">Password</label>
            <g:passwordField name="password"
                             class="${hasErrors(bean:user,field:'password','errors')}" />
        </p>
        <p>
            <label for="confirm">Confirm Password</label>
            <g:passwordField name="confirm"
                             class="${hasErrors(bean:user,field:'password','errors')}" />
        </p>
        <p>
            <label for="firstName">First Name</label>
            <g:textField name="firstName" value="${user?.firstName}"
                         class="${hasErrors(bean:user,field:'firstName','errors')}" />
        </p>
        <p>
            <label for="lastName">Last Name</label>
            <g:textField name="lastName" value="${user?.lastName}"
                         class="${hasErrors(bean:user,field:'lastName','errors')}" />
        </p>
        <p class="button">
            <label>&nbsp;</label>
            <g:submitButton class="button" name="submitButton" value="Create Account" />
        </p>
    </fieldset>
</g:form>
</body>
</html>