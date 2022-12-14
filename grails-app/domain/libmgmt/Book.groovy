package libmgmt

class Book {

    // id

    String title
    Integer pages
    Double price
    PublicationHouse publicationHouse
    Integer noOfCopies

    Boolean isIssued // transient

    // belongs to a single publication house *:1
    // static belongsTo = [PublicationHouse, Student]
    static belongsTo = [PublicationHouse]

    // A Book can be issued to multiple Students - 1 : *
    // static hasMany = [issuedStudents: Student]

    static hasMany = [studentsIssued: BookIssued]

    static transients = ['isIssued']

    static constraints = {
        title maxSize: 50
        pages min: 1, max: 5000
        price nullable: true, min: 1.0d, max: 10000.0d, scale: 2
    }

    static mapping = {
        publicationHouse lazy: false, fetch: 'join'
    }

    static def expensive = Book.where { price > 1000 && pages < 500 }
}
