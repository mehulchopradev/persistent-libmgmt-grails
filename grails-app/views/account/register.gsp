<!doctype html>
<html xmlns:g="http://www.w3.org/1999/html">
<head>
    <title>Welcome to Library Mgmt application :: Register</title>
</head>
<body>
    <h2>Register here!</h2>
    <g:form action="createStudent">
        <p>
            <g:textField name="username" placeholder="Enter username"/>
        </p>
        <p>
            <g:passwordField name="password" placeholder="Enter password"/>
        </p>
        <p>
            Country:
            %{-- <select>
                <g:each var="country" in="${countries}">
                    <option value="${country.key}">${country.value}</option>
                </g:each>
            </select>--}%

            <g:select
                    name="country"
                    from="${countries}"
                    optionValue="value"
                    optionKey="key"
            />
        </p>
        <p>
            Gender:
            %{-- <g:each var="gender" in="${genders}">
                <input type="radio" name="gender" value="${gender.key}">${gender.value}
            </g:each> --}%
            <g:radioGroup
                    name="gender"
                    values="${genders.keySet()}"
                    labels="${genders.values()}" value="F">
                    ${it.radio}${it.label}
            </g:radioGroup>
        </p>
        <p>
            <g:submitButton name="submit" value="Register"/>
        </p>
    </g:form>
</body>
</html>
