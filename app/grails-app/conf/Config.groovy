import grails.util.Environment

grails.config.locations = [
  "file:${userHome}/.grails/${appName}-${Environment.current}-config.groovy"
]

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [ // the first one is the default format
    all:           '*/*', // 'all' maps to '*' or the first available format in withFormat
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        // filteringCodecForContentType.'text/html' = 'html'
    }
}


grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

// configure passing transaction's read-only attribute to Hibernate session, queries and criterias
// set "singleSession = false" OSIV mode in hibernate configuration after enabling
grails.hibernate.pass.readonly = false
// configure passing read-only to OSIV session by default, requires "singleSession = false" OSIV mode
grails.hibernate.osiv.readonly = false

def logFolder = 'target'
environments {
  development {
    grails.logging.jul.usebridge = true
  }
  test { }
  production {
    grails.logging.jul.usebridge = false
    logFolder = System.getProperty('catalina.base') ?: 'logs'
  }
}

// log4j configuration
log4j.main = {
  appenders {
    appender new org.apache.log4j.DailyRollingFileAppender(
      name: "myAppender",
      layout: pattern(conversionPattern: '%d{ISO8601}\t%p\t%c:%L\t%m%n'),
      file: "${logFolder}/tim-integradora.log")
  }

  root {
    debug 'myAppender'
  }

  debug 'grails.app.controllers.com.tim.one.integradora',
        'grails.app.taglib.com.tim.one.integradora',
        'grails.app.services.com.tim.one.integradora',
        'grails.app.domain.com.tim.one.integradora',
        'grails.app.jobs.com.tim.one.integradora',
        'grails.app.conf',
        'groovyx'

  warn  'org.codehaus.groovy.grails.web.servlet',        // controllers
        'org.codehaus.groovy.grails.web.pages',          // GSP
        'org.codehaus.groovy.grails.web.sitemesh',       // layouts
        'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
        'org.codehaus.groovy.grails.web.mapping',        // URL mapping
        'org.codehaus.groovy.grails.commons',            // core / classloading
        'org.codehaus.groovy.grails.plugins',            // plugins
        'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
        'org.springframework',
        'org.hibernate',
        'net.sf.ehcache.hibernate',
        'org.codehaus',
        'org.springframework',
        'org.hibernate',
        'org.mortbay.log',
        'net.sf',
        'org.apache',
        'grails.spring',
        'net.sf.ehcache.hibernate',
        'org.grails.tomcat',
        'grails.plugin',
        'org.grails.plugin.resource',
        'org.jboss',
        'grails.app.resourceMappers',
        'liquibase',
        'org.grails.datastore',
        'org.hibernate.tool',
        'grails.plugin.databasemigration',
        'grails.app.jobs',
        'grails.plugins.quartz'

  environments {
    production {
      error "grails",
      "org",
      "net",
      "com",
      "groovyx",
      "net.bull.javamelody",
      "httpclient"
    }
  }
}
