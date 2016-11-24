package tour

import grails.validation.Validateable
import org.grails.core.util.StopWatch

class DemoController {
    def externalService

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