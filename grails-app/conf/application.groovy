grails {
    mail {
        port = com.icegreen.greenmail.util.ServerSetupTest.SMTP.port
//        host = "smtp.gmail.com"
//        port = 465
//        username = "demo1@gr8conf.org"
//        props = ["mail.smtp.auth"                  : "true",
//                 "mail.smtp.socketFactory.port"    : "465",
//                 "mail.smtp.socketFactory.class"   : "javax.net.ssl.SSLSocketFactory",
//                 "mail.smtp.socketFactory.fallback": "false"]
    }
}

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'tour.de.plugin.demo.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'tour.de.plugin.demo.UserRole'
grails.plugin.springsecurity.authority.className = 'tour.de.plugin.demo.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

