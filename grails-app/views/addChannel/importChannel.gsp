<%--
Revision History:
Developer Name  : Ajeet Singh
Date            : 04-Feb-2015
Reason          : 1. Add and update new client information

--%>
<%@ page import="java.text.SimpleDateFormat;" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<% response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
%>
<html>
<head>
    <title>Channel Details</title>
    <g:urlMappings />
    <resource:autoComplete skin="default"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name='layout' content='main'/>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'project.js')}"></script>
</head>

<body>
<% SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); %>
<div id="div1"  style="padding: 0px; margin-left:auto;margin-right:auto; height: 500px; font-family: verdana; font-size: 12px; color: #FFFFFF; text-decoration: none; background-color: #5776AC">


    <br/>
    <div style="margin-left:auto;margin-right:auto;background-color:#E0EBF8;top:40px;width:750px" align="center">
        <fieldset style="color:Black" ><legend>Add Channel</legend>
            <div class="nav">

            </div>
            <div class="body">
                <g:form  id="save" name="save">

                    <div id="updte1">
                        <table align="center">


                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label>Channel Name</label>
                                </td>
                                <td valign="top">
                                    <g:textField style="background-color: #f5f5f5;width: 210px;" name="channelName" id="channelName" value="" autofocus="autofocus"/>
                                </td>

                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label>Channel ID</label>
                                </td>
                                <td valign="top">
                                    <g:textField style="background-color: #f5f5f5;width: 210px;" name="channelCode" id="channelCode"  value="" autofocus="autofocus"/>
                                </td>

                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label>Start Date</label>
                                </td>
                                <td valign="top">
                                    <input type="text" readonly="readonly" id="startDate" name="startDate" value="" style="width:210px" onclick="callCalendar(this)"/>
                                </td>
                            </tr>

                        </table>
                    </div>
                    <div  style="padding-top: 25px;padding-left: 175px;float: left;">
                        <span class="button"><input type="button" style="width: 120px;" id="update_project" class="X"
                                                    value="${message(code: 'default.button.Save.label', default: 'Save')}"/></span>
                        &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
                        <span class="button"><input type="button"  style="width: 120px;" id="clear" class="X" onclick=""
                                                    value="${message(code: 'default.button.Cancel.label', default: 'Cancel')}"/></span>

                    </div>
                </g:form>
            </div>
        </fieldset>
    </div>

</div>
</body>
</html>