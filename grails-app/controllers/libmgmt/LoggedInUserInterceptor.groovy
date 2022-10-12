package libmgmt


// general interceptor and not related to any controller actions
class LoggedInUserInterceptor {

    public LoggedInUserInterceptor() {
        match controller: 'home'
        match controller: 'account', action: 'view'
    }

    boolean before() {
        if (!session.loggedInStudent) {
            redirect controller: 'account', action: 'login'
            return false
        }
        true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
