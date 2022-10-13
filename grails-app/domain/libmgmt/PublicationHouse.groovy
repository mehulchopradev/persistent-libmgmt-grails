package libmgmt

class PublicationHouse {

    String name

    Integer ratings

    // can publish many books - 1:*

    // hasMany relationship is always lazy
    static hasMany = [books: Book]

    static constraints = {
        name maxSize: 20, unique: true
        ratings range: 1..5
    }

    static mapping = {
        books lazy: false, fetch: 'join'
    }

    String toString() {
        this.name
    }
}
