package com.tan

class TblTrafficSourceType {

    Integer id
    String startDate
    String endDate
    TblYoutubeChannel channelId
    Integer eXT_APP
    Integer yT_SEARCH
    Integer nO_LINK_OTHER
    Integer eXT_URL
    Integer nO_LINK_EMBEDDED
    Integer pLAYLIST
    Integer sUBSCRIBER
    Integer yT_CHANNEL
    Integer yT_OTHER_PAGE
    Integer rELATED_VIDEO
    Integer aNNOTATION
    Integer aDVERTISING

    static mapping = {
        id column: 'TrafficSourceID'
        startDate column: 'StartDate'
        endDate column: 'EndDate'
        channelId column: 'ChannelID'
        eXT_APP column: 'EXT_APP'
        yT_SEARCH column: 'YT_SEARCH'
        nO_LINK_OTHER column: 'NO_LINK_OTHER'
        eXT_URL column: 'EXT_URL'
        nO_LINK_EMBEDDED column: 'NO_LINK_EMBEDDED'
        pLAYLIST column: 'PLAYLIST'
        sUBSCRIBER column: 'SUBSCRIBER'
        yT_CHANNEL column: 'YT_CHANNEL'
        yT_OTHER_PAGE column: 'YT_OTHER_PAGE'
        rELATED_VIDEO column: 'RELATED_VIDEO'
        aNNOTATION column: 'ANNOTATION'
        aDVERTISING column: 'ADVERTISING'
        table:'tbl_trafficSource'
        version false
    }

    static constraints = {

        startDate nullable: false
        endDate nullable: false
        channelId nullable: false
        eXT_APP nullable: true
        yT_SEARCH nullable: true
        nO_LINK_OTHER nullable: true
        eXT_URL nullable: true
        nO_LINK_EMBEDDED nullable: true
        pLAYLIST nullable: true
        sUBSCRIBER nullable: true
        yT_CHANNEL nullable: true
        yT_OTHER_PAGE nullable: true
        rELATED_VIDEO nullable: true
        aNNOTATION nullable: true
        aDVERTISING nullable: true

    }
}
