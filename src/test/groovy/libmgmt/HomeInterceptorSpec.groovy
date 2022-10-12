package libmgmt

import grails.testing.web.interceptor.InterceptorUnitTest
import spock.lang.Specification

class HomeInterceptorSpec extends Specification implements InterceptorUnitTest<HomeInterceptor> {

    def setup() {
    }

    def cleanup() {

    }

    void "Test home interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"home")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
