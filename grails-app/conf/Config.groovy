import com.google.common.collect.Lists

// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts
grails.config.locations = ["classpath:app-config.properties"]
// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if(System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
        xml: ['text/xml', 'application/xml'],
        text: 'text/plain',
        js: 'text/javascript',
        rss: 'application/rss+xml',
        atom: 'application/atom+xml',
        css: 'text/css',
        csv: 'text/csv',
        pdf: 'application/pdf',
        excel: 'application/vnd.ms-excel',
        all: '*/*',
        json: ['application/json','text/json'],
        form: 'application/x-www-form-urlencoded',
        multipartForm: 'multipart/form-data'
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// whether to install the java.util.logging bridge for sl4j. Disable for AppEngine!
grails.logging.jul.usebridge = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

grails.plugins.springsecurity.useHttpSessionEventPublisher = true

// set per-environment serverURL stem for creating absolute links
environments {

    production {

        runningMode = 'LIVE'
    }
    development {


        runningMode = 'DEV'

    }
    test {
        grails.serverURL = "http://localhost:8080/${appName}"
    }
}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
            'org.codehaus.groovy.grails.web.pages', //  GSP
            'org.codehaus.groovy.grails.web.sitemesh', //  layouts
            'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
            'org.codehaus.groovy.grails.web.mapping', // URL mapping
            'org.codehaus.groovy.grails.commons', // core / classloading
            'org.codehaus.groovy.grails.plugins', // plugins
            'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
            'org.springframework',
            'org.hibernate',
            'net.sf.ehcache.hibernate'

    warn   'org.mortbay.log'
}

jawr {
    js {
        // Specific mapping to disable resource handling by plugin.
        mapping = '/jawr/'

        bundle {
            lib {
                // Bundle id is used in views.
                id = '/i18n/messages.js'

                // Tell which messages need to localized in Javascript.
                mappings = 'messages:grails-app.i18n.messages'
            }
        }
    }
    locale {
        // Define resolver so ?lang= Grails functionality works with controllers.
        resolver = 'net.jawr.web.resource.bundle.locale.SpringLocaleResolver'
    }
}

grails {
    mail {
        host = "smtp.gmail.com"
        port = 465
        username = "look4ajit@gmail.com"
        password = "Ajeet@007"
        props = ["mail.smtp.auth": "true",
                "mail.smtp.socketFactory.port": "465",
                "mail.smtp.socketFactory.class": "javax.net.ssl.SSLSocketFactory",
                "mail.smtp.socketFactory.fallback": "false"]

    }
}

youtube{

    clientId="735774311656-l3cendabfuv61ibb8267l20kkofmr1tk.apps.googleusercontent.com"
    clientSecret="8Z4WhPTjwzpEEV4d261ViJj5"
    //scope ="https://gdata.youtube.com"
    scope = "https://www.googleapis.com/auth/yt-analytics.readonly+https://www.googleapis.com/auth/youtube.readonly"
   /* List<String> scope = Lists.newArrayList(
            "https://www.googleapis.com/auth/yt-analytics.readonly",
            "https://www.googleapis.com/auth/youtube.readonly"
    );*/
    responseType ="code"
    accessType="offline"
    //redirectUrl = "http://localhost:8080/ROOT/addChannel/channel"
    redirectUrl = "http://tanzilla.com:8080/ROOT/youtube/callBack"

    url{
        auth =  "https://accounts.google.com/o/oauth2/auth"
        token = "https://accounts.google.com/o/oauth2/token"
        userInfo = "https://www.googleapis.com/oauth2/v2/userinfo"
        dataApi = "https://www.googleapis.com/youtube/v3"
    }

}

// Added by the Spring Security Core plugin:

grails.plugins.springsecurity.userLookup.userDomainClassName = 'com.tan.User'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'com.tan.UserRole'
grails.plugins.springsecurity.authority.className = 'com.tan.Role'
grails.plugins.springsecurity.successHandler.defaultTargetUrl = '/youTubeChannel/analyticsReport'
