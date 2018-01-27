<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII" isELIgnored="false" %>
<html>
    <body>
        <form action="logout" method="post">
            <input type="submit" value="Log out">
        </form>
        <form action="booking" method="post">
            <h5>place number</h5>
            <input type="number" name="place-number" value="<c:out value="${room.capacity}"/>">
            <h5>room class</h5>
            <c:choose>
                <c:when test = "${room == null}">
                    <select name="room-class">
                        <option>A+</option>
                        <option>A</option>
                        <option>B</option>
                    </select>
                </c:when>

                <c:otherwise>
                    <input disabled type="text" name="place-number" value="<c:out value="${room.roomClass}"/>">
                </c:otherwise>
            </c:choose>
            <h5>date from</h5>
            <input type="datetime-local" name="date-from" >
            <h5>date to</h5>
            <input type="datetime-local" name="date-to">
            <h5>room (may be empty)</h5>
            <c:choose>
                <c:when test = "${room == null}">
                    <select name="room">
                    </select>
                </c:when>

                <c:otherwise>
                    <input disabled type="text" name="room" value="<c:out value="${room.number}"/>">
                </c:otherwise>
            </c:choose>
            <input type="submit" value="Book">
        </form>

    </body>
</html>
