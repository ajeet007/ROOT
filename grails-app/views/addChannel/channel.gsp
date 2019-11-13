<%--
Revision History:
Developer Name  : Ajeet Singh
Date            : 04-Feb-2015
Reason          : 1. Add and update new client information

--%>
<%@ page import="com.tan.TblYoutubeChannel; java.text.SimpleDateFormat;" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<% response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
%>
<html>
<head>
    <title>import channel</title>
    <g:urlMappings />
    <resource:autoComplete skin="default"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name='layout' content='main'/>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'project.js')}"></script>
</head>

<body>
<% SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); %>
<div id="div1"  style="padding: 0px; margin-left:auto;margin-right:auto; height: auto; font-family: verdana; font-size: 12px; color: #FFFFFF; text-decoration: none; background-color: #5776AC">


    <br/>
    <div style="margin-left:auto;margin-right:auto;background-color:#E0EBF8;top:40px;width:900px;height: auto" align="center">
        <fieldset style="color:Black" ><legend>Import Channel</legend>
            <div class="nav">

            </div>
            <div class="body">
                <g:form  id="channel" name="channel">
                    <div id="channel_list" style="width:850px;height: 380px;overflow: auto;color: #FFFFFF; text-decoration: none; background-color: #5776AC ">
                        <table id="channel_details" border="2px" style="border-collapse: collapse;width: 100%">
                            <g:each in="${com.tan.TblYoutubeChannel.findAll()}" status="i" var="channelInstance">
                                    <tr id="${channelInstance?.channelId}" style="background: #3E567D">
                                        <td width="120px"align="center">${channelInstance?.channelName}</td>
                                      <g:each in="${dateMap}" status="j" var="dateInstance">
                                          <g:if test="${(dateInstance.key) == (channelInstance.channelId)}">
                                        <td width="100px"align="center">${dateInstance.value}</td>

                                        <td width="130px" align="center"><g:link controller="youtube" action="auth"
                                                                                 params="[channelId: channelInstance.channelId,startDate:dateInstance.value]">
                                            <input type="button" value="Authorize" class="X"
                                                   style="width:80px;height:22px;background: #FF9933"/></g:link>
                                        </td>
                                        <td width="130px" align="center"><g:link controller="addChannel" action="channel"
                                                                                       params="[channelId: channelInstance.channelId,startDate:dateInstance.value]">
                                                  <input type="button" value="Import" class="X"
                                                         style="width:80px;height:22px;background: #FF9933"/></g:link>
                                        </td>
                                          </g:if>
                                      </g:each>
                                    </tr>
                            </g:each>
                        </table>
                    </div>
                </g:form>
            </div>
        </fieldset>
    </div>

</div>
</body>
</html>