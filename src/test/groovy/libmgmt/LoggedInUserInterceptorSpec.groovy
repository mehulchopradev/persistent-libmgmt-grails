package libmgmt

import grails.testing.web.interceptor.InterceptorUnitTest
import spock.lang.Specification

class LoggedInUserInterceptorSpec extends Specification implements InterceptorUnitTest<LoggedInUserInterceptor> {

    def setup() {
    }

    def cleanup() {

    }

    void "Test loggedInUser interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"loggedInUser")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
