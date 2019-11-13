/**
 * Created with IntelliJ IDEA.
 * User: Ajeet Singh
 * Date: 14/11/14
 * Time: 2:55 PM
 * To get the video details.
 */

var userCommentsList=null;
var userName="";
$(document).ready(function () {
    $(document).bind('keypress', function (e) {
        if (e.keyCode == 13) {
            $('#getComment').trigger('click');
        }
    });
    $("#getComment").click(function () {
        $("#commentDiv").empty();
        if ($("#videoId").val() != "") {
            showLoader();
            jQuery.ajax
            ({
                type: 'POST',
                url: g.createLink({controller: 'youTubeChannel', action: 'youtube'}),
                data: "videoId=" + $("#videoId").val(),
                dataType: "json",
                beforeSend: function (x) {
                    if (x && x.overrideMimeType) {
                        x.overrideMimeType("application/j-son;charset=UTF-8");
                    }
                },
                success: function (data, textStatus) {
                    if (data) {
                        document.getElementById("commentUserList").options.length = 0;
                        userCommentsList = data;
                        fillComment(data);
                    }
                }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("Error in getting comment.");
                }, complete: function () {
                    closeLoader();
                }
            });
        }
    });

    $("#commentUserList").bind("change", function () {
        $("#commentDiv").empty();
        showCommentInformation(this);
    });

    $("#respond").click(function () {
        alert("click");
    });
});


function showCommentInformation(obj) {
    var userId = $("#commentUserList option:selected").val();
    for (var key in  userCommentsList) {
        if (key == userId) {
            var commentList = userCommentsList[key];
            for (var i = 1; i < commentList.length; i++) {
                $("#commentDiv").append("<div style='font-size: 14px'><span style='color:#FF9933'>Comments:</span>" + commentList[i] + "</div>");
             if(parseInt(i)==parseInt(commentList.length-1)){
                 $("#commentDiv").append("<div>&nbsp</div>");
               /**
                * open the comment when send message using youtube data api .
                * */
                /* $("#commentDiv").append("<div><input type='button' id='respond' class='respondSingle' name='respond' value='Respond'/><input  type='button' id='respondAll' class='respond_all' name='respondAll' value='Respond All'/></div>");*/

                 $("#commentDiv").append("<div><input type='button' id='sendMsg' class='sendMessage' name='sendMsg' value='Respond'/></div>");
             }
            }
        }
    }
}


function fillComment(data) {
    for (var comment in data) {
        /*alert('key is: ' + comment + ', value is: ' + data[comment]);*/
        /*alert(data[comment].length);*/
        var commentCount = parseInt(data[comment].length) - 1;
        $('#commentUserList').append('<option value="' + comment + '">' + data[comment][0] + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + commentCount + '</option>');
        $('#commentUserList').css('height: 4em');
    }
   sortList();
}
function sortList(){
    $("#commentUserList").html($('#commentUserList option').sort(function(x, y) {
        var lastThree = $.trim($(x).text().substr($(x).text().length - 3));
        var lastThree_2 = $.trim($(y).text().substr($(y).text().length - 3));
        return parseInt(lastThree) > parseInt(lastThree_2) ? -1 : 1;
    }));
    closeLoader();
}


$(document).ready(function () {
    $(function () {
        $(document).on('click', '.respondSingle', function () {
            /*alert($("#commentUserList option:selected").val());
             * var agentName=$("#agencyid option:selected").text();
             * */
            $("#dialog-form").dialog("open");
            $("#dialog-form").dialog("option", "title", "Send message to " + ($("#commentUserList option:selected").text()).slice(0, -3));
        });

    });



    $(function () {
        $(document).on('click', '.sendMessage', function () {
            var url = $("#commentUserList option:selected").val();
            window.open(url,'_blank');
        });
    });
});

function showLoader(){
    $("#preview").removeClass('loading1');
     $("#preview").dialog("open");

}

function closeLoader(){
    $('#preview').dialog('close');
    $("#preview").addClass('loading1');
}