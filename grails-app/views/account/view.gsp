<!doctype html>
<html xmlns:g="http://www.w3.org/1999/html">
<head>
    <title>Welcome to Library Mgmt application</title>
</head>
<body>
    <h2>Welcome!!!</h2>
    <g:uploadForm action="updateStudent">
        <p>
            <g:textField name="username" placeholder="Enter username" value="${session.loggedInStudent.username}"/>
        </p>
        <p>
            <g:passwordField name="password" placeholder="Enter password" value="${session.loggedInStudent.password}"/>
        </p>
        <p>
            Country:
            <g:select
                    name="country"
                    from="${countries}"
                    optionValue="value"
                    optionKey="key"
                    value="${session.loggedInStudent.country}"
            />
        </p>
        <p>
            Gender:
            <g:radioGroup
                    name="gender"
                    values="${genders.keySet()}"
                    labels="${genders.values()}" value="${session.loggedInStudent.gender}">
                ${it.radio}${it.label}
            </g:radioGroup>
        </p>
        <p>
            <g:textField
                    name="contact.mobile"
                    placeholder="Enter mobile"
                    value="${session.loggedInStudent.contact?.mobile}"/>
        </p>
        <p>
            <g:textArea
                    name="contact.address"
                    placeholder="Enter full address"
                    value="${session.loggedInStudent.contact?.address}"/>
        </p>
        <p>
            <input type="file" name="profilePic"/>
        </p>
        <p>
            <g:submitButton name="submit" value="Update"/>
        </p>
    </g:uploadForm>
</body>
</html>
