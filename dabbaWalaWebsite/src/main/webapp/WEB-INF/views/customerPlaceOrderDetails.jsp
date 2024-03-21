<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="com.example.dabbaWalaWebsite.models.CustomerModel" %>
<%@ page import="com.example.dabbaWalaWebsite.models.RecepieModel" %>
<%@ page import="com.example.dabbaWalaWebsite.models.LocationModel" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Place Order Details</title>
    <style>
        table {
            border-collapse: collapse;
            width: 40%;
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
    <%
        CustomerModel customerModel = (CustomerModel) request.getAttribute("customerModel");
        RecepieModel recepieModel = (RecepieModel) request.getAttribute("recepieModel");
        LocationModel locationModel = (LocationModel) request.getAttribute("locationModel");
        double afterDiscountPrice = (double) request.getAttribute("afterDiscountPrice");
    %>
</head>
<body>

    <h2>Place Order Details</h2>

    <h3>Customer Information:</h3>
    <p>Name: <%= customerModel.getName() %></p>
    <p>Email: <%= customerModel.getEmail() %></p>
    <p>Membership Type: <%= customerModel.getMembershipType() %></p>

    <h3>Selected Location:</h3>
    <p>Street: <%= locationModel.getStreet() %></p>
    <p>City: <%= locationModel.getCity() %></p>
    <p>State: <%= locationModel.getState() %></p>

    <h3>Selected Recipe:</h3>
    <p>Recipe Name: <%= recepieModel.getRecepieName() %></p>
    <p>Original Price: <%= recepieModel.getPrice() %></p>
    <p>After Discount Price: <%=afterDiscountPrice %></p>

    <br>
    <form action="/customer/confirmOrder">
        <input type="hidden" name="recipeId" value="<%= recepieModel.getRecepieId() %>">
        <input type="hidden" name="locationId" value="<%= locationModel.getLocationId() %>">
        <input type="hidden" name="afterDiscountPrice" value="<%= afterDiscountPrice %>">
        <input type="submit" value="Confirm Order">
    </form>

</body>
</html>
