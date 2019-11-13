var channelName = 'BhaktiKiShakti';
var vidHeight = 400;
var vidWidth = 500;
var vidResults = 50;
var nextPage = '';
var prevPage = '';
$(document).ready(function(){
    $.get(
        "https://www.googleapis.com/youtube/v3/channels",{
            part:'contentDetails',
            forUsername:channelName,
            key:'AIzaSyCWuYRVnpWLB-eMjfdWft9q11iDoALwb7Q'
        },
        function(data){
            $.each(data.items,function(i,item){
                pid=item.contentDetails.relatedPlaylists.uploads;
                getVids(pid);

            })
        }
    );
    function getVids(pid){
        $.get(
            "https://www.googleapis.com/youtube/v3/playlistItems",{
                part:'snippet',
                maxResults:vidResults,
                playlistId:pid,
                pageToken:nextPage,
                key:'AIzaSyCWuYRVnpWLB-eMjfdWft9q11iDoALwb7Q'
            },
            function(data){
                var output;
                 nextPage = data.nextPageToken;
                 prevPage = data.prevPageToken;
                $.each(data.items,function(i,item){
                    videTitle = item.snippet.title;
                    videoId = item.snippet.resourceId.videoId;
                    //output = '<li>'+videTitle+'</li>' /**videos title **/
                    console.log(videoId);
                    output = '<li><iframe height="'+vidHeight+'" width="'+vidWidth+'" src=\"//www.youtube.com/embed/'+videoId+'"></iframe></li>'
                    $('#results').append(output);

                })
                if(nextPage!=undefined){
                    getVids(pid);
                }

            }
        );
    }
});