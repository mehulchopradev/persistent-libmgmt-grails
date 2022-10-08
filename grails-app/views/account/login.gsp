<!doctype html>
<html xmlns:g="http://www.w3.org/1999/html">
<head>
    <title>Welcome to Library Mgmt application :: Login</title>
</head>
<body>
    <g:if test="${params.regSuccess}">
        <div class="success">
            Register success! Student created with id ${params.regSuccess}. Please login
        </div>
    </g:if>
    <h2>Login here!(${greeting})</h2>
    <form>
        <p>
            <input type="text" placeholder="Enter username">
        </p>
        <p>
            <input type="password" placeholder="Enter password">
        </p>
        <p>
            <input type="submit" value="Login">
        </p>
        <p>
            <g:link action="register">New User ? Sign up!!</g:link>
        </p>
    </form>
</body>
</html>
