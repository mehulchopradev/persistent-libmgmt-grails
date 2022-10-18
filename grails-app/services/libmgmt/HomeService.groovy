package libmgmt

import grails.gorm.transactions.Transactional

@Transactional
class HomeService {

    def transferBook(Book book, Student fromStudent, Student toStudent) {
        def bookIssuedAlready = BookIssued.findByBookAndStudentAndReturnDate book, fromStudent, null
        if (!bookIssuedAlready) {
            throw new IllegalStateException("Book not actively issued")
        }

        // if yes, then return back the book from the student
        bookIssuedAlready.returnDate = new Date()
        bookIssuedAlready.save flush: true, failOnError: true

        
        def bookIssuedAlreadyToStudent = BookIssued.findByBookAndStudentAndReturnDate book, toStudent, null
        if (bookIssuedAlreadyToStudent) {
            // throw an exception to the caller
            throw new IllegalStateException("Book already actively issued to the Student")
        }

        // if yes, then check whether existing active BookIssued instances are same as the noOfCopies of the book
        def count = BookIssued.countByBookAndReturnDate book, null
        if (count == book.noOfCopies) {
            throw new IllegalStateException("Book copies exhausted")
        }

        // if no, then issue the book to the student
        def bi = new BookIssued(book: book, student: toStudent, issuedDate: new Date())
        bi.save flush: true, failOnError: true
    }

    def returnBook(Book book, Student student) {
        // First check whether the book is actively issued to the Student
        def bookIssuedAlready = BookIssued.findByBookAndStudentAndReturnDate book, student, null
        if (!bookIssuedAlready) {
            throw new IllegalStateException("Book not actively issued")
        }

        // if yes, then return back the book from the student
        bookIssuedAlready.returnDate = new Date()
        bookIssuedAlready.save flush: true, failOnError: true

        return bookIssuedAlready
    }

    def issueBook(Book book, Student student) {
        // First check whether the book is actively issued to the Student
        def bookIssuedAlready = BookIssued.findByBookAndStudentAndReturnDate book, student, null
        if (bookIssuedAlready) {
            // throw an exception to the caller
            throw new IllegalStateException("Book already actively issued to the Student")
        }

        // if yes, then check whether existing active BookIssued instances are same as the noOfCopies of the book
        def count = BookIssued.countByBookAndReturnDate book, null
        if (count == book.noOfCopies) {
            throw new IllegalStateException("Book copies exhausted")
        }

        // if no, then issue the book to the student
        def bi = new BookIssued(book: book, student: student, issuedDate: new Date())
        bi.save flush: true, failOnError: true

        return bi
    }
}
