package libmgmt

class Book {

    // id

    String title
    Integer pages
    Double price

    static constraints = {
        title maxSize: 50
        pages min: 1, max: 5000
        price nullable: true, min: 1.0d, max: 10000.0d, scale: 2
    }
}
