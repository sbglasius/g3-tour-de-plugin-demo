package tour

import groovy.json.JsonSlurper
import org.grails.io.support.DefaultResourceLoader

class BootStrap {

    def init = { servletContext ->
        if (!Team.count()) {
            def tourData = new DefaultResourceLoader().getResource('/tourData.json')
            println tourData.file.absolutePath
            importTeamData(tourData.inputStream)
        }
    }

    void importTeamData(InputStream tourData) {
        def json = new JsonSlurper().parse(tourData)
        json.teams.each { Map team ->

            println "Creating team: $team.name with ${team.riders.size()} riders"
            def newTeam = new Team(name: team.name, description: team.description)
            team.riders.each { Map rider ->
                newTeam.addToRiders(rider)
            }
            newTeam.save(failOnError: true)
        }

    }
    def destroy = {
    }
}
