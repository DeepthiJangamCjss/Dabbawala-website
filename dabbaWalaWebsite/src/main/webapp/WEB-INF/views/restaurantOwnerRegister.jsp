<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Restaurant Owner Registration</title>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
    <h1>Restaurant Owner Registration</h1>

    <form:form action="/saveRestaurantOwner" method="post" modelAttribute="restaurantOwnerModel">

        <div>
            <label for="username">Username:</label>
            <form:input path="username" id="username" />
            <br>
            <form:errors path="username" cssClass="error" />
        </div>

        <div>
            <label for="password">Password:</label>
            <form:password path="password" id="password" />
            <br>
            <form:errors path="password" cssClass="error" />
        </div>

        <div>
            <label for="ownerName">Name:</label>
            <form:input path="ownerName" id="ownerName" />
            <br>
            <form:errors path="ownerName" cssClass="error" />
        </div>

        <div>
            <label for="email">Email:</label>
            <form:input path="email" id="email" />
            <br>
            <form:errors path="email" cssClass="error" />
        </div>

        <div>
            <input type="submit" value="Register">
        </div>
    </form:form>
    <form:form action="/">
        <input type="submit" value="Back" />
    </form:form>
</body>
</html>
