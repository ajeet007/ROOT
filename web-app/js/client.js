
/*
 Revision History:
 Developer Name  : Ajeet Singh
 Date            : 02-July-2014
 Reason          : 1. Modified to add client
 */

var clientStatus=false;
$(document).ready(function()
{
    $("#update_client").attr("disabled",true);
    $('#update_client').css({'background-color':'#E0EBF8'});
    $("#clear").attr("disabled",true);
    $('#clear').css({'background-color':'#E0EBF8'});
});

function fillLogin(data)
{
    for (var i=0;i<data.length;i++)
    {
        $('#clientlist').append('<option value="' + data[i].id+ '">' + data[i].clientName + '</option>');
    }


}

function showClientInformation(obj)
{
    /*$("#logstatus").html('');*/
    if(!clientStatus)
    {
        var clientName =$("#clientlist option:selected").text();
        var clientId=$("#clientlist option:selected").val();

        jQuery.ajax
        ({
            type:'POST',
            url:g.createLink({controller: 'addClient', action: 'showClientinformation'}),
            data: "clientName=" + clientName+'&clientId='+clientId,
            success:function(data, textStatus)
            {
                enableTextBoxesAndButtons1(data)
            }
            ,error:function(XMLHttpRequest, textStatus, errorThrown) {
            }
        });
        return false;
    }
}


function enableTextBoxesAndButtons1(data)
{

    $("#clear").attr("disabled",false);
    $('#clear').css({'background-color':'#3E567D'});
    $("#update_client").attr("disabled",false);
    $('#update_client').css({'background-color':'#3E567D'});

    fillClientInformation1(data)
}

function fillClientInformation1(data)
{
    $('#clientName').val(data.clientName);
    $('#client_desc').val(data.description);
    $('#client_status option[value='+data.status+']').attr("selected", "selected");
}

$(document).ready(function(){

    $("#clear").click(function()
    {
        clearFields();
        $("#clientlist").find("option:selected").attr("selected",false);
        $("#update_client").attr("disabled",true);
        $('#update_client').css({'background-color':'#E0EBF8'});
        $("#clear").attr("disabled",true);
        $('#clear').css({'background-color':'#E0EBF8'});
        $("#create").attr("disabled",false);

        enable();
        return false;
    });


    //This function is using for save and update user
    $("#update_client").click(function(){

        var clientName=$("#clientName").val();
        var description=$("#client_desc").val();

        var status=$("#client_status").val();
        if(clientlist==""){
            alert("Client name not empty !");
            return false;
        }
        var clientId=$("#clientlist option:selected").val();

        var r;
        if(clientId)
            r=confirm("Client will be updated. Continue?");
        else
            r=confirm("New Client will be Added. Continue?");
        if(!r){
            return false;
        }
        else{
            jQuery.ajax
            ({
                type:'POST',
                url:g.createLink({controller: 'addClient', action: 'client'}),
                data: "clientName=" + clientName +'&description='+ description +'&clientId='+clientId +'&status='+status,
                success:function(data, textStatus)
                {
                    if(data)
                    {
                        if(!clientId)
                        {
                            alert( "'"+clientName+"' " +"New Client Add successfully");
                        }
                        else
                        {
                            alert("'"+clientName+ "' " +"Client updated successfully") ;
                        }

                        $("#clientlist  option").remove();
                        $("#clientId").val("");
                        clearFields();
                        fillLogin(data);
                        $("#pstatus").html('');
                    }
                }
                ,error:function(XMLHttpRequest, textStatus, errorThrown) {
                }
            });

        }
        return true;
    });
});

function clearFields()
{
    $('#clientName').val("");
    $('#client_desc').val("");
    $('#client_status option[value='+true+']').attr("selected", "selected");
    $('#clientName').focus();
}

function enableButton()
{
    $("#update_client").attr("disabled",false);
    $('#update_client').css({'background-color':'#3E567D'});
    $("#clear").attr("disabled",false);
    $('#clear').css({'background-color':'#3E567D'});
}

$(document).ready(function()
{

    $("#clientlist").bind("change",function()
    {
        showClientInformation(this);
    });

    $("#create").click(function()
    {
        $("#clientlist").find("option:selected").attr("selected",false);
        clearFields();
        enableButton();
    });



    $("input").keyup(function()
    {
        enableButton();
    });
    $("#clientlist").find("option:selected").attr("selected",false);
    clearFields()
});






