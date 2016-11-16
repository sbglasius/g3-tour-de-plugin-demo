package tour

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
}
