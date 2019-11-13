
var selectedChannel=[];
$(document).ready(function(){


    $("input[name=myAnalytics]:radio").bind('change',function()
    {
        var analyticsId= $('input[name$="myAnalytics"]:checked').val();
        if(analyticsId=="1"){

            /*$("#channel").css("display", "block");
            $("#cms").css("display", "none");*/
            $("#channel").show();
            $("#cms").hide();
            /**for multiple channel selection
             * */
            /*$("#cmsChannelList").empty();
            $("#channel").prop("disabled", false);*/

        }else if(analyticsId=="2"){

           /* $("#channel").css("display", "none");
            $("#cms").css("display", "block");*/
            $("#channel").hide();
            $("#cms").show();
              /**for multiple channel selection
               * **/
            /*$("#channel").prop("disabled", true);
            $("#cmsChannelList").append("<img alt='Channel list' src='../images/nav_trend_steady.gif'/>");*/
        }

        /***
         * for multiple channel selection
         * **/
/*        jQuery.ajax
        ({
            type:'POST',
            url:g.createLink({controller: 'youTubeCMS', action: 'getChannel'}),
            data:"analyticsId="+analyticsId,
            success:function(data)
            {
                $("#channel option").remove();
                $("#tblChannel").empty();
                $('#channel').append('<option value="">-Select Channel-</option>');
                fillChannel(data);

            },
            error:function(XMLHttpRequest, textStatus, errorThrown) {
            }
        });*/
    });

    /***
     *  for multiple channel selection
     * ***/

    /*$("#cmsChannelList").click(function() {
        $("#dialog-form").dialog({
            modal: true,
            backgroundColor:"blue",
            title :"Channel list",
            height: 'auto',
            autoResize: true,
            position: [320,45],
            buttons: {
                Close: function() {
                    $( this ).dialog( "close");

                }
            }

        });

    });*/



  /*  $("#custom_range").click(function()
    {
        if($('input[name$="myAnalytics"]:checked').val()=="2")
        {
            selectedChannel = [];
            if($('#startDate_chart').val()!="" && $('#endDate_chart').val()!='')
            {
                $('input[name=comboCheckBox]:checked').each(function(i){
                    selectedChannel.push($(this).attr("value"));
                });

                jQuery.ajax
                ({
                    type:'POST',
                    url:g.createLink({controller: 'youTubeCMS', action: 'cmsChannel'}),
                    data: "channel_list=" + selectedChannel +'&date_to='+ $('#startDate_chart').val() +'&date_from='+  $('#endDate_chart').val()+'&filterViews='+$('#filter_views').val(),
                    success:function(data)
                    {
                        if(data)
                        {
                            dataArray=[];
                            likeArray=[];
                            disLikeArray=[];
                            commentsArray=[];
                            favoritesAddedArray=[];
                            sharesArray=[];
                            subscriberGAINEDArray=[];
                            subscriberLOSTArray=[];
                            watchTimeArray=[];
                            $.each(data, function(k, v) {
                                var orgView= (v.views - v.aDVERTISING);
                                dataArray.push([v.endDate,v.views,orgView]);
                                likeArray.push([v.endDate, v.likes]);
                                disLikeArray.push([v.endDate,v.disLikes]);
                                commentsArray.push([v.endDate,v.comments]);
                                favoritesAddedArray.push([v.endDate,v.favoritesAdded]);
                                sharesArray.push([v.endDate,v.shares]);
                                subscriberGAINEDArray.push([v.endDate,v.subscriberGAINED]);
                                subscriberLOSTArray.push([v.endDate,v.subscriberLOST]);
                                var orgWatchTime=(v.watchTime - v.aDVERTISING);
                                watchTimeArray.push([v.endDate,v.watchTime,orgWatchTime]);
                            });
                            drawChart();
                        }
                    }
                    ,error:function(XMLHttpRequest, textStatus, errorThrown) {
                        alert("error");
                    }
                });
            }
        }
    });*/


});

function fillChannel(data){


    for (var i=0;i<data.length;i++)
    {
        $('#channel').append('<option value="' + data[i].channelId+ '">' + data[i].channelName + '</option>');
        $('#tblChannel').append('<tr><td  ><input type="checkbox" name="comboCheckBox" checked="checked" ' +'id="' + data[i].channelId + '" value="' + data[i].channelId + '" /></td><td>' + data[i].channelName + '</td></tr>');

    }

}
function callCalendar(obj)
    {
        $(obj).datepicker({dateFormat:'dd-mm-yy'}).datepicker( "show");
    }
