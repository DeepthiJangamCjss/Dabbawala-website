<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="com.example.dabbaWalaWebsite.models.RecepieModel" %>
<%@ page import="com.example.dabbaWalaWebsite.models.CustomerModel" %>
<%@ page import="com.example.dabbaWalaWebsite.models.RestaurantModel" %>
<%@ page import="com.example.dabbaWalaWebsite.entity.Location" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Restaurant Recipes</title>
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
        select {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            width: 300px;
         }
    </style>
    <%
        List<RecepieModel> recepieModelList=(List<RecepieModel>) request.getAttribute("recepieModelList");
        CustomerModel customerModel = (CustomerModel) request.getAttribute("customerModel");
        RestaurantModel restaurantModel = (RestaurantModel) request.getAttribute("restaurantModel");
    %>
</head>
<body>

    <h2>Restaurant Recipes</h2>
    <h4>Select location </h4>
    <form action="/customer/placeOrder">
        <select id="locationId" name="locationId">
            <% if (restaurantModel!= null && restaurantModel.getLocationList() != null) { %>
                <% for (Location location : restaurantModel.getLocationList()) { %>
                    <option value="<%= location.getLocationId() %>"><%= location.getStreet() %>, <%= location.getCity() %>, <%= location.getState() %></option>
                <% } %>
            <% } %>
        </select>
        <br><br>
        <table border="1">
            <thead>
                <tr>
                    <th>Recipe Name</th>
                    <th>Price</th>
                    <th>Type</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% if (recepieModelList != null && !recepieModelList.isEmpty()) { %>
                    <% for (RecepieModel recepie : recepieModelList) { %>
                        <tr>
                            <% if (customerModel.getMembershipType().equals("Normal")) { %>
                                <% if (!recepie.isPremiumRecepie()) { %>
                                    <td><%= recepie.getRecepieName() %></td>
                                    <td><%= recepie.getPrice() %></td>
                                    <td>Normal Recepie </td>
                                    <td><button type="submit" name="recipeId" value="<%= recepie.getRecepieId() %>">Place Order</button></td> <!-- Place Order button -->
                                <% } %>
                            <% } else { %>
                                <td><%= recepie.getRecepieName() %></td>
                                <td><%= recepie.getPrice() %></td>
                                <% if(recepie.isPremiumRecepie()) { %>
                                    <td>Normal Recepie</td>
                                <% } else { %>
                                    <td>Premium Recepie</td>
                                <% } %>
                                <td><button type="submit" name="recipeId" value="<%= recepie.getRecepieId() %>">Place Order</button></td> <!-- Place Order button -->
                            <% } %>
                        </tr>
                    <% } %>
                <% } else { %>
                    <tr>
                        <td colspan="4">No recipes available for this restaurant.</td>
                    </tr>
                <% } %>
            </tbody>
        </table>
        <br>
    </form>
    <br>
    <form action="/customer/dashboard">
        <input type="submit" value="Back to Dashboard">
    </form>

</body>
</html>
