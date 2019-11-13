
/*
 Revision History:
 Developer Name  : Ajeet Singh
 Date            : 11-Aug-2014
 Reason          : 1. Modified to add Article
 */
var executiveList={};
var $productive_TCList = {};
var updateMode=false;
var trId=0;
var tcListKey=1;
var gridRefresh=false;
var projectCodeID="";
var projectListMap;
$(document).ready(function(){

    $("#articleList").find("option:selected").attr("selected",false);
    $("#clear").click(function()
    {
        $("#articleList").find("option:selected").attr("selected",false);
        $("#showlist option").remove();
        $("#seasonlist option").remove();
        $('#showlist').append('<option value="">-Select Project-</option>');
        return false;
    });

});


$(document).ready(function()
{
    $("#articleList").bind('change',function()
    {
        var articleID=$("#articleList option:selected").val();
        jQuery.ajax
        ({
            type:'POST',
            url:g.createLink({controller: 'addProject', action: 'clientProjectInformation'}),
            data:"clientID="+articleID,
            success:function(data,textStatus)
            {
                $("#projectList option").remove();
                $('#projectList').append('<option value="">-Select Project-</option>');
                projectListMap=data;
                fillProject(data);
            },
            error:function(XMLHttpRequest, textStatus, errorThrown) {
            }
        });
    });

    $("#projectList").bind('change',function()
    {
        var showID=$("#projectList option:selected").val();
        for (var i=0;i<projectListMap.length;i++)
        {
            if(projectListMap[i].id==showID){
                projectCodeID=projectListMap[i].projectCode;

            }
        }
        jQuery.ajax
        ({
            type: 'POST',
            url: g.createLink({controller: 'addArticle', action: 'getContentWriterInfo'}),
            data: "projectId=" +$("#projectList option:selected").val(),
            success: function (data, textStatus) {
                executiveList = data;
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            }
        });
    });
});

function fillProject(data)
{
    for (var i=0;i<data.length;i++)
    {
        $('#projectList').append('<option value="' + data[i].id+ '">' + data[i].projectName + '</option>');
    }
}


function isNumberKey(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode;
    if(charCode > 31 && (charCode < 48 || charCode > 57)){
        return false;
    }
    else{
        return true;
    }
}

$(document).ready(function () {

    var enterNumberRow = false;
    var rangeNumberRow = false;
    $("#single_article").click(function () {
        if($("#articleList option:selected").val()==""){
            alert("please select client.");
            return false;
        }
        if($("#projectList option:selected").val()==""){
            alert("please select project.");
            return false;
        }

        if (enterNumberRow) {
            $("#uploadField").val("");
            $("#articleDetails").empty();
            $("#articleDetails").width("100%");
            $("#articleDetails").append('<tr><td width="105">Article Number</td><td width="105"><input type="text" id="article_number" name="article_number" onkeypress="return isNumberKey(event)" value="" /></td><td><input type="button" class="article" id="add_episode" name="add_episode" value="ADD"/></td></tr>');
        }
        else {
            $("#articleDetails").append('<tr><td width="105">Article Number</td><td width="105"><input type="text" id="article_number" name="article_number" onkeypress="return isNumberKey(event)" value="" /></td><td><input type="button" class="article" id="add_episode" name="add_episode" value="ADD"/></td></tr>');
            enterNumberRow = true;
            rangeNumberRow = true;
        }
        $("#article_number").focus();
    });
    $("#bulk_article").click(function () {
        if($("#articleList option:selected").val()==""){
            alert("please select client.");
            return false;
        }
        if($("#projectList option:selected").val()==""){
            alert("please select project.");
            return false;
        }
        $("#articleDetails").empty();
/*        jQuery.ajax
        ({
            type: 'POST',
            url: g.createLink({controller: 'addArticle', action: 'getContentWriterInfo'}),
            data: "projectId=" +$("#projectList option:selected").val(),
            success: function (data, textStatus) {
                executiveList = data;
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            }
        });*/
        $("#uploadField").val("");
        if (rangeNumberRow) {
            $("#articleDetails").empty();
            $("#articleDetails").width("100%");
            $("#articleDetails").append('<tr><td width="10">Article</td><td width="490px"><input type="text" id="article_number1" name="article_number1"  value="" />TO<input type="text" id="article_number2" name="article_number2" onkeypress="return isNumberKey(event)"  value="" /><td><input type="button" class="BulkArticle" id="add_bulk" name="add_bulk" value="ADD"/></td></tr>');
        }
        else {
            $("#articleDetails").append('<tr><td width="10">Article</td><td width="490px"><input type="text" id="article_number1" name="article_number1"    value="" />TO<input type="text" id="article_number2" name="article_number2" onkeypress="return isNumberKey(event)"  value="" /><td><input type="button" class="BulkArticle" id="add_bulk" name="add_bulk" value="ADD"/></td></tr>');
            enterNumberRow = true;
            rangeNumberRow = true;
        }
        $("#article_number1").focus();
    });
    $("#articleDetails").on('click', '.article', function () {
        var clientlist = $("#articleList option:selected").text();
        var projectName = $("#projectList option:selected").text();
        var articleNumber = $("#article_number").val();
        if(articleNumber==""){
            alert("Enter article number.");
            return false;
        }
        $("#uploadField").val("");
        $("#articleDetails").empty();
        $("#articleDetails").width("100%");
        $("#articleDetails").append('<tr><td><b>Article ID</b></td><td><b>Title</b></td><td><b>Received Date</b></td><td><b>Due Date</b></td><td></td><td></td></tr>');
        $("#articleDetails").append('<tr id="articleData"><td><input disabled="disabled" size="5" type="text"  name="article_Id" readonly="readonly" id="articleId_'+articleNumber+'" value="'+projectCodeID+articleNumber+'"/></td><td><input type="text" size="5" name="articleTitle" oninput="this.title = this.value"  id="articleTitle_'+articleNumber+'"/> </td><td> <input type="text" size="4" readonly="readonly" name="recDate" value="" onclick="callCalendar(this)" id="recDate_'+articleNumber+'"/></td><td><input type="text"  size="4" readonly="readonly" name="dueDate" value="" onclick="callCalendar(this)" id="dueDate_'+articleNumber+'"/></td><td><select id="executiveList_'+articleNumber+'"></select></td><td><input type="button" class="bulkAllocate" value="Allocate"  id='+articleNumber+'></td></tr>');
        jQuery.ajax
        ({
            type: 'POST',
            url: g.createLink({controller: 'addArticle', action: 'getContentWriterInfo'}),
            data: "projectId=" +$("#projectList option:selected").val(),
            success: function (data, textStatus) {
                $("#executiveList option").remove();
                $('#executiveList_'+ articleNumber).append('<option value="">-Select Writer-</option>');
                for (var i = 0; i < data.length; i++) {
                    $('#executiveList_'+articleNumber).append('<option value="' + data[i].id + '">' + data[i].executiveName + '</option>');
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            }
        });
    });
    $("#articleDetails").on('click', '.BulkArticle', function () {
        var clientlist = $("#articleList option:selected").text();
        var showName = $("#showlist option:selected").text();
        var seasonName = $("#seasonlist option:selected").text();
        var episodeNumberR1 = $("#article_number1").val();
        var episodeNumberR2 = $("#article_number2").val();
        if(episodeNumberR1=="" || episodeNumberR2==""){
            alert("Enter valid range.");
            return false;
        }
        $("#uploadField").val("");
        $("#articleDetails").empty();
        $("#articleDetails").width("100%");
        $("#articleDetails").append('<tr><td><b>Article ID</b></td><td><b>Title</b></td><td><b>Received Date</b></td><td><b>Due Date</b></td><td></td><td></td></tr>');
        var i = 0;
        for (i = parseInt(episodeNumberR1); i <= parseInt(episodeNumberR2); i++) {
            $("#articleDetails").append('<tr id="articleData"><td><input size="5" type="text" disabled="disabled"  name="article_Id" readonly="readonly" value="'+projectCodeID+i+'" id="articleId_'+i+'"/></td><td><input type="text" size="5"  name="articleTitle" oninput="this.title = this.value" id="articleTitle_'+i+'"  /> </td><td> <input type="text" size="4" readonly="readonly"  value="" onclick="callCalendar(this)" id="recDate_'+i+'"/></td><td id="episodeNumberData"><input type="text"  size="4" readonly="readonly"  value="" onclick="callCalendar(this)" id="dueDate_'+i+'" /></td><td><select id="executiveList_'+i+'"></select></td><td><input type="button" class="bulkAllocate" value="Allocate"  id='+i+'></td></tr>');
            $("#executiveList option").remove();
            $("#executiveList_" + i).append('<option value="">-Select Writer-</option>');
            for (var j = 0; j < executiveList.length; j++) {
                $('#executiveList_' + i).append('<option value="' + executiveList[j].id + '">' + executiveList[j].executiveName + '</option>');
            }
        }
    });
    $('.bulkAllocate').live('click', function () {
        var articleNumber = this.id;
        var executiveListID="executiveList_"+articleNumber;
        var getArticleId="articleId_"+articleNumber;
        var getArticleTitle="articleTitle_"+articleNumber;
        var getRecDate="recDate_"+articleNumber;
        var getDueDate="dueDate_"+articleNumber;

        var executiveId = document.getElementById(executiveListID).value;
        var articleId = document.getElementById(getArticleId).value;
        var articleTitle = document.getElementById(getArticleTitle).value;
        var recDate = document.getElementById(getRecDate).value;
        var dueDate = document.getElementById(getDueDate).value;
        if(articleTitle==""){
            alert("Article title not empty.");
            return false;
        }
        if(recDate==""){
            alert("Select received date.");
            return false;
        }
        if(dueDate==""){
            alert("Select due date.");
            return false;
        }
        if(executiveId==""){
            alert("Select writer to allocate article.");
            return false;
        }
        else{
            var clientId=$("#articleList option:selected").val();
            var projectId=$("#projectList option:selected").val();
            var statusColor=false;

            jQuery.ajax
            ({
                type:'POST',
                url:g.createLink({controller: 'addProject', action: 'allocateArticle'}),
                data: "clientId=" + clientId +'&projectId='+projectId+'&articleNumber='+articleNumber+'&executiveId='+executiveId+'&articleId='+articleId+'&articleTitle='+articleTitle+'&recDate='+recDate+'&dueDate='+dueDate,
                async:    false,
                success:function(data, textStatus)
                {
                    if(data.articleInfo){
                        alert("Sorry, Article "+"'"+data.articleInfo+"' " +"already assign to "+data.writerInfo);

                    }
                    else{
                        alert("allocated");
                        statusColor=true;

                    }


                }
                ,error:function(XMLHttpRequest, textStatus, errorThrown) {
                }
            });
            if(statusColor==true){
                $(this).css('color', '#31B404');
            }

        }

    });

    $('#uploadField').live('click', function () {
        $("#articleDetails").empty();
        if($("#articleList option:selected").val()==""){
            alert("please select client.");
            return false;
        }
        if($("#projectList option:selected").val()==""){
            alert("please select project.");
            return false;
        }
    });
    $('.clickClass').live('click', function () {
        var fileInput = $("#uploadField")[0];
        var valid_extensions = /(\.csv)$/i;
        if (valid_extensions.test($("#uploadField").val()) && fileInput.files[0].size <= 1048576) {
            var oData = new FormData(document.forms.namedItem("fileinfo"));
            $.ajax({
                url: g.createLink({controller: 'addProject', action: 'fileArticle'}),
                type: 'POST',
                data: oData,
                processData: false,
                contentType: false,
                success: function (data) {
                    $("#articleDetails").empty();
                    $("#articleDetails").width("100%");
                    $("#articleDetails").append('<tr><td><b>Article ID</b></td><td><b>Title</b></td><td><b>Received Date</b></td><td><b>Due Date</b></td><td></td><td></td></tr>');
                    for (var key in data) {
                        var articleData = data[key];
                        $("#articleDetails").append('<tr id="articleData"><td><input size="5" type="text" disabled="disabled"  readonly="readonly" value="' + key + '" id="articleId_' + articleData[0] + '"/></td><td><input type="text" size="5"  name="articleTitle" oninput="this.title = this.value" value="' + articleData[1] + '" id="articleTitle_' + articleData[0] + '" /> </td><td> <input type="text" size="4" readonly="readonly" value="' + articleData[2] + '" onclick="callCalendar(this)" id="recDate_' + articleData[0] + '"/></td><td id="episodeNumberData"><input type="text"  size="4" readonly="readonly"  value="' + articleData[3] + '"  onclick="callCalendar(this)" id="dueDate_' + articleData[0] + '" /></td><td><select id="executiveList_' + articleData[0] + '"></select></td><td><input type="button" class="fileAllocate" value="Allocate"  id=' + articleData[0] + '></td></tr>');
                        $("#executiveList option").remove();
                        $("#executiveList_" + articleData[0]).append('<option value="">-Select Writer-</option>');
                        for (var j = 0; j < executiveList.length; j++) {
                            $('#executiveList_' + articleData[0]).append('<option value="' + executiveList[j].id + '">' + executiveList[j].executiveName + '</option>');
                        }
                        $("#executiveList_"+articleData[0]+" option:contains(" + articleData[4] + ")").attr('selected', 'selected');
                        $('.fileAllocate').click();
                    }
                }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("Error in file upload contact system admin ");
                }
            });
        }
        else {
            alert("File must be CSV, less than 1MB");
            $("#uploadField").val("");
            return false;
        }
    });

    $('.fileAllocate').live('click', function () {
        var articleNumber = this.id;
        var executiveListID="executiveList_"+articleNumber;
        var getArticleId="articleId_"+articleNumber;
        var getArticleTitle="articleTitle_"+articleNumber;
        var getRecDate="recDate_"+articleNumber;
        var getDueDate="dueDate_"+articleNumber;

        var executiveId = document.getElementById(executiveListID).value;
        var articleId = document.getElementById(getArticleId).value;
        var articleTitle = document.getElementById(getArticleTitle).value;
        var recDate = document.getElementById(getRecDate).value;
        var dueDate = document.getElementById(getDueDate).value;
        if(articleTitle!="" && recDate!="" && dueDate!="" && executiveId!="") {
            var clientId=$("#articleList option:selected").val();
            var projectId=$("#projectList option:selected").val();
            var statusColor=false;
            jQuery.ajax
            ({
                type:'POST',
                url:g.createLink({controller: 'addProject', action: 'allocateArticle'}),
                data: "clientId=" + clientId +'&projectId='+projectId+'&articleNumber='+articleNumber+'&executiveId='+executiveId+'&articleId='+articleId+'&articleTitle='+articleTitle+'&recDate='+recDate+'&dueDate='+dueDate,
                async:    false,
                success:function(data, textStatus)
                {
                    if(data.articleInfo){
                        statusColor=false;

                    }
                    else{
                        statusColor=true;

                    }


                }
                ,error:function(XMLHttpRequest, textStatus, errorThrown) {
                }
            });
            if(statusColor==true){
                $(this).css('color', '#31B404');
            }

        }

    });


});

function callCalendar(obj)
{
    $(obj).datepicker({dateFormat:'dd-mm-yy'}).datepicker( "show");
}

$(document).ready(function () {

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

    // This must be a hyperlink
    $(".downloadClass").on('click', function (event) {
        // CSV
        exportTableToCSV.apply(this, [$('table#tblTemplate'), 'template.csv']);

        // IF CSV, don't do event.preventDefault() or return false
        // We actually need this to be a typical hyperlink
    });
});


