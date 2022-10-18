<!doctype html>
<html xmlns:g="http://www.w3.org/1999/html">
<head>
    <title>Welcome to Library Mgmt application :: Transfer book</title>
</head>
<body>
    <h2>Welcome!!!</h2>
    <div>
        <h2>${book.title}</h2>
        <b>Id: ${book.id}</b><br>
    </div>
    <g:form action="performTransfer">
        <div>
            Students:<br/>
            <g:select
                name="studentId"
                from="${students}"
                optionValue="username"
                optionKey="id"
            />
        </div>
        <div>
            <g:hiddenField name="bookId" value="${book.id}"/>
            <g:submitButton name="submit" value="Transfer"/>
        </div>
    </g:form>
</body>
</html>
