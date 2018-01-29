<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Room list</title>
    <style>
        table, tr {border: 1px solid black; margin: 0; padding: 0}
        td {border: 1px solid black; margin: 0; padding: 0; width: 150px}
    </style>
</head>
<body>
    <div style="float: right">
        <form action="logout" method="post" style="right: 0">
            <c:out value="Hello, ${sessionScope.user.fullName}."/>
            <input type="submit" value="Log out">
        </form>
    </div>
    <div>
        <table>
            <tr>
                <td align="center">Room number</td>
                <td align="center">Room capacity</td>
                <td align="center">Room status</td>
                <td align="center">Room class</td>
                <td align="center">Room price</td>
                <td ></td>
            </tr>
            <c:forEach var="room" items="${rooms}" >
                <tr>
                    <td align="center"><c:out value="${room.number}" /></td>
                    <td align="center"><c:out value="${room.capacity}" /></td>
                    <td align="center"><c:out value="${room.status}" /></td>
                    <td align="center"><c:out value="${room.roomClass}" /></td>
                    <td align="center"><c:out value="${room.price}" /></td>
                    <td align="center">
                    <form method="post" action="/bookingroom">
                        <input type="text" id="roomNumber" hidden value="${room.number}" name="roomNumber">
                        <input type="submit" value="Book" style="margin-top: 9px">
                    </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
