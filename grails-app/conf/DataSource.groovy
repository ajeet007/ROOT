dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
    dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update" // one of 'create', 'create-drop','update'
            url = "jdbc:mysql://localhost/tanzilla_DB?useUnicode=yes&characterEncoding=UTF-8"
            username = "root"
            password = "root"
        }
/*
        hibernate {
            show_sql = true
        }
*/
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:hsqldb:mem:testDb"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            driverClassName = ""
            url = ""
            username = ""
            password = ""
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
            /*  dbCreate = "update"
               driverClassName = "com.mysql.jdbc.Driver"

              //This is for local server configuration
               // url = "jdbc:mysql://localhost/adpro2?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=UTF-8&characterSetResults=UTF-8&autoReconnect=true"
               //This is for public server configuration

               url = "jdbc:mysql://localhost/adpro2?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=UTF-8&characterSetResults=UTF-8&autoReconnect=true"
               username = "adpro1"
               password = "adpro1"
               dialect = org.hibernate.dialect.MySQL5InnoDBDialect
             */

        }
    }
}