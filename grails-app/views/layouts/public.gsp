<!doctype html>
<html xmlns:g="http://www.w3.org/1999/html">
<head>
    <title><g:layoutTitle /></title>
    <asset:stylesheet src="libmgmt.css"/>
    <g:layoutHead />
</head>
<body>
    <g:if test="${session.loggedInStudent}">
        <div class="account-actions">
            <span>${session.loggedInStudent.username}</span>
            <a href="#">Edit profile</a>
            <g:link controller="account" action="logout">
                Logout
            </g:link>
        </div>
    </g:if>
    <asset:image src="library-banner.jpeg" />
    <g:layoutBody />
    <footer>
        Copyright 2022. All Rights Reserved
    </footer>
</body>
</html>
