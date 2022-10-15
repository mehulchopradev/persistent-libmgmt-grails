package libmgmt

import java.time.LocalTime

class HomeController {

    static layout = 'public'

    GreetingsService greetingsService

    def index() {
        def books = Book.list(sort: 'id', order: 'desc')

        // querying for all the Books currently issued To the logged in Student
        def student = session.loggedInStudent
        def booksCurrentlyIssued = BookIssued.findAllByStudentAndReturnDate student, null

        books.each {Book book ->
            if (booksCurrentlyIssued.find {BookIssued bookIssued -> bookIssued.book == book}) {
                // book is issued to the logged in student
                book.isIssued = true
            } else {
                // book is not issued to the logged in student
                book.isIssued = false
            }
        }

        [books: books, message: greetingsService.greetingOfTheDay()]
    }

    def details() {
        def bookId = params['id']
        def book = Book.get(bookId)
        [book: book]
    }

    def issueBook() {
        def bookId = params.id
        def student = session.loggedInStudent

        def book = Book.get bookId

        def bi = new BookIssued(book: book, student: student, issuedDate: new Date())
        try {
            bi.save flush: true, failOnError: true
        } catch (err) {
            err.printStackTrace()
            render "Error in issuing book"
            return
        }

        redirect action: 'index'
    }

    def returnBook() {
        def bookId = params.id
        def book = Book.get bookId
        def student = session.loggedInStudent

        def bi = BookIssued.findByBookAndStudentAndReturnDate book, student, null
        bi.returnDate = new Date()

        try {
            bi.save flush: true, failOnError: true
        } catch (err) {
            err.printStackTrace()
            render "Error in returning a book"
            return
        }

        redirect action: 'index'
    }
}
