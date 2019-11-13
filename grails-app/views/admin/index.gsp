<%--
  Created by IntelliJ IDEA.
  User: Ajeet Singh
  Date: 4/8/14
  Time: 6:12 PM
  Description: administrator panel
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<% response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
%>
<html>
<head><title>admin</title>
    <meta name='layout' content='main'/>
</head>
<body>
<div style="padding-top:5%;width:30%;height:450px;padding-left: 2%;">
    <g:if test="${(session.user?.role?.authority) == 'ROLE_SUPERADMIN'}">
        <div id="admin_mgmt" style=" padding-left: 2%; padding-bottom:4%;"><g:link controller="register"
                                                                                   action="index"><b>Control Panel</b></g:link>
        </div>
        <div id="channel_import" style=" padding-left: 2%; padding-bottom:4%;"><g:link controller="addChannel"
                                                                                   action="channel"><b>Import Channel  Data</b></g:link>
        </div>
        <div id="cms_import" style=" padding-left: 2%; padding-bottom:4%;"><g:link controller="addCMS"
                                                                                       action="cms"><b>Import CMS  Data</b></g:link>
        </div>
    </g:if>
    %{--<g:if test="${((session.user?.role?.authority) == 'ROLE_MANAGER') || ((session.user?.role?.authority) == 'ROLE_MQCCW') || ((session.user?.role?.authority) == 'ROLE_MCW') || ((session.user?.role?.authority) == 'ROLE_MQC')}">
        <div id="manager_mgmt" style=" padding-left: 2%; padding-bottom:4%;"><b><g:link controller="managerManagement"
                                                                                        action="managerPanel">Manager Panel</g:link></b>
        </div>
    </g:if>--}%
</div>
</body>
</html>
