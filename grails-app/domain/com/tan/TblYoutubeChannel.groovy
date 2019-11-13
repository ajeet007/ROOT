package com.tan

class TblYoutubeChannel {

    String channelName
    String channelId
    Date   startDate

    static constraints = {
        channelName blank: false,nullable: false
        channelId blank: false,nullable: false
        startDate nullable: true
    }
    static mapping = {
        channelId column: 'channelId'
        id column:'id'
        channelName column: 'channelName'
        startDate column: 'startDate'
        table :'tbl_channel'
        version false
    }

}