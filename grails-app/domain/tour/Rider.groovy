package tour

class Rider {
    String name
    String image
    Integer weight
    Date born
    Speciality speciality

    String toString() {
        name
    }

    static constraints = {
        weight nullable: true
        speciality nullable: true
    }

    static belongsTo = [team: Team]
}
