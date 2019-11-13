package com.tan

class TblYoutube {

    Integer expires_in
    String token_type
    String refresh_token
    String access_token


    static constraints = {
        expires_in blank: false, nullable: false
        token_type blank: false, nullable: false
        refresh_token blank: false, nullable: false
        access_token blank: false, nullable: false
    }
    static mapping = {
        expires_in column: 'expiresIN'
        id column: 'id'
        token_type column: 'tokenType'
        refresh_token column: 'refreshToken'
        access_token column: 'accessToken'
        version false
    }
    static void updateAccessToken(token) {
        executeUpdate 'UPDATE TblYoutube set access_token =:token', [token:token]
    }
    static void deleteRecord(){
        executeUpdate 'delete from TblYoutube'
    }
}

