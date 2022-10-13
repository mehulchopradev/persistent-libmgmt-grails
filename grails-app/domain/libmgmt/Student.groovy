package libmgmt

class Student {

    // by default get an id instance attribute
    // primary key, auto increment

    String username
    String password
    Character gender
    String country

    // Contact contact

    // a Student can have a Contact - 1 : 1
    static hasOne = [contact: Contact]

    static constraints = {
        username maxSize: 50, email: true, unique: true
        password maxSize: 20
        country maxSize: 4, nullable: true
        contact nullable: true
    }
}
