<!doctype html>
<html xmlns:g="http://www.w3.org/1999/html">
<head>
    <title>Welcome to Library Mgmt application</title>
</head>
<body>
    <h2>Welcome!!!</h2>
    <table border="1">
        <thead>
            <tr>
                <td>Id</td>
                <td>Title</td>
                <td></td>
            </tr>
        </thead>
        <tbody>
            <g:each in="${books}" var="book">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.title}</td>
                    <td>
                        <g:link action="details" id="${book.id}">
                            View details
                        </g:link>
                    </td>
                </tr>
            </g:each>
        </tbody>
    </table>
</body>
</html>
