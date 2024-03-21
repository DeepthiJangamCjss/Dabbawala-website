<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome to DabbaWala</title>
    <link rel="stylesheet" type="text/css" href="../style.css">
</head>
<body>
    <h1>Welcome to DabbaWala</h1>
        <h1> Admin </h1>
        <form:form action="/admin/loginForm">
            <input type="submit" value="Login" />
        </form:form>
        <h1> Restaurant Owner </h1>
        <form:form action="/restaurantOwnerRegister">
            <input type="submit" value="Register" />
        </form:form>
        <br>
        <form:form action="/restaurant/restaurantOwnerLoginForm">
            <input type="submit" value="Login" />
        </form:form>
        <h1> Customer </h1>
        <form:form action="/customerRegister">
            <input type="submit" value="Register" />
        </form:form>
        <br>
        <form:form action="/customer/customerLoginForm">
            <input type="submit" value="Login" />
        </form:form>
</body>
</html>
