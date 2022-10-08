package libmgmt

class Student {

    // by default get an id instance attribute
    // primary key, auto increment

    String username
    String password
    Character gender
    String country

    static constraints = {
        username maxSize: 50, email: true, unique: true
        password maxSize: 20
        country maxSize: 4, nullable: true
    }
}
