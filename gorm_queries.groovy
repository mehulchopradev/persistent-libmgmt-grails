import libmgmt.Student
import libmgmt.Book
import libmgmt.PublicationHouse
// import java.time.LocalDate
import libmgmt.BookIssued


def s1 = Student.list()
println s1

// supports sorting
def s2 = Student.list sort: 'id', order: 'desc' // asc
println s2

// supports pagination
def s3 = Student.list sort: 'id', order: 'desc', max: 5, offset: 0 // first page of students
println s3

def s4 = Student.list sort: 'id', order: 'desc', max: 5, offset: 5 // second page of students
println s4

// record by id
def s5 = Student.get(5)
println s5

// record by ids
def s6 = Student.getAll(5, 3, 1)
println s6

// dynamic finders
// DomainClassName.findAllBy<<PropertyName>><<Comparator>><<Conjunction>><<PropertyName>><<Comparator>>...........

def b1 = Book.findAllByPriceGreaterThan 600
println b1

def b2 = Book.findAllByPriceGreaterThanAndPagesLessThan 600, 1000
println b2

def b3 = Book.findAllByTitleIlike '%prog%'
println b3

// def d1 = LocalDate.of(2022, 10, 15)
// def d2 = LocalDate.of(2022, 10, 18)
def c = Calendar.getInstance()
c.set(2022, 9, 15)
def d1 = c.getTime()

c.set(2022, 9, 18)
def d2 = c.getTime()

def b4 = BookIssued.findAllByIssuedDateBetween d1, d2
println b4

// where()
/* def males = Student.where {
    gender == 'M'
}.findAll() */
// println males

def usFemales = Student.where {
    gender == 'F' && country == 'US'
}.findAll()
println usFemales

def books = Book.where {
    price > 500 && pages < 1000
}.findAll()
println books

def usAuStudents = Student.where {
    country in ['US', 'AU']
}.findAll()
println usAuStudents

def males = Student.males.findAll()
def females = Student.females.count()
println males
println females

println Book.expensive.list()

println Book.where {
    publicationHouse.ratings > 3
}.list()

/* println PublicationHouse.where {
    books.price > 1000 && books.pages < 500
}.list() */

println PublicationHouse.where {
    books { price > 1000 && pages < 500 }
}.list()


println Book.where {
    price > avg(price)
}.list()


// criteria queries
def criteria = PublicationHouse.createCriteria()
def results = criteria {
    gt('ratings', 3)
}
println results

criteria = Book.createCriteria()
results = criteria {
    gt('price', 1000.0d)
    lt('pages', 500)
}
println results

criteria = Student.createCriteria()
results = criteria {
    or {
        eq('gender', 'F' as Character)
        eq('country', 'US')
    }
    order('id', 'desc')
}
println results


criteria = Book.createCriteria()
results = criteria {
    publicationHouse {
        gt('ratings', 3)
    }
    order('title', 'asc')
}
println results

/* criteria = PublicationHouse.createCriteria()
results = criteria {
    books {
        gt('price', 1000.0d)
        lt('pages', 500)
    }
}
println results */

// HQL (Hibernate query language)
results = Book.findAll("from Book as b")
println results

results = PublicationHouse.findAll("from PublicationHouse as p where p.ratings > :ratings", [ratings: 3])
println results

results = Book.findAll("""
    from Book as b 
        where 
        b.price > :price 
        and 
        b.pages < :pages""", [price: 1000.0d, pages: 500])
println results
