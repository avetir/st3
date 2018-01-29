<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<html>
    <head>
        <title>
            Hello
        </title>
    </head>
    <body bgcolor="white">
        <div style="float: bottom">
            <c:forEach var="error" items="${requestScope.errorList}">
                <c:out value="${error}"/>
                <br/>
            </c:forEach>
        </div>
        <div style="float: left">
            <form action="login" method="post">
                <h5>E-mail address</h5>
                <input type="text" name="email">
                <h5>Password</h5>
                <input type="password" name="password">
                <br/>
                <input type="submit">
            </form>
        </div>
    </body>
</html>