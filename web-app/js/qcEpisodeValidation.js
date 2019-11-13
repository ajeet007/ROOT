
/*
 Revision History:
 Developer Name  : Ajeet Singh
 Date            : 24-July-2014
 Reason          : 1. accept and reject Episode
 */
var flagForRefresh;
var qcListKey=1;
var $qc_productive_TCList = {};
var qc_trId=0;
var episode_actors;
var qc_episodeID=0;
var episodeIdList = [];
var currentIndex = 0, version = 0;
var qc_rejection;
var updateMode=false;

$(document).ready(function () {
    $("#allocation_details tr").click(function () {
        var episodeId = $(this).attr("id");
        $("#episodeDetail").dialog({
            modal: true,
            backgroundColor: "blue",
            position: 'center',
            width: 955,
            height:620,
            autoResize: true,
            buttons: {
                Close: function () {
                    $(this).dialog("close");
                }
            },
            close: function () {
                if (flagForRefresh == 1) {
                    window.location = window.location;
                }
            }
        });

         episodeInformation(episodeId);

    });
});

function episodeDetails1(episodeId){
    jQuery.ajax(
        {
            type: 'POST',
            url: g.createLink({controller: 'dashboard', action: 'episodeDetail'}),
            data: "episodeId=" + episodeId,
            success: function (data, textStatus) {
                if (data) {
                    fillEpisodeDetails(data);
                }
            }, error: function (XMLHttpRequest, textStatus, errorThrown) {

        }
        });
}

function episodeInformation(episodeId) {
    var tempEpisodeId = 0;
    for (var i = 0; i < episodeIdList.length; i++) {
        if (episodeIdList[i] == episodeId) {
            tempEpisodeId = episodeIdList[i];
            currentIndex = ++i;
            episodeDetails1(tempEpisodeId);
        }
    }
    if (currentIndex == i) {
        $("#episodeDetail").dialog("close");
    }
}


function fillEpisodeDetails(data){
   $("#episode_number").val(data[0].episodeNumber);
   $("#clear_id").val(data[0].clearID);
   $("#or_language").val(data[0].originalLanguage);
   $("#su_language").val(data[0].subtitleLanguage);
   $("#du_language").val(data[0].dubbingLanguage);
   $("#episode_name").val(data[0].episodeName);
   $("#episode_keywords").val(data[0].keywords);
   $("#episode_description").val(data[0].description);

    if(data[4].rejectionType){
        $("#rejectionReason").val(data[4].rejectionType);
        $("#remarks").val(data[4].remarks);
    }else{
        $("#remarks").val('');
        $("#rejectionReason").find("option:selected").attr("selected", false);

    }
    $("#episodeDetail").dialog('option', 'title', 'Episode Number :' + data[0].episodeNumber + "," + " QC By :" +data[5]+
        '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
        '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
        '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
        '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
        '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
        '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
        '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + currentIndex + " of " + episodeIdList.length);

    var tcData=data[1];
    episode_actors=data[2];
    qc_episodeID=data[3];
    qc_rejection=data[4];
    $("table#timeCodedList tr").remove();
    $('#timeCodedList').append('<tr> <td style="width: 3%"></td><td style="width: 7%"><b>MediaType</b></td><td style="width: 8%"><b>TC IN</b></td><td style="width: 8%"><b>TC OUT</b></td><td style="width: 8%"><b>Title</b></td><td style="width: 8%"><b>Actors</b></td><td style="width: 8%"><b>Keywords</b></td><td style="width:10% "><b>Description</b></td></tr>');

    for (var t = 0; t <= tcData.length; t++) {
        $('#timeCodedList').append('<tr  id="' + qcListKey + '"><td><input type="button" class="tc_list" value="edit" > </td><td>' + tcData[t].mediaTypeName + '</td><td>' + tcData[t].in_Time_Counter + ":" + tcData[t].in_Time_MM + ":" + tcData[t].in_Time_SS + ":" + tcData[t].in_Time_FRMS + '</td><td>' + tcData[t].out_Time_Counter + ":" + tcData[t].out_Time_MM + ":" + tcData[t].out_Time_SS + ":" + tcData[t].out_Time_FRMS + '</td><td>' + tcData[t].metaData_title + '</td><td>' + tcData[t].actors + '</td><td>' + tcData[t].keywords + '</td><td>' + tcData[t].description + '</td></tr>');
        var $qc_productive_TCArray = [];
        var actorsArray = [];
        actorsArray = (tcData[t].actors).split(",");
        $qc_productive_TCArray.push(actorsArray);
        $qc_productive_TCArray.push(tcData[t].in_Time_Counter);
        $qc_productive_TCArray.push(tcData[t].in_Time_MM);
        $qc_productive_TCArray.push(tcData[t].in_Time_SS);
        $qc_productive_TCArray.push(tcData[t].in_Time_FRMS);
        $qc_productive_TCArray.push(tcData[t].out_Time_Counter);
        $qc_productive_TCArray.push(tcData[t].out_Time_MM);
        $qc_productive_TCArray.push(tcData[t].out_Time_SS);
        $qc_productive_TCArray.push(tcData[t].out_Time_FRMS);
        $qc_productive_TCArray.push(tcData[t].metaData_title);
        $qc_productive_TCArray.push(tcData[t].keywords);
        $qc_productive_TCArray.push(tcData[t].description);
        $qc_productive_TCArray.push(tcData[t].mediaType);
        $qc_productive_TCList[qcListKey] = $qc_productive_TCArray;
        qcListKey = parseInt(qcListKey) + 1;
    }
}

$("#timeCodedList td:nth-child(1)").live('click', function () {

    $("#tc_table").css("display", "none");
    $("#tc_form").css("display", "block");
    qc_trId = $(this).closest('tr').attr('id');
    var $td = $(this).closest('tr').children('td');
    var tc_radio = $td.eq(0).text();
    var mediaType = $td.eq(1).text();
    var tc_IN = $td.eq(2).text();
    var tc_OUT = $td.eq(3).text();
    var tc_title = $td.eq(4).text();
    var tc_actors = $td.eq(5).text();

    var actorsArray = [];
     actorsArray = tc_actors.split(",");
    var tc_keywords = $td.eq(6).text();
    var tc_desc = $td.eq(7).text();
    var in_array = tc_IN.split(":");
    var out_array = tc_OUT.split(":");
    var in_c;
    var in_mm;
    var in_ss;
    var in_frms;
    var out_c;
    var out_mm;
    var out_ss;
    var out_frms;
    for (var t = 0; t <= in_array.length; t++) {
        if (t == 0) {
            in_c = in_array[t];
        }
        if (t == 1) {
            in_mm = in_array[t];
        }
        if (t == 2) {
            in_ss = in_array[t];
        }
        if (t == 3) {
            in_frms = in_array[t]
        }
    }
    for (var u = 0; u <= out_array.length; u++) {
        if (u == 0) {
            out_c = out_array[u];
        }
        if (u == 1) {
            out_mm = out_array[u];
        }
        if (u == 2) {
            out_ss = out_array[u];
        }
        if (u == 3) {
            out_frms = out_array[u]
        }
    }
    $("#mediaList option:contains(" + mediaType + ")").attr('selected', 'selected');
    $('#in_counter').val(in_c);
    $('#in_mm').val(in_mm);
    $('#in_ss').val(in_ss);
    $('#in_frames').val(in_frms);
    $('#out_counter').val(out_c);
    $('#out_mm').val(out_mm);
    $('#out_ss').val(out_ss);
    $('#out_frames').val(out_frms);
    $('#meta_title').val(tc_title);
    $('#tc_keywords').val(tc_keywords);
    $('#tc_desc').val(tc_desc);

    $("#actorsList").empty();
    for (var c = 0; c < episode_actors.length; c++)
    {
      $('#actorsList').append('<option value="' + episode_actors[c].id + '"selected>' + episode_actors[c].artistName + '</option>');
    }
    $("#actorsList").find("option:selected").attr("selected", false);
});

$("#qc_clear").live('click', function () {

    $("#tc_form").css("display", "none");
    $("#tc_table").css("display", "block");
});

$("#qc_update").live('click', function () {
    var tc_actors = [];
    var tc_actors_val = [];
    jQuery("#actorsList option:selected").each(function () {
        tc_actors.push($(this).text());
        tc_actors_val.push($(this).val());
    });
    var $qc_productive_TCArray = [];
    $qc_productive_TCArray.push(tc_actors);
    $qc_productive_TCArray.push($('#in_counter').val());
    $qc_productive_TCArray.push($('#in_mm').val());
    $qc_productive_TCArray.push($('#in_ss').val());
    $qc_productive_TCArray.push($('#in_frames').val());
    $qc_productive_TCArray.push($('#out_counter').val());
    $qc_productive_TCArray.push($('#out_mm').val());
    $qc_productive_TCArray.push($('#out_ss').val());
    $qc_productive_TCArray.push($('#out_frames').val());
    $qc_productive_TCArray.push($("#meta_title").val());
    $qc_productive_TCArray.push($("#tc_keywords").val());
    $qc_productive_TCArray.push($("#tc_desc").val());
    $qc_productive_TCArray.push($("#mediaList option:selected").val());

    $("table#timeCodedList tr[id='" + qc_trId + "']").remove();
    $('#timeCodedList').append('<tr  id="' + qc_trId + '"><td><input type="button" class="tc_list" value="edit" > </td><td>' + $("#mediaList option:selected").text() + '</td><td>' + $('#in_counter').val() + ":" + $('#in_mm').val() + ":" + $('#in_ss').val() + ":" + $('#in_frames').val() + '</td><td>' + $('#out_counter').val() + ":" + $('#out_mm').val() + ":" + $('#out_ss').val() + ":" + $('#out_frames').val() + '</td><td>' + $("#meta_title").val() + '</td><td>' + tc_actors + '</td><td>' + $("#tc_keywords").val() + '</td><td>' + $("#tc_desc").val() + '</td></tr>');
    $qc_productive_TCList[qc_trId] = $qc_productive_TCArray;
    alert("Time coded data updated.");
    $("#tc_form").css("display", "none");
    $("#tc_table").css("display", "block");
});


$(document).ready(function () {
$("#accept").click(function () {
    flagForRefresh = 1;
    var in_counter=$("#in_counter").val();
    var counterValidation = /(^[0-9]{1}$|^[1]{1}[0]{1}$|^10$)/gm;

/*    if(counterValidation.test(in_counter)) {
        alert("valid counter");
    }
    else{
        alert("counter fail");
    }*/
    var secondsValidation = /(^[0-9]{1}$|^[1-5]{1}[0-9]{1}$|^60$)/gm;
    var in_ss=$("#in_ss").val();
/*    if(secondsValidation.test(in_ss)) {
        alert("valid seconds");
    }
    else{
        alert("seconds fail");
    }*/
    var qc_episodeName=$("#episode_name").val();
    var qc_keywords=$("#episode_keywords").val();
    var qc_description=$("#episode_description").val();
    var qc_originalLanguage=$("#or_language option:selected").val();
    var qc_subtitleLanguage= $("#su_language option:selected").val();
    var qc_dubbingLanguage= $("#du_language option:selected").val();
    var qc_approvedType= $("#approved_type option:selected").val();
    var qc_rejectionReason= $("#rejectionReason option:selected").val();
    var qc_episodeNumber=$("#episode_number").val();
    var qc_clear_Id=$("#clear_id").val();
    var qc_remarks=$("#remarks").val();
    var newEpisodeId = episodeIdList[currentIndex - 1];
    updateMode=true;
    if(qc_approvedType==""){
        alert("Select quality.");
        return false;
    }
    else if(qc_episodeNumber!=""){
        jQuery.ajax
        ({
            type:'POST',
            url:g.createLink({controller: 'addEpisode', action: 'updateEpisode'}),
            data:{episodeId:newEpisodeId,clearId:qc_clear_Id,episodeName:qc_episodeName,description:qc_description,keywords:qc_keywords,originalLanguage:qc_originalLanguage,subtitleLanguage:qc_subtitleLanguage,dubbingLanguage:qc_dubbingLanguage,approvedType:qc_approvedType,updateMode:updateMode,timeCodedList:JSON.stringify($qc_productive_TCList)},
            dataType: "json",
            success:function(data)
            {
                alert( "Episode number  " +"'"+qc_episodeNumber+"'"+"  Approved.");
                qc_trId=0;
                $("#skip").click();
            }

            ,error:function(XMLHttpRequest, textStatus, errorThrown) {
                alert("Error in QC Approved. contact system Admin.");
            }
        });
    }
    else{
      alert("Episode number not empty.");
    }


});
$('#reject').click(function () {
    flagForRefresh = 1;
    var qc_rejectionReason= $("#rejectionReason option:selected").val();
    var qc_remarks=$("#remarks").val();
    var statusValid = validate();
    var newEpisodeId = episodeIdList[currentIndex - 1];
    if (statusValid) {
        jQuery.ajax(
            {
                type: 'POST',
                url: g.createLink({controller: 'dashboard', action: 'rejectEpisode'}),
                data: "episodeID=" + newEpisodeId + "&rejectionReason=" + qc_rejectionReason+ "&remarks=" + qc_remarks,
                success: function (data, textStatus) {
/*                    if (data.flag == 1) {
                        $("#skip").click();

                    }*/
                    if(data){
                        $("#skip").click();
                    }
/*                    if (data.flag != 1) {
                        alert("Cannot complete the process. This booking is being processed by another user.");
                        $("#skip").click();
                    }*/
                }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("Error in rejection process contact system Admin.");
            }


            });
    }
});
    $("#skip").click(function () {
        var newBookingId = episodeIdList[currentIndex];
        episodeInformation(newBookingId);
        $("#approved_type").find("option:selected").attr("selected", false);
    });
});

function validate() {
    var status = null;
    if ($('#rejectionReason option').length > 0) {
        if ($('#rejectionReason').val() == '') {

            $('#rejectionReasonStatus').html('<font color ="red">Select Reason for Rejection.</font>')
            status = false;
            return status;
        }
        else {
            $('#rejectionReasonStatus').html('');
            status = true;
            return status;
        }

    }
}


