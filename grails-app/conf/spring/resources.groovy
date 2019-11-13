// Place your Spring DSL code here


import org.apache.commons.dbcp.BasicDataSource
import org.springframework.jndi.JndiObjectFactoryBean
import grails.util.GrailsUtil
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.springframework.security.core.session.SessionRegistryImpl
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.session.ConcurrentSessionControlStrategy
import org.springframework.security.web.session.ConcurrentSessionFilter
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils


beans = {
    userDetailsService(tan.CustomLoginService)
    sessionRegistry(SessionRegistryImpl)
    concurrencyFilter(ConcurrentSessionFilter) {
        sessionRegistry = sessionRegistry
        logoutHandlers = [ref("rememberMeServices"), ref("securityContextLogoutHandler")]
        expiredUrl='/login/auth'
    }
    concurrentSessionControlStrategy(ConcurrentSessionControlStrategy, sessionRegistry) {
        alwaysCreateSession = true
        exceptionIfMaximumExceeded = false
        maximumSessions = 1
    }
    switch(GrailsUtil.environment) {
        case GrailsApplication.ENV_PRODUCTION:
            log.info "creating my special production Jndi datasource"
            mySpecialDatasource(BasicDataSource)  {
                driverClassName = '${dataSource.driverClassName}'
                url = '${dataSource.url}'
                username = '${dataSource.username}'
                password = '${dataSource.password}'
                properties {
                    /*maxActive = -1
                    minEvictableIdleTimeMillis=1800000
                    timeBetweenEvictionRunsMillis=1800000
                    numTestsPerEvictionRun=3
                    testOnBorrow=true
                    testWhileIdle=true
                    testOnReturn=true*/
                    validationQuery="SELECT 1"

                }
            }
            break
        default:
            log.info "creating my special development/test datasource"
            mySpecialDatasource(BasicDataSource)  {
                driverClassName = "com.mysql.jdbc.Driver"
                url = '${dataSource.url}'
                username = '${dataSource.username}'
                password = '${dataSource.password}'
                properties {
                    /* maxActive = -1
                     minEvictableIdleTimeMillis=1800000
                     timeBetweenEvictionRunsMillis=1800000
                     numTestsPerEvictionRun=3
                     testOnBorrow=true
                     testWhileIdle=true
                     testOnReturn=true*/
                    validationQuery="SELECT 1"
                }

            }
            break;
    }
    authenticationSuccessHandler(MyAuthSuccessHandler) {
        def conf = SpringSecurityUtils.securityConfig
        requestCache = ref('requestCache')
        defaultTargetUrl = conf.successHandler.defaultTargetUrl
        alwaysUseDefaultTargetUrl = conf.successHandler.alwaysUseDefault
        targetUrlParameter = conf.successHandler.targetUrlParameter
        useReferer = conf.successHandler.useReferer
        redirectStrategy = ref('redirectStrategy')
        adminUrl = "/admin/index"
        editorUrl = "/youTubeChannel/analyticsReport"

    }


}

