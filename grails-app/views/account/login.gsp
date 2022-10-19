<!doctype html>
<html xmlns:g="http://www.w3.org/1999/html">
<head>
    <title>Welcome to Library Mgmt application :: Login</title>
</head>
<body>
    %{-- <g:if test="${params.regSuccess}">
        <div class="success">
            Register success! Student created with id ${params.regSuccess}. Please login
        </div>
    </g:if> --}%
    <g:if test="${flash.newStudentId}">
        <div class="success">
            Register success! Student created with id ${flash.newStudentId}. Please login
        </div>
    </g:if>
    <g:if test="${flash.errorMsg}">
        <div class="error">
            ${flash.errorMsg}
        </div>
    </g:if>
    <h2><g:message code="loginhere.label"/>!(${greeting})</h2>
    <g:form action="auth">
        <p>
            <g:textField name="username" placeholder="${message(code: 'enter.label', args: [message(code: 'username.label')])}"/>
        </p>
        <p>
            <g:passwordField name="password" placeholder="${message(code: 'enter.label', args: [message(code: 'password.label')])}"/>
        </p>
        <p>
            <g:submitButton name="submit" value="${message(code: 'login.label')}"/>
        </p>
        <p>
            <g:link action="register">New User ? Sign up!!</g:link>
        </p>
    </g:form>
</body>
</html>
