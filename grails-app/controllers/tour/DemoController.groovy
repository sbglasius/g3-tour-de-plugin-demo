package tour

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.Validateable
import org.grails.core.util.StopWatch

@Secured(['ROLE_ADMIN'])
class DemoController {
    def externalService
    def sendGridService
    static defaultAction = "search"

    def search(String query) {
        if (request.method == 'POST') {
            StopWatch stopwatch = new StopWatch()
            stopwatch.start()
            def result = externalService.slowQuery(query)
            stopwatch.stop()
            flash.message = "Result: ${result} (in ${stopwatch.lastTaskTimeMillis} ms)"
        }
        [query: query]
    }

    def evict(String query) {
        externalService.evictEntry(query)
        flash.message = "Evicted: $query"
        redirect(action: 'search', params: [query: query])
    }

    def evictAll() {
        externalService.evictAll()
        flash.message = 'Evicted all'
        redirect(action: 'search')
    }

    def mail(MailCommand mailCommand) {
        if(!mailCommand.hasErrors() && request.method == 'POST') {
            sendMail {
                to mailCommand.to
                subject mailCommand.subject
                text mailCommand.text
            }
            flash.message = "Sent mail to $mailCommand.to"
        } else {
            mailCommand.clearErrors()
        }
        [mailCommand: mailCommand]
    }

    def sendgrid(MailCommand mailCommand) {
        if (!mailCommand.hasErrors() && request.method == 'POST') {
            sendGridService.sendMail {
                from "demo1@gr8conf.org"
                to mailCommand.to
                subject mailCommand.subject
                body mailCommand.text
            }
            flash.message = "Sent mail to $mailCommand.to"
        } else {
            mailCommand.clearErrors()
        }
        [mailCommand: mailCommand]
    }
}

class MailCommand implements Validateable {
    String to
    String subject
    String text

    static constraints = {
        to email: true
        subject nullable: false
        text nullable: false, widget: 'textArea'
    }
}