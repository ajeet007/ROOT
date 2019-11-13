package com.tan

import grails.converters.JSON

import java.text.SimpleDateFormat

class AddCMSController {

    def index() {}

    def importCMS={
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            if (params.cmsCode && params?.cmsName && params?.startDate) {
                def cms = TblYoutubeCMS.findByContentOwnerIDAndCmsName(params?.cmsCode,params?.cmsName);
                Date date = formatter.parse(params?.startDate);
                if (cms == null) {
                    def projectCMSObject = new TblYoutubeCMS(
                            cmsName: params?.cmsName,
                            contentOwnerID: params?.cmsCode,
                            startDate: date,
                    ).save(failOnError: true);

                    render projectCMSObject as JSON
                } else {

                    def cmsInformation = [:];
                    cmsInformation.put("cmsInfo", cms?.cmsName);
                    cmsInformation.put("cms_Info", cms?.contentOwnerID);

                    render cmsInformation as JSON;
                }
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

    def cms={
        def startDate;
        if(params?.startDate==""){
            def youtubeChannelData=TblYoutubeCMS.findByContentOwnerID(params?.contentOwner_ID);
            println(">>>>>>>>"+youtubeChannelData)
            params?.startDate = youtubeChannelData?.startDate;
        }
        if (params?.contentOwner_ID && params?.startDate){
            redirect(controller: 'youTubeContentOwners',action: 'contentOwnersReport',params: [contentOwner_ID: params?.contentOwner_ID,start_date:params?.startDate]);
        }
        def dateMap=[:];
        def channel_ID= TblYoutubeCMS.findAll();
        channel_ID.each {i->
            def lastUpdate=TblCMS.findAllByContentOwnerID(TblYoutubeCMS.findById(i?.id));
            if (lastUpdate.size()>0){
                dateMap.put(i?.contentOwnerID,lastUpdate.last()?.endDate);
            }else if (lastUpdate.size()==0){
                dateMap.put(i?.contentOwnerID,"");
            }
        }
        [dateMap:dateMap]
    }
}
