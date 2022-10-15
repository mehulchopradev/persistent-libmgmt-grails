package libmgmt

import grails.gorm.transactions.Transactional

import java.time.LocalTime

// never include grails controller implicit objects in a service
// a service should have business functions that play only with simple java and groovy data types

@Transactional
class GreetingsService {

    String greetingOfTheDay() {
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
        message
    }
}
