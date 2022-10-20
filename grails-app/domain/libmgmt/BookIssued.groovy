package libmgmt

// which represents the intermediate relationship table in the many to many relationship
class BookIssued implements Serializable {

    // id
    // version

    Book book
    Student student
    Date issuedDate
    Date returnDate

    static belongsTo = [Book, Student]

    static constraints = {
        returnDate nullable: true
    }

    static mapping = {
        id composite: ['book', 'student', 'issuedDate']
    }

    @Override
    String toString() {
        "${book.id}-${this.issuedDate}"
    }
}
