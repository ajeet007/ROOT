
<!DOCTYPE html>
<% response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
%>
<html>
<head>
    <title>TAN-ZILLA</title>
    <link href="../images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
    <jq:resources/>
    <g:setProvider library="jquery"/>
    <jqui:resources/>
    <jawr:script src="/i18n/messages.properties"/>
    %{-- <g:javascript library='scriptaculous'/>--}%
    <link rel="stylesheet" media="screen" href="${resource(dir: 'css', file: 'homePage.css')}"/>
    %{--  <script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.simplemodal.js')}"></script>--}%

    %{-- <link rel="stylesheet" media="screen" href="${resource(dir: 'css/smoothness', file: 'jquery-ui-1.8.14.custom.css', plugin: 'spring-security-ui')}"/>--}%
    <link rel="stylesheet" media="screen" href="${resource(dir: 'css', file: 'jquery.jgrowl.css')}"/>
    %{--<link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}"/>--}%

    <link rel="stylesheet" media="screen" href="${resource(dir: 'css', file: 'jquery.safari-checkbox.css')}"/>
    <link rel="stylesheet" media="screen" href="${resource(dir: 'css', file: 'auth.css')}"/>
    <link rel="stylesheet" href="${resource(dir:'css',file:'menue.css')}" />
    <link rel="stylesheet" href="${resource(dir:'css',file:'Style.css')}"/>
    <link rel="stylesheet" href="${resource(dir:'css',file:'editionInfo.css')}"/>
    %{--<g:javascript library="application" />--}%
    <g:layoutHead />
    <r:layoutResources>
        <g:javascript library='application' />
    </r:layoutResources>
</head>
<body  style="background-color:#5776AC">
<sec:ifLoggedIn >
    <g:render template="/home/header"/>
    <s2ui:showFlash/>
    <g:layoutBody />
    <g:render template="/home/footer"/>
</sec:ifLoggedIn>
<sec:ifNotLoggedIn >
    <g:layoutBody />
</sec:ifNotLoggedIn>
</body>
</html>