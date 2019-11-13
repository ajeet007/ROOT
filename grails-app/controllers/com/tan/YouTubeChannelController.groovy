package com.tan

import grails.converters.JSON
import youtube.GetYouTubeComment
import java.text.SimpleDateFormat

class YouTubeChannelController {

    def index() {

    }

    def youtube = {
        try {

            String dev_id = "AIzaSyCWuYRVnpWLB-eMjfdWft9q11iDoALwb7Q";
            //String video_idi = "vow-kxTPatc";
            GetYouTubeComment yu = new GetYouTubeComment(dev_id);

        }
        catch (Exception e) {
            e.printStackTrace()
        }
    }
    def sendMessage={
           println("params val::?>>>>>>>>>>>>>"+params);

        String dev_id = "AIzaSyCWuYRVnpWLB-eMjfdWft9q11iDoALwb7Q";
        //String video_idi = "vow-kxTPatc";
       /* GetYouTubeComment yu = new GetYouTubeComment(dev_id);*/
        //String video_id = params?.videoId
        String user_google = params?.youTubeUserName
        String user_pass = params?.youTubePassword
        YouTubeAnalyticsReportsController ob=new YouTubeAnalyticsReportsController();
        ob.youTubeAnalytics();
       /* yu.sendMessage(user_google,user_pass);*/
        redirect(controller: 'youTubeChannel',action: 'analyticsReport')
    }
    def analyticsReport={
      render(view: 'analyticsReport')
    }

   def graphViews={
       println(params?.date_to)
       println(params?.date_from)
       String date_from = params?.date_from;
       String date_to =   params?.date_to;
       String channelId=params?.channel_id;
       SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
       Date dateStart = formatter.parse(date_to);
       Date dateEnd = formatter.parse(date_from);
       Date toDate =dateStart-1;
       Date fromDate;
       if (params?.filterViews=="daily"){
           fromDate=dateEnd-1;
       }
       else{
           fromDate=dateEnd;
       }


       Calendar startCal = Calendar.instance
       Calendar endCal = Calendar.instance
       startCal.time = toDate;
       endCal.time= fromDate
       String startDay="";
       String startMonth="";
       String startDate="";
       String endDay="";
       String endMonth="";
       String endDate="";
       int month= startCal.get(Calendar.MONTH);
       int day=startCal.get(Calendar.DATE);
       int date_month=month+1;

       int end_month= endCal.get(Calendar.MONTH);
       int end_day=endCal.get(Calendar.DATE);
       int end_date_month=end_month+1;

       if (date_month==1 || date_month==2 || date_month==3 || date_month==4 || date_month==5 || date_month==6 || date_month==7 || date_month==8 || date_month==9){
           startMonth="0"+date_month;
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

       if (end_date_month==1 || end_date_month==2 || end_date_month==3 || end_date_month==4 || end_date_month==5 || end_date_month==6 || end_date_month==7 || end_date_month==8 || end_date_month==9){
           endMonth="0"+ end_date_month;
       }
       else{
           endMonth=end_date_month
       }
       if (end_day==1 || end_day==2 || end_day==3 || end_day==4 || end_day==5 || end_day==6 || end_day==7 || end_day==8 || end_day==9){
           endDay = "0"+end_day;
       }
       else{
           endDay = end_day;
       }

       startDate = startCal.get(Calendar.YEAR)+"-"+startMonth+"-"+startDay;
       endDate=     endCal.get(Calendar.YEAR)+"-"+endMonth+"-"+endDay;
       def channel=TblYoutubeChannel.findByChannelId(channelId);
       def result=TblViews.findAllByChannelIdAndStartDateBetween(channel,startDate,endDate);
       if (params?.filterViews=="daily"){
           render result as JSON;
       }
       def final_week_map=[:];
       if (params?.filterViews == "weekly") {
           SimpleDateFormat date_formatter = new SimpleDateFormat("dd-MM-yyyy");
           Calendar dateInstance = Calendar.instance
           int viewCount = 0;
           int likeCount = 0;
           int disLikeCount = 0;
           int commentsCount = 0;
           int favoritesAddedCount = 0;
           int sharesCount = 0;
           int subscriberGAINEDCount = 0;
           int subscriberLOSTCount = 0;
           int watchTimeCount = 0;
           int advertisingCount = 0;

           for (date in toDate..fromDate) {

               String start_weekDay = "";
               String start_weekMonth = "";

               def week_map = [:];
               dateInstance.time = date;
               int week_month = dateInstance.get(Calendar.MONTH);
               int week_day = dateInstance.get(Calendar.DATE);
               int date_month_week = week_month + 1;


               if (date_month_week == 1 || date_month_week == 2 || date_month_week == 3 || date_month_week == 4 || date_month_week == 5 || date_month_week == 6 || date_month_week == 7 || date_month_week == 8 || date_month_week == 9) {
                   start_weekMonth = "0" + date_month_week;
               } else {
                   start_weekMonth = date_month_week;
               }
               if (week_day == 1 || week_day == 2 || week_day == 3 || week_day == 4 || week_day == 5 || week_day == 6 || week_day == 7 || week_day == 8 || week_day == 9) {
                   start_weekDay = "0" + week_day

               } else {
                   start_weekDay = week_day;

               }
               String date_of_week = dateInstance.get(Calendar.YEAR) + "-" + start_weekMonth + "-" + start_weekDay;
               result.each { e ->
                   if (e.endDate.equals(date_of_week)) {
                       int start_date = dateInstance.getAt(Calendar.DAY_OF_WEEK);
                       if (start_date == 1 || start_date == 2 || start_date == 3 || start_date == 4 || start_date == 5 || start_date == 6 || start_date == 7){
                           viewCount = viewCount + e.views;
                           likeCount = likeCount + e.likes;
                           disLikeCount = disLikeCount + e.disLikes;
                           commentsCount = commentsCount + e.comments;
                           favoritesAddedCount = favoritesAddedCount + e.favoritesAdded;
                           sharesCount = sharesCount + e.shares;
                           subscriberGAINEDCount = subscriberGAINEDCount + e.subscriberGAINED;
                           subscriberLOSTCount = subscriberLOSTCount + e.subscriberLOST;
                           watchTimeCount = watchTimeCount + e.watchTime;
                           advertisingCount = advertisingCount + e.aDVERTISING;
                       }
                       if (start_date == 7) {
                           week_map.put("views", viewCount);
                           week_map.put("likes", likeCount);
                           week_map.put("dislikes", disLikeCount);
                           week_map.put("comments", commentsCount);
                           week_map.put("favoritesAdded", favoritesAddedCount);
                           week_map.put("shares", sharesCount);
                           week_map.put("subscriberGAINED", subscriberGAINEDCount);
                           week_map.put("subscriberLOST", subscriberLOSTCount);
                           week_map.put("watchTime", watchTimeCount);
                           week_map.put("aDVERTISING", advertisingCount);
                           week_map.put("endDate", date_of_week);
                           final_week_map.put(date_of_week, week_map);
                           viewCount = 0;
                           likeCount = 0;
                           disLikeCount = 0;
                           commentsCount = 0;
                           favoritesAddedCount = 0;
                           sharesCount = 0;
                           subscriberGAINEDCount = 0;
                           subscriberLOSTCount = 0;
                           watchTimeCount = 0;
                           advertisingCount = 0;

                           week_map = [:];
                       }

                       if (date_formatter.format(date).equals(date_formatter.format(fromDate)) && start_date!= 7) {
                           week_map.put("views", viewCount);
                           week_map.put("likes", likeCount);
                           week_map.put("dislikes", disLikeCount);
                           week_map.put("comments", commentsCount);
                           week_map.put("favoritesAdded", favoritesAddedCount);
                           week_map.put("shares", sharesCount);
                           week_map.put("subscriberGAINED", subscriberGAINEDCount);
                           week_map.put("subscriberLOST", subscriberLOSTCount);
                           week_map.put("watchTime", watchTimeCount);
                           week_map.put("aDVERTISING", advertisingCount);
                           week_map.put("endDate", date_of_week);
                           final_week_map.put(date_of_week, week_map);
                           viewCount = 0;
                           likeCount = 0;
                           disLikeCount = 0;
                           commentsCount = 0;
                           favoritesAddedCount = 0;
                           sharesCount = 0;
                           subscriberGAINEDCount = 0;
                           subscriberLOSTCount = 0;
                           watchTimeCount = 0;
                           advertisingCount = 0;
                           week_map = [:];
                       }
                   }
               }
           }
       }
       if (params?.filterViews == "monthly"){
           Calendar toInstance = Calendar.instance
           Calendar fromInstance = Calendar.instance
           toInstance.time = dateStart;
           fromInstance.time = dateEnd;
           def final_month_map=[:];
           toInstance.set(Calendar.DATE, toInstance.getActualMinimum(Calendar.DATE));
           fromInstance.set(Calendar.DATE, fromInstance.getActualMaximum(Calendar.DATE));
           Date firstDayOfMonth = toInstance.getTime();

           Date lastDayOfMonth = fromInstance.getTime();
           toInstance.setTime(firstDayOfMonth);
           fromInstance.setTime(lastDayOfMonth);
           int to_year = toInstance.get(Calendar.YEAR);
           int to_month = toInstance.get(Calendar.MONTH);
           int to_day = toInstance.get(Calendar.DAY_OF_MONTH);
           int startingMonth = to_month + 1;
           int from_year = fromInstance.get(Calendar.YEAR);
           int from_month = fromInstance.get(Calendar.MONTH);
           int from_day = fromInstance.get(Calendar.DAY_OF_MONTH);
           int endingMonth = from_month + 1;
           String start_Day="";
           String start_Month="";
           String start_Date="";
           String end_Day="";
           String end_Month="";
           String end_Date="";
           if (startingMonth==1 || startingMonth==2 || startingMonth==3 || startingMonth==4 || startingMonth==5 || startingMonth==6 || startingMonth==7 || startingMonth==8 || startingMonth==9){
               start_Month="0"+startingMonth;
           }
           else{
               start_Month=startingMonth;
           }
           if (to_day==1 || to_day==2 || to_day==3 || to_day==4 || to_day==5 || to_day==6 || to_day==7 || to_day==8 || to_day==9){
               start_Day="0"+to_day

           }
           else{
               start_Day=to_day;

           }

           if (endingMonth==1 || endingMonth==2 || endingMonth==3 || endingMonth==4 || endingMonth==5 || endingMonth==6 || endingMonth==7 || endingMonth==8 || endingMonth==9){
               end_Month="0"+ endingMonth;
           }
           else{
               end_Month=endingMonth
           }
           if (from_day==1 || from_day==2 || from_day==3 || from_day==4 || from_day==5 || from_day==6 || from_day==7 || from_day==8 || from_day==9){
               end_Day = "0"+from_day;
           }
           else{
               end_Day = from_day;
           }

           start_Date = toInstance.get(Calendar.YEAR)+"-"+start_Month+"-"+start_Day;
           end_Date = fromInstance.get(Calendar.YEAR)+"-"+end_Month+"-"+end_Day;
           def month_map = [:];
           def resultData=TblViews.findAllByChannelIdAndEndDateBetween(channel,start_Date,end_Date);
           SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
           Calendar calendarInstance = Calendar.instance
           int viewCount = 0;
           int likeCount = 0;
           int disLikeCount = 0;
           int commentsCount = 0;
           int favoritesAddedCount = 0;
           int sharesCount = 0;
           int subscriberGAINEDCount = 0;
           int subscriberLOSTCount = 0;
           int watchTimeCount = 0;
           int advertisingCount = 0;

               resultData.each{ e->
                   String startDateString=e.endDate;
                   Date date_starting= dateFormatter.parse(startDateString);
                   calendarInstance.time = date_starting;
                   int calenderMonth = calendarInstance.get(Calendar.MONTH);
                   int calender_Month = calenderMonth + 1;
                   int currentDate = calendarInstance.get(Calendar.DATE);
                   int lastDate = calendarInstance.getActualMaximum(Calendar.DATE);
                   int dateOfYear = calendarInstance.get(Calendar.YEAR);
                   String monthString = "";
                   if (calender_Month ==1){
                       monthString = "Jan-"+dateOfYear;
                   }
                   if (calender_Month ==2){
                       monthString = "Feb-"+dateOfYear;
                   }
                   if (calender_Month ==3){
                       monthString = "March-"+dateOfYear;
                   }
                   if (calender_Month ==4){
                       monthString = "April-"+dateOfYear;
                   }
                   if (calender_Month ==5){
                       monthString = "May-"+dateOfYear;
                   }
                   if (calender_Month ==6){
                       monthString = "June-"+dateOfYear;
                   }
                   if (calender_Month ==7){
                       monthString = "July-"+dateOfYear;
                   }
                   if (calender_Month ==8){
                       monthString = "Aug-"+dateOfYear;
                   }
                   if (calender_Month ==9){
                       monthString = "Sept-"+dateOfYear;
                   }
                   if (calender_Month ==10){
                       monthString = "Oct-"+dateOfYear;
                   }
                   if (calender_Month ==11){
                       monthString = "Nov-"+dateOfYear;
                   }
                   if (calender_Month ==12){
                       monthString = "Dec-"+dateOfYear;
                   }

                    if (currentDate==lastDate){
                        viewCount = viewCount + e.views;
                        likeCount = likeCount + e.likes;
                        disLikeCount = disLikeCount + e.disLikes;
                        commentsCount = commentsCount + e.comments;
                        favoritesAddedCount = favoritesAddedCount + e.favoritesAdded;
                        sharesCount = sharesCount + e.shares;
                        subscriberGAINEDCount = subscriberGAINEDCount + e.subscriberGAINED;
                        subscriberLOSTCount = subscriberLOSTCount + e.subscriberLOST;
                        watchTimeCount = watchTimeCount + e.watchTime;
                        advertisingCount = advertisingCount + e.aDVERTISING;
                        month_map.put("views", viewCount);
                        month_map.put("likes", likeCount);
                        month_map.put("dislikes", disLikeCount);
                        month_map.put("comments", commentsCount);
                        month_map.put("favoritesAdded", favoritesAddedCount);
                        month_map.put("shares", sharesCount);
                        month_map.put("subscriberGAINED", subscriberGAINEDCount);
                        month_map.put("subscriberLOST", subscriberLOSTCount);
                        month_map.put("watchTime", watchTimeCount);
                        month_map.put("aDVERTISING", advertisingCount);
                        month_map.put("endDate", monthString);
                        final_month_map.put(e.endDate,month_map);
                        viewCount = 0;
                        likeCount = 0;
                        disLikeCount = 0;
                        commentsCount = 0;
                        favoritesAddedCount = 0;
                        sharesCount = 0;
                        subscriberGAINEDCount = 0;
                        subscriberLOSTCount = 0;
                        watchTimeCount = 0;
                        advertisingCount = 0;
                        month_map = [:];
                    }
                   else{
                        viewCount = viewCount + e.views;
                        likeCount = likeCount + e.likes;
                        disLikeCount = disLikeCount + e.disLikes;
                        commentsCount = commentsCount + e.comments;
                        favoritesAddedCount = favoritesAddedCount + e.favoritesAdded;
                        sharesCount = sharesCount + e.shares;
                        subscriberGAINEDCount = subscriberGAINEDCount + e.subscriberGAINED;
                        subscriberLOSTCount = subscriberLOSTCount + e.subscriberLOST;
                        watchTimeCount = watchTimeCount + e.watchTime;
                        advertisingCount = advertisingCount + e.aDVERTISING;
                    }

               }
           render final_month_map as JSON;
       }

       if (params?.filterViews=="weekly"){
           render final_week_map as JSON;
       }
    }
}
