package com.tan

import grails.plugins.springsecurity.Secured

class HomeController {
    @Secured('IS_AUTHENTICATED_FULLY')
    def index() {}
    @Secured('IS_AUTHENTICATED_FULLY')
    def composead(){}

}
