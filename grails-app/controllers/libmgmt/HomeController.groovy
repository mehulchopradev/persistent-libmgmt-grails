package libmgmt

import java.time.LocalTime

class HomeController {

    static layout = 'public'

    GreetingsService greetingsService

    HomeService homeService

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

        try {
            homeService.issueBook(book, student)
        } catch (IllegalStateException e) {
            e.printStackTrace()
            render status: 409, text: e.message
            return
        } catch (err) {
            err.printStackTrace()
            render status: 500, text: 'Something went wrong'
            return
        }

        redirect action: 'index'
    }

    def returnBook() {
        def bookId = params.id
        def book = Book.get bookId
        def student = session.loggedInStudent

        try {
            homeService.returnBook(book, student)
        } catch (IllegalStateException e) {
            e.printStackTrace()
            render status: 409, text: e.message
            return
        } catch (err) {
            err.printStackTrace()
            render status: 500, text: 'Something went wrong'
            return
        }

        redirect action: 'index'
    }

    def showTransferPage() {
        def bookId = params.id
        def student = session.loggedInStudent

        def students = Student.findAllByIdNotEqual student.id

        [book: Book.get(bookId), students: students]
    }

    def performTransfer() {
        def bookId = params.bookId
        def toStudentId = params.studentId

        def book = Book.get bookId
        def fromStudent = session.loggedInStudent
        def toStudent = Student.get toStudentId

        try {
            homeService.transferBook(book, fromStudent, toStudent)
        } catch (IllegalStateException e) {
            e.printStackTrace()
            render status: 409, text: e.message
            return
        } catch (err) {
            err.printStackTrace()
            render status: 500, text: 'Something went wrong'
            return
        }

        redirect action: 'index'
    }
}
