package com.tan

import grails.converters.JSON

class TestController {

    def index() {

       def test="hello";
        render test;
    }

}
