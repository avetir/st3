<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head><title>Hello</title></head>
    <body bgcolor="white">
        <form action="registration" method="post">
            <h5>Name</h5>
            <input type="text" name="name">
            <h5>E-mail address</h5>
            <input type="text" name="email">
            <h5>Password</h5>
            <input type="password" name="password">
            <h5>Confirm password</h5>
            <input type="password" name="password-confirmation">
            <br/>
            <br/>
            <input type="submit">
        </form>
    </body>
</html>