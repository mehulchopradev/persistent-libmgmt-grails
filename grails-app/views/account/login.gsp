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
    <h2>Login here!(${greeting})</h2>
    <g:form action="auth">
        <p>
            <g:textField name="username" placeholder="Enter username"/>
        </p>
        <p>
            <g:passwordField name="password" placeholder="Enter password"/>
        </p>
        <p>
            <g:submitButton name="submit" value="Login"/>
        </p>
        <p>
            <g:link action="register">New User ? Sign up!!</g:link>
        </p>
    </g:form>
</body>
</html>
