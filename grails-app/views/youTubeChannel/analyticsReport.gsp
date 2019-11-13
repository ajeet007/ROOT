
<%@ page import="java.text.SimpleDateFormat; com.tan.Role; com.tan.User;com.tan.TblYoutubeChannel" contentType="text/html;charset=UTF-8" %>
<% response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
%>
<html xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
<head><title>YouTube</title>
    <g:urlMappings />
    <resource:autoComplete skin="default"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name='layout' content='main'/>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'chart.js')}"></script>
    %{--    <script type="text/javascript" src="${resource(dir: 'js', file: 'jquery-ui.min.js')}"></script>
        <link rel="stylesheet" href="${resource(dir:'css',file:'jquery-ui.css')}" />--}%
    %{-- <link rel="stylesheet" href="${resource(dir:'css',file:'jquery-ui-1.8.15.custom.css')}" />--}%


    <script type="text/javascript" src="https://www.google.com/jsapi?autoload={'modules':[{'name':'visualization','version':'1','packages':['corechart']}]}"></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript" src="https://canvg.googlecode.com/svn/trunk/rgbcolor.js"></script>
    <script type="text/javascript" src="https://canvg.googlecode.com/svn-history/r157/trunk/canvg.js"></script>

    <style>
    .validateTips {
        border: 1px solid transparent;
        padding: 0.3em;
    }
    </style>
</head>

<body>
<g:render template="cmsChannel"/>
<% SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); %>
<script type="text/javascript">
    var dataArray = [];
    var likeArray = [];
    var disLikeArray = [];
    var commentsArray = [];
    var favoritesAddedArray = [];
    var sharesArray = [];
    var subscriberGAINEDArray = [];
    var subscriberLOSTArray = [];
    var watchTimeArray = [];

    google.load('visualization', '1', {packages: ['corechart']});
    google.setOnLoadCallback(drawChart);

    function drawChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'X');
        data.addColumn('number', 'Views');
        data.addColumn('number','organic Views');

        data.addRows(dataArray);

        var options = {
            width: 900,
            height: 400,
            hAxis: {
                title: 'Time'
            },
            vAxis: {

                title: 'Views'
            },
            ZAxis: {

                title: 'OrgViews'
            }
        };
        var chart = new google.visualization.LineChart(
                document.getElementById('ex0'));

        chart.draw(data, options);

    }

    function drawLikeChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'X');
        data.addColumn('number', 'Likes');
        data.addRows(likeArray);

        var options = {
            width: 900,
            height: 400,
            hAxis: {
                title: 'Time'
            },
            vAxis: {

                title: 'Likes'
            }
        };

        var chart = new google.visualization.LineChart(
                document.getElementById('ex0'));

        chart.draw(data, options);

    }

    function drawDisLikeChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'X');
        data.addColumn('number', 'Dislikes');
        data.addRows(disLikeArray);

        var options = {
            width: 900,
            height: 400,
            hAxis: {
                title: 'Time'
            },
            vAxis: {

                title: 'Dislikes'
            }
        };

        var chart = new google.visualization.LineChart(
                document.getElementById('ex0'));

        chart.draw(data, options);

    }

    function drawCommentChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'X');
        data.addColumn('number', 'Comments');
        data.addRows(commentsArray);

        var options = {
            width: 900,
            height: 400,
            hAxis: {
                title: 'Time'
            },
            vAxis: {

                title: 'Comments'
            }
        };

        var chart = new google.visualization.LineChart(
                document.getElementById('ex0'));

        chart.draw(data, options);

    }


    function drawFavoritesAddedChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'X');
        data.addColumn('number', 'Favorites added');
        data.addRows(favoritesAddedArray);

        var options = {
            width: 900,
            height: 400,
            hAxis: {
                title: 'Time'
            },
            vAxis: {

                title: 'Favorites added'
            }
        };

        var chart = new google.visualization.LineChart(
                document.getElementById('ex0'));

        chart.draw(data, options);

    }

    function drawSharesChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'X');
        data.addColumn('number', 'Shares');
        data.addRows(sharesArray);

        var options = {
            width: 900,
            height: 400,
            hAxis: {
                title: 'Time'
            },
            vAxis: {

                title: 'Shares'
            }
        };

        var chart = new google.visualization.LineChart(
                document.getElementById('ex0'));

        chart.draw(data, options);

    }


    function drawSubscriberGNChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'X');
        data.addColumn('number', 'Subscribers gained');
        data.addRows(subscriberGAINEDArray);

        var options = {
            width: 900,
            height: 400,
            hAxis: {
                title: 'Time'
            },
            vAxis: {

                title: 'Subscribers gained'
            }
        };

        var chart = new google.visualization.LineChart(
                document.getElementById('ex0'));

        chart.draw(data, options);

    }

    function drawSubscriberLOSTChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'X');
        data.addColumn('number', 'Subscribers lost');
        data.addRows(subscriberLOSTArray);

        var options = {
            width: 900,
            height: 400,
            hAxis: {
                title: 'Time'
            },
            vAxis: {

                title: 'Subscribers lost'
            }
        };

        var chart = new google.visualization.LineChart(
                document.getElementById('ex0'));

        chart.draw(data, options);

    }

    function drawWatchTimeArrayChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'X');
        data.addColumn('number', 'Estimated minutes watched');
        data.addColumn('number','organic Estimated minutes watched');
        data.addRows(watchTimeArray);

        var options = {
            width: 900,
            height: 400,
            hAxis: {
                title: 'Time'
            },
            vAxis: {

                title: 'Estimated minutes watched'
            },
            ZAxis: {

                title: 'OrgViews Estimated minutes watched'
            }
        };

        var chart = new google.visualization.LineChart(
                document.getElementById('ex0'));

        chart.draw(data, options);

    }


    $(document).ready(function(){
        $("#custom_range").click(function()
        {

            if($('input[name$="myAnalytics"]:checked').val()=="1")
            {
                if($('#startDate_chart').val()!="" && $('#endDate_chart').val()!='')
                {
                    $('input[name=comboCheckBox]:checked').each(function(i){
                        selectedChannel.push($(this).attr("value"));
                    });
                    jQuery.ajax
                    ({
                        type:'POST',
                        url:g.createLink({controller: 'youTubeChannel', action: 'graphViews'}),
                        data: "channel_id=" + $('#channel').val() +'&date_to='+ $('#startDate_chart').val() +'&date_from='+  $('#endDate_chart').val()+'&filterViews='+$('#filter_views').val(),
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
            if($('input[name$="myAnalytics"]:checked').val()=="2"){


            }

        });



        $("#custom_range").click(function()
        {
            if($('input[name$="myAnalytics"]:checked').val()=="2")
            {
                selectedChannel = [];
                if($('#startDate_chart').val()!="" && $('#endDate_chart').val()!="" && $('#cms').val()!="")
                {
                    /*$('input[name=comboCheckBox]:checked').each(function(i){
                        selectedChannel.push($(this).attr("value"));
                    });*/

                    jQuery.ajax
                    ({
                        type:'POST',
                        url:g.createLink({controller: 'youTubeCMS', action: 'cmsChannel'}),
                        data: "channel_list=" +$('#cms').val() +'&date_to='+ $('#startDate_chart').val() +'&date_from='+  $('#endDate_chart').val()+'&filterViews='+$('#filter_views').val(),
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
        });

        $("#filterAnalytics").change(function () {
            if($('option:selected', this).val()=="1"){
                drawChart();
            }else if($('option:selected', this).val()=="2"){
                drawLikeChart();
            }
            else if($('option:selected', this).val()=="3"){
                drawDisLikeChart();
            }
            else if($('option:selected', this).val()=="4"){
                drawCommentChart();
            }
            else if($('option:selected', this).val()=="7"){
                drawFavoritesAddedChart();
            }
            else if($('option:selected', this).val()=="6"){
                drawSharesChart();
            }
            else if($('option:selected', this).val()=="8"){
                drawSubscriberGNChart();
            }
            else if($('option:selected', this).val()=="9"){
                drawSubscriberLOSTChart();
            }
            else if($('option:selected', this).val()=="5"){
                drawWatchTimeArrayChart();
            }
        });
        $( "#exportLink").live( "click", function() {
            /***
             * save graph as image.
             * ***/

            /*  var chartArea = document.getElementById('ex0').getElementsByTagName('svg')[0].parentNode;
             var svg = chartArea.innerHTML;
             var doc = document.getElementById('ex0').ownerDocument;
             var canvas = doc.createElement('canvas');
             canvas.setAttribute('width', chartArea.offsetWidth);
             canvas.setAttribute('height', chartArea.offsetHeight);

             canvas.setAttribute(
             'style',
             'position: absolute; ' +
             'top: ' + (-chartArea.offsetHeight * 2) + 'px;' +
             'left: ' + (-chartArea.offsetWidth * 2) + 'px;');
             doc.body.appendChild(canvas);
             canvg(canvas, svg);
             var imgData = canvas.toDataURL("image/png");
             canvas.parentNode.removeChild(canvas);
             window.location = imgData.replace("image/png", "image/octet-stream");

             */
            $("#tblProduction tbody tr").remove();

            if($('#filterAnalytics').val()=="1"){
                $("#tblProduction tbody").append("<tr>" + "<td>Date</td>" + "<td>Views</td>" + "<td>Organic views</td>" + "</tr>");
                $.each(dataArray, function (index, value) {
                    $("#tblProduction tbody").append("<tr>" + "<td>" + value[0] + "</td>" + "<td>" + value[1] + "</td>" + "<td>" + value[2] + "</td>" + "</tr>");
                });
            }else if($('#filterAnalytics').val()=="2"){
                $("#tblProduction tbody").append("<tr>" + "<td>Date</td>" + "<td>Likes</td>" + "</tr>");
                $.each(likeArray, function (index, value) {
                    $("#tblProduction tbody").append("<tr>" + "<td>" + value[0] + "</td>" + "<td>" + value[1] + "</td>" + "</tr>");
                });
            }
            else if($('#filterAnalytics').val()=="3"){
                $("#tblProduction tbody").append("<tr>" + "<td>Date</td>" + "<td>Dislikes</td>" + "</tr>");
                $.each(disLikeArray, function (index, value) {
                    $("#tblProduction tbody").append("<tr>" + "<td>" + value[0] + "</td>" + "<td>" + value[1] + "</td>" + "</tr>");
                });
            }
            else if($('#filterAnalytics').val()=="4"){
                $("#tblProduction tbody").append("<tr>" + "<td>Date</td>" + "<td>Comments</td>" + "</tr>");
                $.each(commentsArray, function (index, value) {
                    $("#tblProduction tbody").append("<tr>" + "<td>" + value[0] + "</td>" + "<td>" + value[1] + "</td>" + "</tr>");
                });
            }
            else if($('#filterAnalytics').val()=="7"){
                $("#tblProduction tbody").append("<tr>" + "<td>Date</td>" + "<td>FavoritesAdded</td>" + "</tr>");
                $.each(favoritesAddedArray, function (index, value) {
                    $("#tblProduction tbody").append("<tr>" + "<td>" + value[0] + "</td>" + "<td>" + value[1] + "</td>"  + "</tr>");
                });
            }
            else if($('#filterAnalytics').val()=="6"){
                $("#tblProduction tbody").append("<tr>" + "<td>Date</td>" + "<td>Shares</td>" + "</tr>");
                $.each(sharesArray, function (index, value) {
                    $("#tblProduction tbody").append("<tr>" + "<td>" + value[0] + "</td>" + "<td>" + value[1] + "</td>" + "</tr>");
                });
            }
            else if($('#filterAnalytics').val()=="8"){
                $("#tblProduction tbody").append("<tr>" + "<td>Date</td>" + "<td>SubscriberGained</td>" + "</tr>");
                $.each(subscriberGAINEDArray, function (index, value) {
                    $("#tblProduction tbody").append("<tr>" + "<td>" + value[0] + "</td>" + "<td>" + value[1] + "</td>" + "</tr>");
                });
            }
            else if($('#filterAnalytics').val()=="9"){
                $("#tblProduction tbody").append("<tr>" + "<td>Date</td>" + "<td>SubscriberLost</td>" + "</tr>");
                $.each(subscriberLOSTArray, function (index, value) {
                    $("#tblProduction tbody").append("<tr>" + "<td>" + value[0] + "</td>" + "<td>" + value[1] + "</td>" + "</tr>");
                });
            }
            else if($('#filterAnalytics').val()=="5"){
                $("#tblProduction tbody").append("<tr>" + "<td>Date</td>" + "<td>Estimated minutes watched</td>" + "<td>Organic Estimated minutes watched</td>" + "</tr>");
                $.each(watchTimeArray, function (index, value) {
                    $("#tblProduction tbody").append("<tr>" + "<td>" + value[0] + "</td>" + "<td>" + value[1] + "</td>" + "<td>" + value[2] + "</td>" + "</tr>");
                });
            }


            exportTableToCSV.apply(this, [$('table#tblProduction'), 'production.csv']);

        });

        function exportTableToCSV($table, filename) {

            var $rows = $table.find('tr:has(td)'),

            // Temporary delimiter characters unlikely to be typed by keyboard
            // This is to avoid accidentally splitting the actual contents
                    tmpColDelim = String.fromCharCode(11), // vertical tab character
                    tmpRowDelim = String.fromCharCode(0), // null character

            // actual delimiter characters for CSV format
                    colDelim = '","',
                    rowDelim = '"\r\n"',

            // Grab text from table into CSV formatted string
                    csv = '"' + $rows.map(function (i, row) {
                        var $row = $(row),
                                $cols = $row.find('td');

                        return $cols.map(function (j, col) {
                            var $col = $(col),
                                    text = ($col.text()).trim();
                            return text.replace('"', '""'); // escape double quotes

                        }).get().join(tmpColDelim);

                    }).get().join(tmpRowDelim)
                            .split(tmpRowDelim).join(rowDelim)
                            .split(tmpColDelim).join(colDelim) + '"',

            // Data URI
                    csvData = 'data:application/csv;charset=utf-8,' + encodeURIComponent(csv);

            $(this)
                    .attr({
                        'download': filename,
                        'href': csvData,
                        'target': '_blank'
                    });

        }
    });
</script>

<div id="div1" style="padding: 0px; margin: 0px; height: 500px; font-size: 12px; font-weight: inherit; color: #FFFFFF; text-decoration: none; background-color: #5776AC">
    <div style="left: 15%;position: absolute;">
        %{--
                <g:form id="postComment" name="postComment" controller="youTubeChannel" action="sendMessage" >
                    <table>
                        <tr>
                            <td>
                                Sign in with your Google Account.
                            </td>
                        </tr>

                    </table>
                </g:form>--}%
        <g:radio id="myAnalytics" name="myAnalytics" value="1"/>Channel
        <g:radio id="myAnalytics" name="myAnalytics" value="2" />CMS
         <g:select name="channel" from="${TblYoutubeChannel.findAll()}" optionValue="channelName" optionKey="channelId" noSelection="['':'-Select Channel-']" style="width: 15%;"></g:select>
        <g:select id="cms" name="cms" from="${com.tan.TblYoutubeCMS.findAll()}" optionValue="cmsName" optionKey="contentOwnerID"  noSelection="['':'-Select CMS-']" style="width: 15%; display: none"></g:select>
       %{--for multiple channel selection --}%
       %{-- <g:select id="channel" name="channel" from=""  noSelection="['':'-Select Channel-']" style="width: 15%"></g:select>--}%

            %{-- for multiple channel selection --}%
        %{--<label id="cmsChannelList" style="width: 2%;"></label>--}%
        <input type="text" readonly="readonly" placeholder="From" id="startDate_chart" name="startDate_chart" value="" style="width:7%" onclick="callCalendar(this)"/>
        <input type="text" readonly="readonly" placeholder="To" id="endDate_chart" name="endDate_chart" value="" style="width:7%" onclick="callCalendar(this)"/>
        <select id="filter_views" name="filterViews" style="width: 8%">
            <option value=""></option>
            <option value="daily">Daily</option>
            <option value="weekly">Weekly</option>
            <option value="monthly" >Monthly</option>
        </select>
        <select id="filterAnalytics" name="filterAnalytics" style="width: 9%">
            <option value=""></option>
            <option value="1" selected="selected">Views</option>
            <option value="2">Likes</option>
            <option value="3" >Dislikes</option>
            <option value="4">Comments</option>
            <option value="5">Estimated minutes watched</option>
            <option value="6">Shares</option>
            <option value="7">Favorites added</option>
            <option value="8">Subscribers gained</option>
            <option value="9">Subscribers lost</option>
        </select>
        <input type="button" style="width: 5%" name="custom_range" id="custom_range" value="Apply"/>
        %{-- <input type="button"  name="save_report" id="save_report" value="Report">--}%
        <a id="exportLink"><input type="button" name="report" style="width: 12%" value="Down­load re­port"/></a>
        <div id="ex0"></div>
    </div>
</div>
<table id="tblProduction" style="visibility: hidden">
    <tr>
    </tr>
</table>

</body>
</html>

