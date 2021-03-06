package tour

class Team {
    String name
    String description

    String toString() {
        name
    }

    static constraints = {
        description nullable: true, widget: 'textArea'
    }

    static hasMany = [riders: Rider]

    static mapping = {
        description type: 'text'
    }
}
