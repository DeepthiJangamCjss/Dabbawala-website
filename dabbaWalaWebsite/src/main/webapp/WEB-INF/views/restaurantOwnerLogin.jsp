<!-- login-restaurant-owner.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Restaurant Owner Login</title>
    <link rel="stylesheet" type="text/css" href="../style.css">
</head>
<body>

    <h2>Restaurant Owner Login</h2>

    <form:form action="/restaurant/restaurantOwnerLogin" method="post">
        <input type="hidden" name="userType" value="RESTAURANT_OWNER">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>

        <button type="submit">Login</button>
    </form:form>
    <form:form action="/">
        <input type="submit" value="Home" />
    </form:form>
</body>
</html>
