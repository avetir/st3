<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>

</head>
<body>
    <div style="float: right">
        <form action="logout" method="post" style="right: 0">
            <c:out value="Hello, ${sessionScope.user.fullName}."/>
            <input type="submit" value="Log out">
        </form>
    </div>
    <div>
        <form action="rooms" method="get">
            <input type="submit" value="Watch room list">
        </form>
        <form action="booking" method="get">
            <input type="submit" value="Do a booking">
        </form>
    </div>
</body>
</html>
