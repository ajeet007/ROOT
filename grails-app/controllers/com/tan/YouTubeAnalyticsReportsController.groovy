package com.tan

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.GenericUrl
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.youtubeAnalytics.YouTubeAnalytics
import com.google.api.services.youtubeAnalytics.model.ResultTable
import grails.converters.JSON
import org.codehaus.groovy.grails.core.io.ResourceLocator
import tan.YoutubeAuthService

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import java.io.File
import java.text.DateFormat
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.java6.auth.oauth2.FileCredentialStore;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Activity;
import com.google.api.services.youtube.model.ActivityContentDetails;
import com.google.api.services.youtube.model.ActivitySnippet;
import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.ResourceId;
import com.google.common.collect.Lists;


class YouTubeAnalyticsReportsController {
    ResourceLocator grailsResourceLocator
    YoutubeAuthService youtubeAuthService

    /** Global instance of the HTTP transport. */
    private  final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    /** Global instance of the JSON factory. */
    private  final JsonFactory JSON_FACTORY = new JacksonFactory();

    /** Global instance of YouTube object to make all API requests. */
    private  YouTube youtube;

    private  YouTubeAnalytics analytics;


    /*
     * Global instance of the video id we want to post as a bulletin into our channel feed. You will
     * probably pull this from a search or your app.
     */
    private  String VIDEO_ID = "L-oNKK1CrnU";

    /**
     * Authorizes the installed application to access user's protected data.
     *
     * @param scopes list of scopes needed to run upload.
     */

    private Credential authorize(List<String> scopes) throws Exception {

        JsonFactory jsonFactory = JacksonFactory.defaultInstance
        File clientSecretsFile = grailsResourceLocator.findResourceForURI("./configs/client_secrets.json").file
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory, new InputStreamReader(new FileInputStream(clientSecretsFile)));
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        FileDataStoreFactory dataStoreFactory = new FileDataStoreFactory(new File(System.getProperty("user.home"), ".store/oauth2_sample"))
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory, clientSecrets, scopes).setDataStoreFactory(dataStoreFactory).build();
        LocalServerReceiver localReceiver = new LocalServerReceiver.Builder().setPort(8081).build();
        return new AuthorizationCodeInstalledApp(flow, localReceiver).authorize("user");
    }
    YouTube getYoutubeInstance(String accessToken) {
        Credential credential = new GoogleCredential().setAccessToken(accessToken)
        YouTube youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), credential).build()
        return youtube
    }
    YouTubeAnalytics getYoutubeAnalyticsInstance(String accessToken) {
        Credential credential = new GoogleCredential().setAccessToken(accessToken)
        YouTubeAnalytics youTubeAnalytics = new YouTubeAnalytics.Builder(new NetHttpTransport(), new JacksonFactory(), credential).build()
        return youTubeAnalytics
    }



    def youTubeAnalytics={

        /*String date_from = params?.date_from;
        String date_to =   params?.date_to;*/

       /* String date_to =   "17-09-2014";
        String date_from = "24-02-2015";*/
        String to_date;
        String from_date;
        Calendar cal = Calendar.instance
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat date_formatter = new SimpleDateFormat("yyyy-MM-dd");
        Map<String,Long> axis=new LinkedHashMap<String,Long>();


        List<String> scopes = Lists.newArrayList(
                "https://www.googleapis.com/auth/yt-analytics.readonly",
                "https://www.googleapis.com/auth/youtube.readonly"
        );

        // Authorization.

       /* Credential credential = authorize(scopes);*/

        try {

           /* youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                    .setApplicationName("youtube-api-project")
                    .build();
            // YouTube object used to make all analytic API requests.
            analytics = new YouTubeAnalytics.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                    .setApplicationName("youtube-api-project")
                    .build();*/
            def accessToken=TblYoutube.findAll();
            println(accessToken[0]?.access_token);
            youtube=getYoutubeInstance((accessToken[0]?.access_token).toString());
            analytics=getYoutubeAnalyticsInstance((accessToken[0]?.access_token).toString());

            YouTube.Channels.List channelRequest = youtube.channels().list("id");

            channelRequest.setMine(true);
            channelRequest.setFields("items(id,snippet/title)");
            ChannelListResponse channels = channelRequest.execute();
            // List channels associated with the user.
            List<Channel> listOfChannels = channels.getItems();
            Channel defaultChannel = listOfChannels.get(0);
           // String channel_Id = defaultChannel.getId();
            String channel_Id = params?.channel_id;
            if(params?.channel_id){
                println("same channel :>>>>>>>>>>.");
            }else{
                println("channel change>>>>>>>>>>.");
            }
            Date dt = new Date();
            println(">>>>>>>>>>>>>>>"+params?.start_date);
            Date toDate = date_formatter.parse(params?.start_date);
            //Date fromDate = formatter.parse(date_from);
            int firstCount=0;
            Date dateEnd=dt-3;
            String startDate="";
            long totalViews=0;
            long totalWatched=0;
            long totalsubscriber=0;
            long total_Lostsubscriber=0;
            long totallikes=0;
            long totaldeslikes=0;
            long totalcomments=0;
            long totalfavorites=0;
            long totalshares=0;
            long views=0;
            long watched=0;
            long subscriber=0;
            long subscriberLost=0;
            long likes=0;
            long dislikes=0;
            long comments=0;
            long favorites=0;
            long shares=0;
            String endDate="";
            long Total_SUBSCRIBER=0;
            long Total_EXT_APP=0;
            long Total_NO_LINK_OTHER=0;
            long Total_YT_SEARCH=0;
            long Total_EXT_URL=0;
            long Total_NO_LINK_EMBEDDED=0;
            long Total_PLAYLIST=0;
            long Total_YT_CHANNEL=0;
            long Total_YT_OTHER_PAGE=0;
            long Total_RELATED_VIDEO=0;
            long Total_ANNOTATION=0;
            long Total_ADVERTISING=0;
            /*to_date=formatter.format(toDate);
            from_date=formatter.format(fromDate);*/

            long SUBSCRIBER=0;
            long EXT_APP=0;
            long YT_SEARCH=0;
            long NO_LINK_OTHER=0;
            long EXT_URL=0;
            long NO_LINK_EMBEDDED=0;
            long PLAYLIST=0;
            long YT_CHANNEL=0;
            long YT_OTHER_PAGE=0;
            long RELATED_VIDEO=0;
            long ANNOTATION=0;
            long ADVERTISING=0;
            println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+toDate+">>>>>>>>>>>>>>>>>"+dateEnd);

            for (date in toDate..dateEnd) {
                cal.time = date;
                String startDay="";
                String startMonth="";
                String stringYear="";

                int month= cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DATE);
                int year=cal.get(Calendar.YEAR);

                int date_month=month+1;
                if (date_month==1 || date_month==2 || date_month==3 || date_month==4 || date_month==5 || date_month==6 || date_month==7 || date_month==8 || date_month==9){
                    startMonth="0"+date_month
                }
                else{
                    startMonth=date_month;
                }
                if (day==1 || day==2 || day==3 || day==4 || day==5 || day==6 || day==7 || day==8 || day==9){
                    startDay="0"+day
                }
                else{
                    startDay=day;
                }
                endDate = cal.get(Calendar.YEAR)+"-"+startMonth+"-"+startDay;

                if(firstCount==0){
                def past_list=TblViews.findByChannelIdAndEndDate(TblYoutubeChannel.findByChannelId(channel_Id),endDate);
                  if (past_list){
                      totalViews=past_list?.views;
                      totalWatched=past_list?.watchTime;
                      totalsubscriber=past_list?.subscriberGAINED;
                      total_Lostsubscriber=past_list?.subscriberLOST;
                      totallikes=past_list?.likes;
                      totaldeslikes=past_list?.disLikes;
                      totalcomments=past_list?.comments;
                      totalfavorites=past_list?.favoritesAdded;
                      totalshares=past_list?.shares;
                      Total_SUBSCRIBER=past_list?.sUBSCRIBER;
                      Total_RELATED_VIDEO=past_list?.rELATED_VIDEO;
                      Total_YT_OTHER_PAGE=past_list?.yT_OTHER_PAGE;
                      Total_YT_CHANNEL=past_list?.yT_CHANNEL;
                      Total_PLAYLIST=past_list?.pLAYLIST;
                      Total_NO_LINK_EMBEDDED=past_list?.nO_LINK_EMBEDDED;
                      Total_EXT_URL=past_list?.eXT_URL;
                      Total_YT_SEARCH=past_list?.yT_SEARCH;
                      Total_EXT_APP=past_list?.eXT_APP;
                      Total_NO_LINK_OTHER=past_list?.nO_LINK_OTHER;
                      Total_ANNOTATION=past_list?.aNNOTATION;
                      Total_ADVERTISING=past_list?.aDVERTISING;
                  }
                }

                if (startDate != "") {
                    ResultTable results = analytics.reports()
                            .query("channel==" + channel_Id, startDate, endDate, "views")
                            .execute();


                    /* ResultTable resultsEstimatedMinutesWatched = analytics.reports()
                             .query("channel==" + channel_Id, startDate, endDate, "estimatedMinutesWatched")
                             .setDimensions("day")
                             .execute();

                     ResultTable resultsAverageViewDuration = analytics.reports()
                             .query("channel==" + channel_Id, startDate, endDate, "averageViewDuration")
                             .execute();

                     ResultTable resultsViewsOverTimeQuery = analytics.reports()
                             .query("channel==" + channel_Id, startDate, endDate, "views,uniques")
                             .execute();*/

                    //ResultTable results1= analytics.reports().query("channel=="+channel_Id,"2014-11-01","2014-12-01","estimatedMinutesWatched") .setDimensions("month").execute();
                    ResultTable resultEstimateWatchedTime= analytics.reports().query("channel=="+channel_Id,startDate,endDate,"estimatedMinutesWatched").execute();
                    ResultTable resultLikes= analytics.reports().query("channel=="+channel_Id,startDate,endDate,"likes").execute();
                    ResultTable resultDislikes= analytics.reports().query("channel=="+channel_Id,startDate,endDate,"dislikes").execute();
                    ResultTable resultShares= analytics.reports().query("channel=="+channel_Id,startDate,endDate,"shares").execute();
                    ResultTable resultSubscribersGained= analytics.reports().query("channel=="+channel_Id,startDate,endDate,"subscribersGained").execute();
                    ResultTable resultSubscribersLost= analytics.reports().query("channel=="+channel_Id,startDate,endDate,"subscribersLost").execute();
                    ResultTable resultComments= analytics.reports().query("channel=="+channel_Id,startDate,endDate,"comments").execute();
                    ResultTable resultFavoritesAdded= analytics.reports().query("channel=="+channel_Id,startDate,endDate,"favoritesAdded").execute();
                    ResultTable resultTrafficSource = analytics.reports().query("channel=="+channel_Id,startDate,endDate,"views").setDimensions("insightTrafficSourceType").execute();
                   /* ResultTable resultVideosPublished = analytics.reports().query("channel=="+channel_Id,startDate,endDate,"VideosPublished").execute();*/






                    if (resultTrafficSource.getRows()!=null){
                        List<Object[]> sourceList= new ArrayList<Object[]>();
                        sourceList = resultTrafficSource.getRows();
                        for (ArrayList<Object[]> list : sourceList)
                        {
                            if (list[0]=="SUBSCRIBER"){
                                SUBSCRIBER=(long)list[1];
                                SUBSCRIBER=SUBSCRIBER-Total_SUBSCRIBER;
                                if (SUBSCRIBER<0){
                                    SUBSCRIBER = SUBSCRIBER * -1;
                                }
                                Total_SUBSCRIBER=SUBSCRIBER;
                            }
                            if (list[0]=="EXT_APP"){
                                EXT_APP=(long)list[1];
                                EXT_APP=EXT_APP-Total_EXT_APP;
                                if (EXT_APP<0){
                                    EXT_APP = EXT_APP * -1;
                                }
                                Total_EXT_APP=EXT_APP;
                            }
                            if (list[0]=="YT_SEARCH"){
                                YT_SEARCH=(long)list[1];
                                YT_SEARCH=YT_SEARCH-Total_YT_SEARCH;
                                if (YT_SEARCH<0){
                                    YT_SEARCH = YT_SEARCH * -1;
                                }
                                Total_YT_SEARCH=YT_SEARCH;
                            }
                            if (list[0]=="NO_LINK_OTHER"){
                                NO_LINK_OTHER=(long)list[1];
                                NO_LINK_OTHER=NO_LINK_OTHER-Total_NO_LINK_OTHER;
                                if (NO_LINK_OTHER<0){
                                    NO_LINK_OTHER = NO_LINK_OTHER * -1;
                                }
                                Total_NO_LINK_OTHER=NO_LINK_OTHER;
                            }
                            if (list[0]=="EXT_URL"){
                                EXT_URL=(long)list[1];
                                EXT_URL=EXT_URL-Total_EXT_URL;
                                if (EXT_URL<0){
                                    EXT_URL = EXT_URL * -1;
                                }
                                Total_EXT_URL= EXT_URL;
                            }
                            if (list[0]=="NO_LINK_EMBEDDED"){
                                NO_LINK_EMBEDDED=(long)list[1];
                                NO_LINK_EMBEDDED=NO_LINK_EMBEDDED-Total_NO_LINK_EMBEDDED;
                                if (NO_LINK_EMBEDDED<0){
                                    NO_LINK_EMBEDDED = NO_LINK_EMBEDDED * -1;
                                }
                                Total_NO_LINK_EMBEDDED = NO_LINK_EMBEDDED;
                            }
                            if (list[0]=="PLAYLIST"){
                                PLAYLIST=(long)list[1];
                                PLAYLIST=PLAYLIST-Total_PLAYLIST;
                                if (PLAYLIST<0){
                                    PLAYLIST = PLAYLIST * -1;
                                }
                                Total_PLAYLIST= PLAYLIST;
                            }
                            if (list[0]=="YT_CHANNEL"){
                                YT_CHANNEL=(long)list[1];
                                YT_CHANNEL=YT_CHANNEL-Total_YT_CHANNEL;
                                if (YT_CHANNEL<0){
                                    YT_CHANNEL = YT_CHANNEL * -1;
                                }
                                Total_YT_CHANNEL= YT_CHANNEL;
                            }
                            if (list[0]=="YT_OTHER_PAGE"){
                                YT_OTHER_PAGE=(long)list[1];
                                YT_OTHER_PAGE=YT_OTHER_PAGE-Total_YT_OTHER_PAGE;
                                if (YT_OTHER_PAGE<0){
                                    YT_OTHER_PAGE = YT_OTHER_PAGE * -1;
                                }
                                Total_YT_OTHER_PAGE= YT_OTHER_PAGE;
                            }
                            if (list[0]=="RELATED_VIDEO"){
                                RELATED_VIDEO=(long)list[1];
                                RELATED_VIDEO=RELATED_VIDEO-Total_RELATED_VIDEO;
                                if (RELATED_VIDEO<0){
                                    RELATED_VIDEO = RELATED_VIDEO * -1;
                                }
                                Total_RELATED_VIDEO= RELATED_VIDEO;
                            }
                            if (list[0]=="ANNOTATION"){
                                ANNOTATION=(long)list[1];
                                ANNOTATION=ANNOTATION-Total_ANNOTATION;
                                if (ANNOTATION<0){
                                    ANNOTATION = ANNOTATION * -1;
                                }
                                Total_ANNOTATION= ANNOTATION;
                            }
                            if (list[0]=="ADVERTISING"){
                                ADVERTISING=(long)list[1];
                                ADVERTISING=ADVERTISING-Total_ADVERTISING;
                                if (ADVERTISING<0){
                                    ADVERTISING = ADVERTISING * -1;
                                }
                                Total_ADVERTISING= ADVERTISING;
                            }
                        }

                        println(SUBSCRIBER);
                        println(EXT_APP);
                        println(YT_SEARCH);
                        println(NO_LINK_OTHER);
                        println(EXT_URL);
                        println(NO_LINK_EMBEDDED);
                        println(PLAYLIST);
                        println(YT_CHANNEL);
                        println(YT_OTHER_PAGE);
                        println(RELATED_VIDEO);
                        println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                        new TblTrafficSourceType(
                                startDate:startDate ,
                                endDate: endDate,
                                sUBSCRIBER: SUBSCRIBER,
                                rELATED_VIDEO: RELATED_VIDEO,
                                yT_OTHER_PAGE: YT_OTHER_PAGE,
                                yT_CHANNEL: YT_CHANNEL,
                                pLAYLIST: PLAYLIST,
                                nO_LINK_EMBEDDED: NO_LINK_EMBEDDED,
                                eXT_URL: EXT_URL,
                                yT_SEARCH: YT_SEARCH,
                                eXT_APP: EXT_APP,
                                nO_LINK_OTHER: NO_LINK_OTHER,
                                aNNOTATION: ANNOTATION,
                                aDVERTISING: ADVERTISING,
                                channelId: TblYoutubeChannel.findByChannelId(channel_Id),
                        ).save(failOnError: true);

                    }
                    else{
                        Total_SUBSCRIBER=0;
                        Total_EXT_APP=0;
                        Total_NO_LINK_OTHER=0;
                        Total_YT_SEARCH=0;
                        Total_EXT_URL=0;
                        Total_NO_LINK_EMBEDDED=0;
                        Total_PLAYLIST=0;
                        Total_YT_CHANNEL=0;
                        Total_YT_OTHER_PAGE=0;
                        Total_RELATED_VIDEO=0;
                        Total_ANNOTATION=0;
                        Total_ADVERTISING=0;
                    }


                    /******
                     *  views
                     * *******/

                    if (results.getRows()!=null){
                        String viewsArray= results.getRows()[0][0];
                        double views_val= Double.parseDouble(viewsArray);
                        views = (long)views_val;
                        views=views-totalViews;
                        if (views<0){
                            views = views * -1;
                        }
                        totalViews=views;

                        axis.put(startDate,views);
                        //  println(views+">>>>>>>>>>>>"+startDate+">>>>>>>>>>>>>"+endDate);
                        /*new TblViews(
                                startDate:startDate ,
                                endDate: endDate,
                                views: views,
                                channelId: TblYoutubeChannel.findByChannelId(channel_Id),
                        ).save(failOnError: true);*/
                    }
                    else{
                        axis.put(endDate,0);
                        views=0;
                        totalViews=0;
                        /*new TblViews(
                                startDate:startDate ,
                                endDate: endDate,
                                views: 0,
                                channelId: TblYoutubeChannel.findByChannelId(channel_Id),
                        ).save(failOnError: true);*/
                    }
                    /****
                     *  EstimateWatchedTime
                     * ******/
                    if (resultEstimateWatchedTime.getRows()!=null){
                        String resultEstimateWatchedTimeArray = resultEstimateWatchedTime.getRows()[0][0];
                        double watched_val= Double.parseDouble(resultEstimateWatchedTimeArray);
                        watched = (long)watched_val;
                        watched=watched-totalWatched;
                        if (watched<0){
                            watched = watched * -1;
                        }
                        totalWatched= watched;

                        //println("WatchTime:>>>"+watched+">>>>>>>>>>>>"+startDate+">>>>>>>>>>>>>"+endDate);
                        /* new TblViews(
                                 startDate:startDate ,
                                 endDate: endDate,
                                 views: views,
                                 channelId: TblYoutubeChannel.findByChannelId(channel_Id),
                         ).save(failOnError: true);*/
                    }
                    else{
                        watched=0;
                        totalWatched=0;
                        // println("WatchTime:>>>"+0+">>>>>>>>>>>>"+startDate+">>>>>>>>>>>>>"+endDate);

                    }


                    /******
                     *
                     *  SubscribersGained
                     *
                     * *****/

                    if (resultSubscribersGained.getRows()!=null){
                        String resultSubscribersGainedArray = resultSubscribersGained.getRows()[0][0];
                        double subscribers_val= Double.parseDouble(resultSubscribersGainedArray);
                        subscriber = (long)subscribers_val;
                        subscriber=subscriber-totalsubscriber;
                        if (subscriber<0){
                            subscriber = subscriber * -1;
                        }
                        totalsubscriber= subscriber;
                        // println("subscriberGN:>>>"+subscriber+">>>>>>>>>>>>"+startDate+">>>>>>>>>>>>>"+endDate);
                    }
                    else{
                        subscriber=0;
                        totalsubscriber=0;
                        //  println("subscriber:>>>"+0+">>>>>>>>>>>>"+startDate+">>>>>>>>>>>>>"+endDate);

                    }

                    /******
                     *
                     *  SubscribersLost
                     *
                     * *****/

                    if (resultSubscribersLost.getRows()!=null){
                        String resultSubscribersLostArray = resultSubscribersLost.getRows()[0][0];
                        double subscribersLost_val= Double.parseDouble(resultSubscribersLostArray);
                        subscriberLost = (long)subscribersLost_val;
                        subscriberLost=subscriberLost-total_Lostsubscriber;
                        if (subscriberLost<0){
                            subscriberLost = subscriberLost * -1;
                        }
                        total_Lostsubscriber= subscriberLost;
                        // println("subscriberLost:>>>"+subscriberLost+">>>>>>>>>>>>"+startDate+">>>>>>>>>>>>>"+endDate);
                    }
                    else{
                        subscriberLost=0;
                        total_Lostsubscriber=0;
                        //  println("subscriber:>>>"+0+">>>>>>>>>>>>"+startDate+">>>>>>>>>>>>>"+endDate);

                    }

                    /***
                     * Likes
                     *
                     * ***********/

                    if (resultLikes.getRows()!=null){
                        String resultLikesArray = resultLikes.getRows()[0][0];
                        double likes_val= Double.parseDouble(resultLikesArray);
                        likes = (long)likes_val;
                        boolean likeFlag=true;
                        if (likes<0){
                            likes=likes+totallikes;
                            likeFlag=false;

                        }
                        else{
                            likes=likes-totallikes;
                        }
                        if (likes<0 && likeFlag){
                            likes = likes * -1;
                        }
                        totallikes= likes;
                        //  println("Likes:>>>"+likes+">>>>>>>>>>>>"+startDate+">>>>>>>>>>>>>"+endDate);
                    }
                    else{
                        likes=0;
                        totallikes=0;
                        //  println("Likes:>>>"+0+">>>>>>>>>>>>"+startDate+">>>>>>>>>>>>>"+endDate);

                    }

                    /******
                     * DisLikes
                     * ***********/



                    if (resultDislikes.getRows()!=null){
                        String resultDislikesArray = resultDislikes.getRows()[0][0];
                        double deslike_val= Double.parseDouble(resultDislikesArray);
                        dislikes = (long)deslike_val;
                        dislikes=dislikes-totaldeslikes;
                        if (dislikes<0){
                            dislikes = dislikes * -1;
                        }
                        totaldeslikes= dislikes;
                        //println("Totaldeslikes:>>>"+dislikes+">>>>>>>>>>>>"+startDate+">>>>>>>>>>>>>"+endDate);
                    }
                    else{
                        dislikes=0;
                        totaldeslikes=0;
                        //  println("Totaldeslikes:>>>"+0+">>>>>>>>>>>>"+startDate+">>>>>>>>>>>>>"+endDate);

                    }

                    /******
                     * Comments
                     * ***********/



                    if (resultComments.getRows()!=null){
                        String resultCommentsArray = resultComments.getRows()[0][0];
                        double comments_val= Double.parseDouble(resultCommentsArray);
                        comments = (long)comments_val;
                        comments=comments-totalcomments;
                        if (comments<0){
                            comments = comments * -1;
                        }
                        totalcomments= comments;
                        // println("Comments::>>>"+comments+">>>>>>>>>>>>"+startDate+">>>>>>>>>>>>>"+endDate);
                    }
                    else{
                        comments=0;
                        totalcomments=0;
                        //  println("Comments:>>>"+0+">>>>>>>>>>>>"+startDate+">>>>>>>>>>>>>"+endDate);

                    }

                    /******
                     * FavoritesAdded
                     * ***********/



                    if (resultFavoritesAdded.getRows()!=null){
                        String resultFavoritesAddedArray = resultFavoritesAdded.getRows()[0][0];
                        double favorites_val= Double.parseDouble(resultFavoritesAddedArray);
                        favorites = (long)favorites_val;
                        favorites=favorites-totalfavorites;
                        if (favorites<0){
                            favorites = favorites * -1;
                        }
                        totalfavorites= favorites;
                        // println("Favorites:>>>"+favorites+">>>>>>>>>>>>"+startDate+">>>>>>>>>>>>>"+endDate);
                    }
                    else{
                        favorites=0;
                        totalfavorites=0;
                        //  println("Favorites:>>>"+0+">>>>>>>>>>>>"+startDate+">>>>>>>>>>>>>"+endDate);

                    }

                    /******
                     * Shares
                     * ***********/

                    if (resultShares.getRows()!=null){
                        String resultSharesArray = resultShares.getRows()[0][0];
                        double shares_val= Double.parseDouble(resultSharesArray);
                        shares = (long)shares_val;
                        shares=shares-totalshares;
                        if (shares<0){
                            shares = shares * -1;
                        }
                        totalshares= shares;
                        //println("shares :::>>>"+shares+">>>>>>>>>>>>"+startDate+">>>>>>>>>>>>>"+endDate);
                    }
                    else{
                        shares=0;
                        totalshares=0;
                        //  println("shares:>>>"+0+">>>>>>>>>>>>"+startDate+">>>>>>>>>>>>>"+endDate);

                    }
                    new TblViews(
                            startDate:startDate ,
                            endDate: endDate,
                            views: views,
                            watchTime: watched,
                            subscriberGAINED: subscriber,
                            subscriberLOST:subscriberLost,
                            likes:likes,
                            disLikes:dislikes,
                            comments:comments,
                            favoritesAdded:favorites,
                            shares: shares,
                            sUBSCRIBER: SUBSCRIBER,
                            rELATED_VIDEO: RELATED_VIDEO,
                            yT_OTHER_PAGE: YT_OTHER_PAGE,
                            yT_CHANNEL: YT_CHANNEL,
                            pLAYLIST: PLAYLIST,
                            nO_LINK_EMBEDDED: NO_LINK_EMBEDDED,
                            eXT_URL: EXT_URL,
                            yT_SEARCH: YT_SEARCH,
                            eXT_APP: EXT_APP,
                            nO_LINK_OTHER: NO_LINK_OTHER,
                            aNNOTATION: ANNOTATION,
                            aDVERTISING: ADVERTISING,
                            channelId: TblYoutubeChannel.findByChannelId(channel_Id),
                    ).save(failOnError: true);

                }
                startDate = endDate;
                firstCount=firstCount+1;
            }

/*           ResultTable results= analytics.reports()
                   .query("channel=="+channelId,"2014-11-01","2014-12-01","views")
                   .execute();
                   println(results.getRows()[0][0]);*/

            /*render axis as JSON;*/
        }
        catch (GoogleJsonResponseException e) {
            e.printStackTrace();

        } catch (Throwable t) {
            t.printStackTrace();
        }
        finally {
            println("Data imported for Channel ");

        }
       redirect(controller:'addChannel',action: 'channel');
    }
}
