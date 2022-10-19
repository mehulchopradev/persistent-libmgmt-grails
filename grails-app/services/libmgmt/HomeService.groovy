package libmgmt

import grails.gorm.transactions.Transactional
import org.springframework.transaction.annotation.Propagation

@Transactional(propagation = Propagation.NOT_SUPPORTED)
class HomeService {

    HomeService homeService

    @Transactional
    def transferBook(Book book, Student fromStudent, Student toStudent) {
        homeService.returnBook(book, fromStudent)
        // deliberately create an error between two operations of a business function
        /* String x = null
        x.toUpperCase() */
        homeService.issueBook(book, toStudent)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
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

    @Transactional(propagation = Propagation.SUPPORTS)
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
