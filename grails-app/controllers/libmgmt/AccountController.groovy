package libmgmt

import java.time.LocalTime

class AccountController {

    static layout = 'public'

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

        def now = LocalTime.now()
        def hour = now.hour
        def message
        if (hour > 0 && hour < 12) {
            message = 'Good morning'
        } else if (hour >= 12 && hour < 16) {
            message = 'Good afternoon'
        } else {
            message = 'Good evening'
        }

        [greeting: message]
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

        s1.save(flush: true) // immediately hit the database with the insert query

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

        // add it to the hibernate session
        student.attach()

        student.username = params['username']
        student.password = params['password']
        student.country = params['country']
        student.gender = params['gender']

        // dirty checking

        // student.save(flush: true)

        redirect action: 'view'
    }
}
