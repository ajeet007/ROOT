/*
 Revision History:
 Developer Name  : Ajeet Singh
 Date            : 25-June-2014
 Reason          : 1. Modified to add user
 2. Modified to assign multiple roles to a single user.
 */

var allroles = [];

$(document).ready(function()
{
    $("#assign").click(function()
    {

        $("#unassignedRoles option:selected").each(function()
        {
                $('#assignedRoles').append($(this).clone());
                $(this).remove();
        });
    });

    $("#unassign").click(function()
    {

        $("#assignedRoles option:selected").each(function () {
            $('#unassignedRoles').append($(this).clone());
            $(this).remove();
        })
    });

});


$(document).ready(function(){
    $("#update").click(function()
    {
        var clientId=$("#clientId").val();
        var projectName=$("#projectName").val();
        var projectId=$("#projectId").val();
        var user_roles = [];
        jQuery("#unassignedRoles option").each(function()
        {
            user_roles.push($(this).val());
        });

        if((projectId||projectName)=="" )
        {
            return false;
        }
        var r;
        if(projectId)
        {
            r=confirm("Team will be  added. Continue?");
        }
        if(!r){
            return false;
        }
        else
        {
            jQuery.ajax
            ({
                type:'POST',
                url:g.createLink({controller: 'dashboard', action: 'createTeam'}),
                data: "projectId=" + projectId+ '&user_roles=' + user_roles,
                success:function(data, textStatus)
                {
                    if(data)
                    {
                        if(projectName)
                        {
                            alert( "'"+projectName+"' " +"New Team created successfully");
                        }


                    }
                }
                ,error:function(XMLHttpRequest, textStatus, errorThrown) {}
            });

        }
        return true;

    });

});

$(document).ready(function()
{
    jQuery("#assignedRoles option").each(function()
    {
        var id = $(this).val()
        allroles.push([id,$(this).text()])
    });


    $("#clear").click(function()
    {
        $("#unassignedRoles option").each(function()
        {
            if($(this).text() != 'ROLE_SUPERADMIN')
            {
                $('#assignedRoles').append($(this).clone());
            }
            $(this).remove();
        });
        return false;
    });
});
