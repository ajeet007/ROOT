package com.tan

import grails.converters.JSON

import java.text.SimpleDateFormat

class AddChannelController {

    def index() {}
    def importChannel={
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            if (params.channelCode && params?.channelName && params?.startDate) {
                println(">>>>>>>>>>>>>>>>>>>"+params);
                def client = TblYoutubeChannel.findByChannelIdAndChannelName(params?.channelCode,params?.channelName);
                Date date = formatter.parse(params?.startDate);
                if (client == null) {
                    def projectObject = new TblYoutubeChannel(
                            channelName: params?.channelName,
                            channelId: params?.channelCode,
                            startDate: date,
                    ).save(failOnError: true);

                    render projectObject as JSON
                } else {

                    def projectInformation = [:];
                    projectInformation.put("projectInfo", client?.channelName);
                    projectInformation.put("clientInfo", client?.channelId);

                    render projectInformation as JSON;
                }
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

    def channel={
        def startDate;
        if(params?.startDate==""){
            def youtubeChannelData=TblYoutubeChannel.findByChannelId(params?.channelId);
            params?.startDate = youtubeChannelData?.startDate;
        }

        if (params?.channelId && params?.startDate){
            println("params?.channelId && params?.startDate"+params?.channelId +">>>>>"+ params?.startDate);
           redirect(controller: 'youTubeAnalyticsReports',action: 'youTubeAnalytics',params: [channel_id: params?.channelId,start_date:params?.startDate]);
        }
        def dateMap=[:];
        def channel_ID= TblYoutubeChannel.findAll();
        channel_ID.each {i->
            def lastUpdate=TblViews.findAllByChannelId(TblYoutubeChannel.findById(i?.id));
            if (lastUpdate.size()>0){
             dateMap.put(i?.channelId,lastUpdate.last()?.endDate);
            }else if (lastUpdate.size()==0){
                dateMap.put(i?.channelId,"");
            }
        }
        [dateMap:dateMap]
    }
}
