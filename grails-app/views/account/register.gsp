<!doctype html>
<html xmlns:g="http://www.w3.org/1999/html">
<head>
    <title>Welcome to Library Mgmt application :: Register</title>
</head>
<body>
    <h2>Register here!</h2>
    <form>
        <p>
            <input type="text" placeholder="Enter username">
        </p>
        <p>
            <input type="password" placeholder="Enter password">
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
            <input type="submit" value="Register">
        </p>
    </form>
</body>
</html>
