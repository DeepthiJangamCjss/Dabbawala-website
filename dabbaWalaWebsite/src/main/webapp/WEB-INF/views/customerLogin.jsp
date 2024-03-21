<!-- login-customer.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Login</title>
    <link rel="stylesheet" type="text/css" href="../style.css">
</head>
<body>

    <h2>Customer Login</h2>

    <form:form action="/customer/customerLogin" method="post">
        <input type="hidden" name="userType" value="CUSTOMER">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>

        <input type="submit" value="Login"/>
    </form:form>

    <form:form action="/">
        <input type="submit" value="Home" />
    </form:form>
</body>
</html>


<%--
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Login</title>
</head>
<body>

    <h2>Customer Login</h2>


    <form:form action="/customerLogin" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required autofocus><br>

        <label for="password">Password:</label>
        <br>
        <input type="password" id="password" name="password" required><br>

        <input type="submit" value="Login">
    </form:form>
</body>
</html>
--%>