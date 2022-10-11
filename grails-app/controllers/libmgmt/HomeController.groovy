package libmgmt

class HomeController {

    static layout = 'public'

    def index() {
        if (!session.loggedInStudent) {
            redirect controller: 'account', action: 'login'
        }
        def books = Book.list(sort: 'id', order: 'desc')
        [books: books]
    }

    def details() {
        if (!session.loggedInStudent) {
            redirect controller: 'account', action: 'login'
        }
        def bookId = params['id']
        def book = Book.get(bookId)
        [book: book]
    }
}
