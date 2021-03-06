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
    <div>
        <form action="home" method="get">
            <input type="submit" value="Home Page">
        </form>
    </div>
    <div style="float: right">
        <form action="logout" method="post" style="right: 0">
            <c:out value="Hello, ${sessionScope.user.fullName}."/>
            <input type="submit" value="Log out">
        </form>
    </div>
    <div>
        <table>
            <tr>
                <td align="center">Request date</td>
                <td align="center">Date in</td>
                <td align="center">Date out</td>
                <td align="center">Room number</td>
                <td align="center">Room class</td>
                <td align="center">Places</td>
                <td align="center">Status</td>
                <td></td>
            </tr>
            <c:forEach var="request" items="${requests}" >
                <tr>
                    <td align="center"><c:out value="${request.requestDateTime}" /></td>
                    <td align="center"><c:out value="${request.dateTimeIn}" /></td>
                    <td align="center"><c:out value="${request.dateTimeOut}" /></td>
                    <td align="center"><c:out value="${request.roomNumber}" /></td>
                    <td align="center"><c:out value="${request.roomClass}" /></td>
                    <td align="center"><c:out value="${request.placesNumber}" /></td>
                    <td align="center"><c:out value="${request.status}" /></td>
                    <td align="center">
                        <c:if test = "${request.status == \"PENDING\"}">
                            <form method="post" action="/requestApprove">
                                <input type="text" hidden value="${request.id}" name="requestId">
                                <input type="submit" value="Approve">
                            </form>
                            <form method="post" action="/requestReject">
                                <input type="text" hidden value="${request.id}" name="requestId">
                                <input type="submit" value="Decline">
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
