<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Restaurant</title>
    <link rel="stylesheet" type="text/css" href="../style.css">
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h2>Add Restaurant</h2>

    <form:form action="/restaurant/saveRestaurant" method="post" modelAttribute="restaurantModel">
        <form:errors path="*" cssClass="error" />

        <label for="restaurantName">Restaurant Name:</label>
        <form:input path="restaurantName" id="restaurantName" required="true"/>
        <form:errors path="restaurantName" cssClass="error" /><br>

        <label for="street">Street:</label>
        <form:input path="street" id="street" required="true"/>
        <form:errors path="street" cssClass="error" /><br>

        <label for="city">City:</label>
        <form:input path="city" id="city" required="true"/>
        <form:errors path="city" cssClass="error" /><br>

        <label for="state">State:</label>
        <form:input path="state" id="state" required="true"/>
        <form:errors path="state" cssClass="error" /><br>

        <input type="submit" value="Save Restaurant"/>
    </form:form>

    <form:form action="/restaurant/dashboard" method="get">
        <input type="submit" value="Back" />
    </form:form>
</body>
</html>
