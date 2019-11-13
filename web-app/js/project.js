
/*
 Revision History:
 Developer Name  : Ajeet Singh
 Date            : 02-July-2014
 Reason          : 1. add project
 */

$(document).ready(function(){

    $("#clear").click(function()
    {
        clearFields();
        clearCMSFields();
        return false;
    });


    //This function is using for save and update user
    $("#update_project").click(function(){

        var projectName=$("#channelName").val();
        var projectCode=$("#channelCode").val();
        var startDate=$("#startDate").val();

        if(projectName==""){
            alert("Channel name not empty.");
            return false;
        }
        if(projectCode==""){
            alert("Channel Code not empty.");
            return false;
        }
        if(startDate==""){
            alert("Select Channel start date.");
            return false;
        }



        var r;
        if(projectName)
            r=confirm("New Channel will be Added. Continue?");
        if(!r){
            return false;
        }
        else{
            jQuery.ajax
            ({
                type:'POST',
                url:g.createLink({controller: 'addChannel', action: 'importChannel'}),
                data: "channelName=" + projectName +'&channelCode='+ projectCode +'&startDate='+ startDate,
                success:function(data, textStatus)
                {
                    if(data)
                    {
                        if(data.projectInfo && data.clientInfo){

                            alert( "Sorry, Channel "+"'"+data.projectInfo+"' " +"Channel ID "+"'"+data.clientInfo+"' "+"already exist !");
                        }
                        else if(projectName)
                        {
                            alert( "'"+projectName+"' " +"New Channel Add successfully");
                        }
                        clearFields();
                    }
                }
                ,error:function(XMLHttpRequest, textStatus, errorThrown) {
                }
            });

        }
        return true;
    });

    $("#cms_project").click(function(){

        var cmsName=$("#cmsName").val();
        var cmsCode=$("#cmsCode").val();
        var startDate=$("#startDate").val();

        if(cmsName==""){
            alert("CMS name not empty.");
            return false;
        }
        if(cmsCode==""){
            alert("ContentOwner ID not empty.");
            return false;
        }
        if(startDate==""){
            alert("Select CMS start date.");
            return false;
        }



        var r;
        if(cmsName)
            r=confirm("New CMS will be Added. Continue?");
        if(!r){
            return false;
        }
        else{
            jQuery.ajax
            ({
                type:'POST',
                url:g.createLink({controller: 'addCMS', action: 'importCMS'}),
                data: "cmsName=" + cmsName +'&cmsCode='+ cmsCode +'&startDate='+ startDate,
                success:function(data, textStatus)
                {
                    if(data)
                    {
                        if(data.cmsInfo && data.cms_Info){

                            alert( "Sorry, CMS "+"'"+data.cmsInfo+"' " +"ContentOwner ID "+"'"+data.cms_Info+"' "+"already exist !");
                        }
                        else if(cmsName)
                        {
                            alert( "'"+cmsName+"' " +"New CMS Add successfully");
                        }
                        clearCMSFields();
                    }
                }
                ,error:function(XMLHttpRequest, textStatus, errorThrown) {
                }
            });

        }
        return true;
    });
});

function clearCMSFields(){
    $('#cmsName').val("");
    $('#cmsCode').val("");
    $('#startDate').val("");
    $('#cmsName').focus();
}
function clearFields()
{
    $('#channelName').val("");
    $('#channelCode').val("");
    $('#startDate').val("");
    $('#channelName').focus();
}



function callCalendar(obj)
{
    $(obj).datepicker({dateFormat:'dd/mm/yy'}).datepicker( "show");
}

$(document).ready(function(){

/*    $(".ps").click(function () {
        var projectId = this.id;
        if (projectId != "") {
            jQuery.ajax
            ({
                type: 'POST',
                url: g.createLink({controller: 'accountingReport', action: 'projectReport'}),
                data: "projectId=" + projectId,
                success: function (data, textStatus) {
                    if (data) {
                        alert("success !");
                    }
                }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("error in view report contact system admin");
                }
            });
        }

    });*/
    $('#receipt_div').click(function()
    {
        $('#receipt_div').css({'font-size':'20px'});
    });
    $("#reportClientList").bind('change',function()
    {
        var articleID=$("#reportClientList option:selected").val();
        jQuery.ajax
        ({
            type:'POST',
            url:g.createLink({controller: 'addProject', action: 'clientProjectInformation'}),
            data:"clientID="+articleID,
            success:function(data,textStatus)
            {
                $("#reportProjectList option").remove();
                $('#reportProjectList').append('<option value="">-Select Project-</option>');
                fillProjectData(data);
            },
            error:function(XMLHttpRequest, textStatus, errorThrown) {
            }
        });
    });
    $('#articleDetails').click(function(){

        var url =g.createLink({controller: 'accountingReport', action: 'projectReport'});
        window.open (url+'?clientId='+$('#reportClientList').val()+'&projectId='+$('#reportProjectList').val()+'&deferredFrom='+$('#deferredFrom').val()+'&deferredTo='+$('#deferredTo').val(),"mywindow1","menubar=1,resizable=1,width=950,height=800,scrollbars=1" );

    });
});

function fillProjectData(data)
{
    for (var i=0;i<data.length;i++)
    {
        $('#reportProjectList').append('<option value="' + data[i].id+ '">' + data[i].projectName + '</option>');
    }
}
function shw(){
    $("#article_div").toggle();
}

/*
function sfuSubmitForm(form) {
    $("#originalFileName").val($("#swfupload_text").val());
    var selectedFolders = $("input:checked").map(function () {
        return this.value;
    }).get();
    if (selectedFolders.length < 1) {
        alert("Please select a  folder to upload file.");
        return false
    }
}*/




