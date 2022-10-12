package libmgmt

class HomeController {

    static layout = 'public'

    def index() {
        def books = Book.list(sort: 'id', order: 'desc')
        [books: books]
    }

    def details() {
        def bookId = params['id']
        def book = Book.get(bookId)
        [book: book]
    }
}
