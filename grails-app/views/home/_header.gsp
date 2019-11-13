<script type="text/javascript">
    $(document).ready(function () {
        $("#channelManagerHeader").mouseover(function () {
            $("#addChannel").css('visibility', 'visible');
        });
        $("#channelManagerHeader").mouseout(function () {
            $("#addChannel").css('visibility', 'hidden');
        });
    });
</script>

<div id="div_main"
     style="padding: 0px; margin: 0px; font-family: verdana; font-size: 12px; color: #FFFFFF; text-decoration: none; background-color: #5776AC">
    <table style="width: 100%;" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td bgcolor="#3E567D"></td>
            <td bgcolor="#3E567D">

                <g:if test="${(params.action == 'analyticsReport')  && params.controller == 'youTubeChannel' || (params.action == 'importChannel' && params.controller == 'addChannel') || (params.action == 'importCMS' && params.controller == 'addCMS')}">
                    <div id="channelManagerHeader" style="float: left; padding-left: 10px; width: 70px; height: 20px; border-left-style: solid;background-color:#808080; border-left-width: 1px; border-left-color: #C0C0C0;"><g:link
                            controller="youTubeChannel" action="analyticsReport"><b>YouTube</b></g:link>
                    <div id="addChannel" style="visibility:hidden;padding-top: 7px;">
                        <ul>
                            <g:link controller="addChannel" action="importChannel"><b>+Channel</b></g:link>
                            <li><br></li>
                        </ul>
                        <ul>
                            <g:link controller="addCMS" action="importCMS"><b>+CMS</b></g:link>
                            <li><br></li>
                        </ul>

                    </div>
                    </div>
                </g:if>
                <g:else>
                    <div style="float: left; padding-left: 10px; width: 70px; height: 20px; border-left-style: solid; border-left-width: 1px; border-left-color: #C0C0C0;"><g:link
                            controller="youTubeChannel" action="analyticsReport"><b>YouTube</b></g:link>
                        <div id="addChannel" style="visibility:hidden;padding-top: 7px;">
                            <ul>
                                <g:link controller="addChannel" action="importChannel"><b>+Channel</b></g:link>
                                <li><br></li>
                            </ul>
                            <ul>
                                <g:link controller="addCMS" action="importCMS"><b>+CMS</b></g:link>
                                <li><br></li>
                            </ul>

                        </div>
                    </div>
                </g:else>

                <g:if test="${((session.user?.role?.authority) == 'ROLE_SUPERADMIN')}">

                    <g:if test="${params.controller == 'admin' || params.controller == 'register' || params.controller == 'addChannel' && params.action == 'channel' || params.controller == 'addCMS' && params.action == 'cms'}">
                        <div style="float: left; padding-left: 10px; width: 100px; height: 20px;background-color: #808080; border-left-style: solid; border-left-width: 1px; border-left-color: #C0C0C0;"><g:link
                                controller="admin"><b>Administrator</b></g:link></div>
                    </g:if>
                    <g:else>
                        <div style="float: left; padding-left: 10px; width: 100px; height: 20px;border-left-style: solid; border-left-width: 1px; border-left-color: #C0C0C0;"><g:link
                                controller="admin"><b>Administrator</b></g:link></div>
                    </g:else>
                </g:if>
                <g:if test="${params.controller == 'changePassword'}">
                    <div style="float: left; padding-left: 10px; width: 130px; height: 20px; border-left-style: solid;background-color: #808080; border-left-width: 1px; border-left-color: #C0C0C0;"><g:link
                            controller="changePassword"><b>Change Password</b></g:link></div>
                </g:if>
                <g:else>
                    <div style="float: left; padding-left: 10px; width: 130px; height: 20px; border-left-style: solid; border-left-width: 1px; border-left-color: #C0C0C0;"><g:link
                            controller="changePassword"><b>Change Password</b></g:link></div>
                </g:else>
                <g:if test="${params.controller == 'logout'}">
                    <div style="float: left; padding-left: 10px; width: 70px;background-color: #808080; height: 20px; border-left-style: solid; border-left-width: 1px; border-left-color: #C0C0C0;"><g:link
                            controller="logout"><b>Logout</b></g:link></div>

                </g:if>
                <g:else>
                    <div style="float: left; padding-left: 10px; width: 70px; height: 20px; border-left-style: solid; border-left-width: 1px; border-left-color: #C0C0C0;"><g:link
                            controller="logout"><b>Logout</b></g:link></div>
                </g:else>
        </tr>

    </table>
</div>