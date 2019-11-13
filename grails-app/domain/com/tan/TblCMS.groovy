package com.tan

class TblCMS {
    Long id
    String startDate
    String endDate
    TblYoutubeCMS contentOwnerID
    Long views
    Long watchTime
    Long subscriberGAINED
    Long subscriberLOST
    Long likes
    Long disLikes
    Long comments
    Long favoritesAdded
    Long shares
    Long eXT_APP
    Long yT_SEARCH
    Long nO_LINK_OTHER
    Long eXT_URL
    Long nO_LINK_EMBEDDED
    Long pLAYLIST
    Long sUBSCRIBER
    Long yT_CHANNEL
    Long yT_OTHER_PAGE
    Long rELATED_VIDEO
    Long aNNOTATION
    Long aDVERTISING

    static mapping = {
        id column: 'contentID'
        startDate column: 'StartDate'
        endDate column: 'EndDate'
        contentOwnerID column: 'contentOwnerID'
        views column: 'Views'
        watchTime column: 'WatchTime'
        subscriberGAINED column: 'SubscriberGAINED'
        subscriberLOST column: 'SubscriberLOST'
        likes column: 'Likes'
        disLikes column: 'DisLikes'
        comments column: 'Comments'
        favoritesAdded column: 'FavoritesAdded'
        shares column: 'Shares'
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
        //table:'tbl_cms'
        version false
    }
    static constraints = {
        startDate nullable: false
        endDate nullable: false
        contentOwnerID  nullable: false
        views nullable: false
        watchTime nullable: false
        subscriberGAINED nullable: false
        subscriberLOST nullable: false
        likes nullable: false
        disLikes nullable: false
        comments nullable: false
        favoritesAdded nullable: false
        shares nullable: false
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
