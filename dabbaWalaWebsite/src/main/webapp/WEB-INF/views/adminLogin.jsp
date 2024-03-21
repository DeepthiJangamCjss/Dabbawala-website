<!-- login-admin.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Login</title>
    <link rel="stylesheet" type="text/css" href="../style.css">
</head>
<body>

    <h2>Admin Login</h2>

    <form action="/admin/login" method="post">
        <input type="hidden" name="userType" value="ADMIN">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>

        <input type="submit" value="Login" />
    </form>
    <form:form action="/">
        <input type="submit" value="Back" />
    </form:form>
</body>
</html>
