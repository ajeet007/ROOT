/*
Revision History:
Developer Name  : Ajeet Singh
Date            : 25-June-2014
Reason          : 1. Modified to add user
                  2. Modified to assign multiple roles to a single user.
*/
checkStatus=false;
var checkStatus=true;
var userStatus=false;
var userExists=false;
var allroles = [];

$(document).ready(function()
{
    $("#update").attr("disabled",true);
    $('#update').css({'background-color':'#E0EBF8'});
    /*$("#deleteIcon").attr("disabled",true);*/
    $('#delete').css({'background-color':'#E0EBF8'});
    $('#delete').css({'background-color':'#E0EBF8'});
    $("#clear").attr("disabled",true);
    $('#clear').css({'background-color':'#E0EBF8'});

    $("#assign").click(function()
    {

        $("#unassignedRoles option:selected").each(function()
        {
            if($(this).text() != "ROLE_SUPERADMIN")
            {
                $('#assignedRoles').append($(this).clone());
                $(this).remove();
            }
        });
    });

    $("#unassign").click(function()
    {
       /* $("#assignedRoles option:selected").each(function()
        {
            $('#unassignedRoles').append($(this).clone());
            $(this).remove();
        })*/

        $("#assignedRoles option:selected").each(function () {
/*            if ($('#unassignedRoles option').text()!="") {
                alert("One user have only one role.");
            }
            else {*/
                $('#unassignedRoles').append($(this).clone());
                $(this).remove();
            /*}*/
        });

    });

    $("#loginName").bind("change",function()
    {
        jQuery.ajax
        ({
            type:'POST',
            url:g.createLink({controller: 'userMaster', action: 'userExistsOrNot'}),

            data: "loginName=" + $('#loginName').val(),
            success:function(data, textStatus)
            {
                if(data=='true')
                {
                   /*$("#logstatus").html('<font color=red>'+messages.js.user.existsLabel.message()+'</font>')*/
                    $("#logstatus").html('<font color=red>'+"Login Name is already in use."+'</font>')
                   userExists=true;
                }
                else
                {
                    $("#logstatus").html('');
                    userExists=false;
                }
            }
            ,error:function(XMLHttpRequest, textStatus, errorThrown) {}
        });
        return false;
    });
});

function fillLogin(data)
{
    for (var i=0;i<data.length;i++)
    {
        $('#userlist').append('<option value="' + data[i].id+ '">' + data[i].loginName + '</option>');
    }

    $("#unassignedRoles option").each(function()
    {

        if($(this).text() != "ROLE_SUPERADMIN")
        {
            $('#assignedRoles').append($(this).clone());
        }
        $(this).remove();
    });
}

function showUserInformation(obj)
{
    $("#logstatus").html('');
    if(!userStatus)
    {
        var userName =$("#userlist option:selected").text();
        var userId=$("#userlist option:selected").val();
        $('#loginId').val(userId);

        jQuery.ajax
        ({
            type:'POST',
            url:g.createLink({controller: 'userMaster', action: 'showUserinformation'}),
            data: "userName=" + userName+'&userId='+userId,
            success:function(data, textStatus)
            {
                enableTextBoxesAndButtons1(data)
            }
            ,error:function(XMLHttpRequest, textStatus, errorThrown) {}
        });
        return false;
    }
}


function enableTextBoxesAndButtons1(data)
{

    $("#clear").attr("disabled",false);
    $('#clear').css({'background-color':'#3E567D'});
    $("#update").attr("disabled",false);
    $('#update').css({'background-color':'#3E567D'});

    $('#assignedRoles option').remove();

    for(key in allroles)
    {
        $('#assignedRoles').append('<option value="' + allroles[key].toString().substring(0, allroles[key].toString().indexOf(','))+ '">' + allroles[key].toString().substring(allroles[key].toString().indexOf(',') + 1, allroles[key].toString().length) + '</option>');
    }

    fillUserInformation1(data)
}

function fillUserInformation1(data)
{
    $('#unassignedRoles').empty();

    $('#userName').val(data.userInformation.loginName);
    $('#loginName').val(data.userInformation.username);
    $.each(data.role, function()
    {
        $('#unassignedRoles').append('<option value="' + this.id+ '">' + this.authority + '</option>');
        $("#assignedRoles option[value="+this.id + "]").remove();
    });
}

$(document).ready(function(){

    $("#clear").click(function()
    {
        clearFields();
        $("#logstatus").html("");
        $("#pstatus").html('');
        $("#loginId").val("");
        $("#userlist").find("option:selected").attr("selected",false);
        $("#update").attr("disabled",true);
        $('#update').css({'background-color':'#E0EBF8'});
        $("#delete").attr("disabled",true);
        $('#delete').css({'background-color':'#E0EBF8'});
        $("#clear").attr("disabled",true);
        $('#clear').css({'background-color':'#E0EBF8'});
        $("#create").attr("disabled",false);

        $("#unassignedRoles option").each(function()
        {
            if($(this).text() != 'ROLE_SUPERADMIN')
            {
                $('#assignedRoles').append($(this).clone());
            }
            $(this).remove();
         });
        enable();
        return false;
      });


       //This function is using for save and update user
    $("#update").click(function()
    {
        var userName=$("#userName").val();
        var loginName=$("#loginName").val();
        var password=$("#password").val();
        var cpassword=$("#cpassword").val();

        var user_roles = [];
        jQuery("#unassignedRoles option").each(function()
        {
            user_roles.push($(this).val());
        });

        if((userName||loginName||password||cpassword)=="" || userExists)
        {
            return false;
        }
        else if(password!=cpassword)
        {
            $('#password').val("");
            $('#cpassword').val("");
            $("#pstatus").html("<p style='color: red;font-size:9px;'></p>");
            $('#password').focus();
            return false;
        }

        var loginId=$("#userlist option:selected").val();

        var r;

        /*if(loginId)
            r=confirm(messages.js.update.msg.message());
        else
            r=confirm(messages.js.createUser.msg.message());*/
        if(loginId)
            r=confirm("User will be updated. Continue?");
        else
            r=confirm("New user will be created. Continue?");
        if(!r)
            return false;
        else
        {
            jQuery.ajax
            ({
                type:'POST',
                url:g.createLink({controller: 'register', action: 'register'}),
                data: "username=" + userName+'&loginName='+loginName+'&password='+password+'&loginId='+loginId+'&cpassword='+cpassword + '&user_roles=' + user_roles,
                success:function(data, textStatus)
                {
                    if(data)
                    {
                    if(!loginId)
                    {
                        /*alert( "'"+userName+"' " +messages.js.newUser.msg.message());*/
                        alert( "'"+userName+"' " +"New user created successfully");
                    }
                    else
                    {
                       /* alert("'"+userName+ "' " +messages.js.newUser.updateMsg.message()) ;*/
                        alert("'"+userName+ "' " +"User updated successfully") ;
                    }

                    $("#userlist option").remove();
                    $("#loginId").val("");
                    clearFields();
                    fillLogin(data);
                    $("#pstatus").html('');
                    }
                }
                ,error:function(XMLHttpRequest, textStatus, errorThrown) {}
            });

        }

        enable()
        $('#userName').focus();
        return true;

    });

    //This function is use for matching password
    $("#cpassword").keyup(function()
    {
        var plength=$("#password").val().length;
        for(var i=0;i<$(this).val().length;i++)
        {
            if($(this).val().charAt(i)==$("#password").val().charAt(i))
            {
                if(i==plength-1 && $(this).val()==$("#password").val())
                {
                   /* $("#pstatus").html('<font color=red>'+messages.js.password.matchMsg.message()+'</font>');*/
                    $("#pstatus").html('<font color=red>'+"Passwords match"+'</font>');
                }
            }
            else
            {
               /* $("#pstatus").html('<font color=red>'+messages.js.password.notMatchedMsg.message()+'</font>');*/
                $("#pstatus").html('<font color=red>'+"Passwords do not match"+'</font>');
                return false;
            }
        }
        if($("#cpassword").val()=="")
        {
            $("#pstatus").html('');
        }
    });
});

function clearFields()
{
    $('#userName').val("");
    $('#password').val("");
    $('#cpassword').val("");
    $('#loginName').val("");
    $('#loginName').focus();
    $("p").hide();
}

function enableButton()
{
    $("#update").attr("disabled",false);
    $('#update').css({'background-color':'#3E567D'});
    $("#delete").attr("disabled",false);
    $('#delete').css({'background-color':'#3E567D'});
    $("#clear").attr("disabled",false);
    $('#clear').css({'background-color':'#3E567D'});
}

$(document).ready(function()
{
    jQuery("#assignedRoles option").each(function()
    {
        var id = $(this).val()
        allroles.push([id,$(this).text()])
    });

    $("#userlist").bind("change",function()
    {
        disable();
        $("#changePassword").html("<a  id='changepassword' style='cursor:pointer;text-decoration: underline;;color:#003399;'>Change password?</a>");
        showUserInformation(this);
    });

   $("#create").click(function()
    {
        $("#userlist").find("option:selected").attr("selected",false);
        clearFields();
        enableButton();
        enable();
    $("#unassignedRoles option").each(function()
       {
        if($(this).text() != "ROLE_SUPERADMIN")
        {
            $('#assignedRoles').append($(this).clone());
        }
        $(this).remove();
       });
    });



    $("input").keyup(function()
    {
        enableButton();
    });

    $("#userlist").find("option:selected").attr("selected",false);
    $("#changePassword").toggle
    (
        function()
        {
            changepass();
        },

        function()
        {
            disable();
            $("#changePassword").html("<a  id='changepassword' style='cursor:pointer;text-decoration: underline;;color:#003399;'>Change password?</a>");
            $('#password').val("");
            $('#cpassword').val("");
            $("#pstatus").html("<p style='color: red;font-size:9px;'></p>");
        }
    );

   clearFields()
})

$(document).ready(function()
{
    //This function is use for show user information for  update
    $("#agencyid").bind('change',function()
    {
        var agentID=$("#agencyid option:selected").val();
        var agentName=$("#agencyid option:selected").text();
        $('#agentID').val(agentID);

        jQuery.ajax
        ({
            type:'POST',
            url:g.createLink({controller: 'userMaster', action: 'showLogAgentUser'}),
            data:"agentID="+agentID,
            success:function(data,textStatus)
            {
                $("#userlist option").remove();
                $("#loginId").val("");
                clearFields();
                fillLogin(data);
            },
            error:function(XMLHttpRequest, textStatus, errorThrown) {}
        });
    });


    $('#statusid').change(function() {
        if($('#agencyID').val()!="" && $('#selectedagencycode').val()!="") {
        $("#agencyStatus").attr("disabled", false);
        $('#agencyStatus').css({'background-color':'#3E567D'});
       }
   });

    });

function disable()
{
    $("#password").attr("disabled",true) ;
    $('#password').css({'background-color':'#E0EBF8'});
    $("#cpassword").attr("disabled",true);
    $('#cpassword').css({'background-color':'#E0EBF8'});
}

function enable()
{
    $("#password").attr("disabled",false) ;
    $('#password').css({'background-color':'#f5f5f5'});
    $("#cpassword").attr("disabled",false);
    $('#cpassword').css({'background-color':'#f5f5f5'});
    $("#changepassword").remove();
}

function changepass()
{
    $("#password").attr("disabled",false) ;
    $('#password').css({'background-color':'#f5f5f5'});
    $("#cpassword").attr("disabled",false);
    $('#cpassword').css({'background-color':'#f5f5f5'});
    $("#changePassword").html("<a id='changepassword' style='cursor:pointer;text-decoration: underline;;color:#003399;'>Unchange password?</a>");
    $('#password').focus();
}

