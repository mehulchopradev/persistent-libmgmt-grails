package libmgmt

// will be called only for all the actions of the Home controller
class HomeInterceptor {

    // called just before the corresponding controller action executes
    boolean before() {
        /* if (!session.loggedInStudent) {
            redirect controller: 'account', action: 'login'
            return false
        } */
        true
    }

    // called just after the corresponding controller action finishes execution
    boolean after() { true }

    // called after the gsp response is sent to the browser
    void afterView() {
        // no-op
    }
}
