package com.tan.com.tan.master

import grails.plugins.springsecurity.Secured

class AdminController {
    @Secured('IS_AUTHENTICATED_FULLY')
    def index() { }


}
