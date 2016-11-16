package tour.de.plugin.demo

import grails.plugin.cache.CacheEvict
import grails.plugin.cache.Cacheable

class ExternalService {

    @Cacheable(value = 'externalData', key = '#query')
    def slowQuery(String query) {
        println("Query for $query")
        sleep(1000)
        return "Slowly queried for: $query"
    }

    @CacheEvict(value = 'externalData', key = '#query')
    void evictEntry(String query) {
        println("Evicted $query")
    }

    @CacheEvict(value = 'externalData', allEntries = true)
    void evictAll() {
        println("Evicted all")
    }
}
