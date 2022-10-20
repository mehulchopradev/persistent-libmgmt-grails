import libmgmt.Student
import libmgmt.Book
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