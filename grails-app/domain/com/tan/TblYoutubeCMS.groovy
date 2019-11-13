package com.tan

class TblYoutubeCMS {

    String cmsName
    String contentOwnerID
    Date startDate

    static constraints = {
        cmsName blank: false,nullable: false
        contentOwnerID blank: false,nullable: false
        startDate nullable: true
    }
    static mapping = {
        contentOwnerID column: 'contentOwnerID'
        id column:'id'
        cmsName column: 'cmsName'
        startDate column: 'startDate'
        //table :'tbl_youtubeCMS'
        version false
    }
}