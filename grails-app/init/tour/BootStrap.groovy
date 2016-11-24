package tour

import groovy.json.JsonSlurper
import org.grails.io.support.DefaultResourceLoader
import tour.de.plugin.demo.Role
import tour.de.plugin.demo.User
import tour.de.plugin.demo.UserRole

class BootStrap {

    def init = { servletContext ->
        if(!User.count() && !Role.count()) {
            initUsers()
        }

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

    void initUsers() {
        def adminRole = new Role(authority: 'ROLE_ADMIN').save()
        def userRole = new Role(authority: 'ROLE_USER').save()

        def adminUser = new User(username: 'admin', password: 'password').save()
        def regularUser = new User(username: 'user', password: 'password').save()

        UserRole.create adminUser, adminRole
        UserRole.create regularUser, userRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }

        assert User.count() == 2
        assert Role.count() == 2
        assert UserRole.count() == 2
    }
    def destroy = {
    }
}
