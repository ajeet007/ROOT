<%--
Revision History:
Developer Name  : Ajeet Singh
Date            : 25-June-2014
Reason          : 1. Modified to add user
                  2. Modified to assign multiple roles to a single user.
--%>
<%@ page import="com.tan.User; com.tan.Role;" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<% response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
%>
<html>
<head>
    <g:javascript library="jquery"/>
    <g:urlMappings />
    <g:set var="entityName" value="${message(code: 'User.label', default: 'User')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>

    <script type="text/javascript">

    </script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name='layout' content='main'/>



    <script type="text/javascript" src="${resource(dir: 'js', file: 'CreateUser.js')}"></script>

</head>

<body>
<div id="div1"  style="padding: 0px; margin-left:auto;margin-right:auto; height: 500px; font-family: verdana; font-size: 12px; color: #FFFFFF; text-decoration: none; background-color: #5776AC">


    <br/>
    <div style="margin-left:auto;margin-right:auto;background-color:#E0EBF8;top:40px;width:750px" align="center">
        <fieldset style="color:Black" ><legend>Manage User</legend>
            <div class="nav">

            </div>
            <div class="body">

                <g:if test="${flash.message}">

                </g:if>
                <g:hasErrors bean="${tbl_UserInstance}">
                    <div class="errors">
                        <g:renderErrors bean="${tbl_UserInstance}" as="list"/>
                    </div>
                </g:hasErrors>

                <div id="updte" style="padding-top:0px;float: left;">
                    <table border="0px;">

                        %{--<tr>
                            <td colspan="2">


                                <g:select from="${Role.findAllByAuthorityInList(['ROLE_USER'])}" optionKey="id" optionValue="authority"  id="userid" name="userid" value="${session?.user}" noSelection="['':'-Select manager-']" style="width: 160px;background-color: #E0EBF8;"/>
                            </td>
                        </tr>--}%

                        <tr>
                            <td colspan="2">
                                <g:select from="${User.findAllByRoleInList([Role.findById(2)],[sort:'username'])}"  optionValue="loginName"  optionKey="id"  id="userlist"  name="userlist" size="20" style="width: 160px;background-color: #E0EBF8;"/>
                            </td>
                        </tr>
                        <tr>
                            <td>

                                %{-- <span class="button"><input title="Create User" type="image" src="../web-app/images/add.png" height="32" width="32" id="create"  name="create" class="" value="" /></span>--}%
                                <img title="Create User"  style="text-align:center;cursor:pointer;" src="${createLinkTo(dir: 'images', file: 'add.png')}"  id="create" name="create"  /><div style="width: 50px; float: right; margin-top: 4px; padding-right: 35px;"></div></td>
                            <td align="right">
                            <img title="Delete User"  style="text-align:center;cursor:pointer;" src="${createLinkTo(dir: 'images', file: 'delete.png')}"  id="deleteIcon" />
                            </td>
                        </tr>

                    </table>
                </div>
                <g:form  id="save" name="save">


                    <div style="width:1px;height:370px;background-color:black;float:left;margin-left: 5px"></div>


                    <div id="updte1" style="padding-top:0px;float: left;margin-left: 7px;">
                        <table align="left">



                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="loginName"><g:message code="User.username.label"
                                                                      default="Email"/></label>
                                </td>
                                <td valign="top"
                                    class="value ${hasErrors(bean: tbl_UserInstance, field: 'loginName', 'errors')}">
                                    <g:textField style="background-color: #f5f5f5;width:210px" name="loginName" id="loginName" value="${tbl_UserInstance?.loginName}"/>

                                </td>
                                <td>
                                    <div style="height: 5px;" id="logstatus"></div>
                                </td>
                            </tr>



                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="Name"><g:message code="User.loginName.label"
                                                                 default="Name"/></label>
                                </td>
                                <td valign="top"
                                    class="value ${hasErrors(bean: tbl_UserInstance, field: 'username', 'errors')}">
                                    <g:textField style="background-color: #f5f5f5;width: 210px;" name="userName" id="userName" value="${tbl_UserInstance?.username}" autofocus="autofocus"/>
                                </td>

                            </tr>


                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="password"><g:message code="User.password.label"
                                                                     default="Password"/></label>
                                </td>
                                <td valign="top"
                                    class="value ${hasErrors(bean: tbl_UserInstance, field: 'password', 'errors')}">
                                    <g:passwordField style="background-color:#f5f5f5;width:210px" name="password" id="password" value="${tbl_UserInstance?.password}"/>

                                </td>

                                <td>
                                    <div  style="height:5px;" id="changePassword"></div>
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    Confirm Password
                                </td>
                                <td  valign="top" class="value ${hasErrors(bean: tbl_UserInstance, field: 'password', 'errors')}">
                                    <g:passwordField style="background-color: #f5f5f5;width:210px" name="cpassword" id="cpassword" value="${tbl_UserInstance?.password}"/>
                                </td>
                                <td>
                                    <div style="height:5px;" id="pstatus"></div>
                                </td>
                            </tr>

                        </table>
                    </div>
                    <div id="auth" style="float: left; padding-left:0px;padding-top: 10px;">
                        <table border="0px" align="left" style="padding-left:2px;margin-left: 5px;">
                            <caption>Unassigned Roles</caption>
                            <tr>
                                <td valign="top">
                                    <g:select from="${Role.findAllByAuthorityInList(['ROLE_EDITOR'])}" optionKey="id"  optionValue="authority"  size="8" name="assignedRoles" id="assignedRoles" multiple="multiple"  style="width:215px;background-color: #E0EBF8;" />
                                </td>
                            </tr>
                        </table>
                        <div style="width:30px;height: 30px;float: left;">
                            <div style="padding-top: 30px;padding-left:15px">
                                <input type="button" id="assign" name="assign" value="<" />
                            </div>
                            <div style="padding-top: 10px;padding-left:15px;">
                                <input type="button" id="unassign" name="unassign" value=">"/>
                            </div>

                        </div>

                        <table border="0px" align="right" style="padding-left:2px;margin-left: 40px;">
                            <caption>Assigned Roles</caption>
                            <tr>
                                <td valign="top">
                                    <g:select from=""  optionValue="authority" optionKey="id"  size="8" name="unassignedRoles"  id="unassignedRoles" multiple="multiple" style="width:215px;background-color: #E0EBF8;" />

                                </td>
                            </tr>
                        </table>

                    </div>

                    <div  style="padding-top: 70px;padding-left: 150px;float: left;">
                        <span class="button"><input type="button" style="width: 120px;" id="update" class="X"
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