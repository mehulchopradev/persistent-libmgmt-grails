<!doctype html>
<html xmlns:g="http://www.w3.org/1999/html">
<head>
    <title>Welcome to Library Mgmt application :: Book details</title>
</head>
<body>
    <h2>Welcome!!!</h2>
    <div>
        <h2>${book.title}</h2>
        <b>Id: ${book.id}</b><br>
        <i>Price: ${book.price}</i><br>
        <i>Pages: ${book.pages}</i>
    </div>
    <div>
        <h2>Publication house details</h2>
        <p>Name: ${book.publicationHouse.name}</p>
        <p>Ratings: ${book.publicationHouse.ratings}</p>
    </div>
</body>
</html>
