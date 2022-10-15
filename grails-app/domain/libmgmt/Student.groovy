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

    // a Student can issue multiple Books - 1 : *
    // by default lazy
    // static hasMany = [issuedBooks: Book] // addTo* - addToIssuedBooks(b), removeFrom* - removeFromIssuedBooks(b)

    static hasMany = [booksIssued: BookIssued]

    static constraints = {
        username maxSize: 50, email: true, unique: true
        password maxSize: 20
        country maxSize: 4, nullable: true
        contact nullable: true
    }
}
