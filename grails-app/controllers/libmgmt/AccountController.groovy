package libmgmt

import org.springframework.dao.OptimisticLockingFailureException

import java.time.LocalTime
import grails.validation.ValidationException

class AccountController {

    static layout = 'public'

    def greetingsService

    def index() { }

    // controller can have multiple action functions
    // login
    // register
    // forgotPassword

    // /account/login
    def login() {
        if (session.loggedInStudent) {
            redirect controller: 'home'
        }

        // avoid!!!
        // def message = new GreetingsService().greetingOfTheDay()

        // Dependency injection (DI) --- Inversion of control (IOC)
        // Spring container --- Manages the lifecyle of the spring beans (objects)

        [greeting: greetingsService.greetingOfTheDay()]
    }

    // /account/register
    def register() {
        if (session.loggedInStudent) {
            redirect controller: 'home'
        }

        // groovy code to get the countries from the database
        def countries = ['IN': 'India', 'US': 'USA', 'AU': 'Australia', 'JP': 'Japan']

        // groovy code to get the genders from the database
        def genders = ['M': 'Male', 'F': 'Female']

        [countries: countries, genders: genders]
    }

    def createStudent() {
        // implicit variable called as `params`
        // request data will be store in this variable
        // println params
        /* def username = params['username']
        def password = params['password']
        def country = params['country']
        def gender = params['gender'] as Character

        def s1 = new Student(username: username, password: password, gender: gender, country: country) */
        def s1 = new Student(params)
        // s1.save() // does not immediately hit the database with the insert query. Mostly does it after the response of this action
        // is sent to the client

        try {
            s1.save(flush: true, failOnError: true) // immediately hit the database with the insert query
        } catch (err) {
            // groovy code to get the countries from the database
            def countries = ['IN': 'India', 'US': 'USA', 'AU': 'Australia', 'JP': 'Japan']

            // groovy code to get the genders from the database
            def genders = ['M': 'Male', 'F': 'Female']
            render view: 'register', model: [student: s1, countries: countries, genders: genders]
            return
        }

        // render "Register success!!! Student created with id ${s1.id}"

        // redirect action: 'login', params: ['regSuccess': s1.id]

        // flash implicit object
            // stores any data at the server side for the request that comes from the client
            // scope of this data is till the next HTTP request completes
        flash.newStudentId = s1.id
        redirect action: 'login'
    }

    def auth() {
        def username = params['username']
        def password = params['password']

        // verify whether the database has a Student with the above username and password
        def student = Student.findByUsernameAndPassword(username, password)
        if (!student) {
            flash.errorMsg = 'Invalid username or password'
            redirect action: 'login'
        } else {
            // session scope -- HttpSession
            // session implicit object
            session.loggedInStudent = student
            redirect controller: 'home' // by default action should be `index`
        }
    }

    def logout() {
        session.invalidate()
        redirect action: 'login'
    }

    def view() {
        // groovy code to get the countries from the database
        def countries = ['IN': 'India', 'US': 'USA', 'AU': 'Australia', 'JP': 'Japan']

        // groovy code to get the genders from the database
        def genders = ['M': 'Male', 'F': 'Female']

        [countries: countries, genders: genders]
    }

    def updateStudent() {
        // Update the Student domain entity
        def student = session.loggedInStudent

        def file = request.getFile('profilePic')
        if (!file.empty) {
            file.transferTo(new File("/Users/mehulchopra/Downloads/${student.id}.jpg"))
        }

        // add it to the hibernate session
        student.attach()

        /* student.username = params['username']
        student.password = params['password']
        student.country = params['country']
        student.gender = params['gender'] */

        student.properties = params

        // dirty checking
        try {
            student.save(flush: true, failOnError: true) // adds the entity to the hibernate session
        } catch(ValidationException e) {
            // exception thrown and caught here only when failOnError is true
            e.printStackTrace()
            render "Error in updating profile"
            return
        } catch(OptimisticLockingFailureException e) {
            e.printStackTrace()
            student.refresh() // re reads the latest changes from the database and updates the student domain instance
            render "Oops someone else updated whilst you were updating. Please reload and try again"
        }

        redirect action: 'view'
    }

    def profileImage() {
        def student = session.loggedInStudent
        // Try out!
        // For a user with no uploaded pic, send in the response a no image dummy pic
        render file: new File("/Users/mehulchopra/Downloads/${student.id}.jpg"), contentType: 'image/*'
    }
}
