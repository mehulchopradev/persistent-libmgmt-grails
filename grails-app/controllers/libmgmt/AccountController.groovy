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
        // groovy code to get the countries from the database
        def countries = ['IN': 'India', 'US': 'USA', 'AU': 'Australia', 'JP': 'Japan']
        [countries: countries]
    }
}
