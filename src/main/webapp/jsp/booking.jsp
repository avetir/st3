<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<html>
    <body>
        <div style="float: right">
            <form action="logout" method="post" style="right: 0">
                <c:out value="Hello, ${sessionScope.user.fullName}."/>
                <input type="submit" value="Log out">
            </form>
        </div>
        <div>
            <form action="booking" method="post">
                <h5>date from</h5>
                <input type="datetime-local" name="date-from" >
                <h5>date to</h5>
                <input type="datetime-local" name="date-to">

                <h5>place number</h5>
                <input type="number" name="place-number" value="<c:out value="${room.capacity}"/>">
                <c:choose>
                    <c:when test = "${room == null}">
                        <h5>room class</h5>
                        <select name="room-class">
                            <option>A+</option>
                            <option>A</option>
                            <option>B</option>
                        </select>
                        <h5>room (may be empty)</h5>
                        <select name="room">
                        </select>
                    </c:when>

                    <c:otherwise>
                        <h5>room class</h5>
                        <input readonly="readonly" type="text" name="room-class" value="<c:out value="${room.roomClass}"/>" >
                        <h5>room number (may be left empty)</h5>
                        <input readonly="readonly" type="text" name="roomNumber" value="<c:out value="${room.number}"/>">
                    </c:otherwise>
                </c:choose>
                <input type="submit" value="Book">
            </form>
        </div>
    </body>
</html>
