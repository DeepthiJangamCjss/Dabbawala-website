<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="com.example.dabbaWalaWebsite.models.RecepieModel" %>
<%@ page import="com.example.dabbaWalaWebsite.models.CustomerModel" %>
<%@ page import="com.example.dabbaWalaWebsite.models.LocationModel" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Recipes</title>
    <style>
        table {
            border-collapse: collapse;
            width: 80%;
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
        List<LocationModel> locationModelList=(List<LocationModel>) request.getAttribute("locationModelList");
    %>
</head>
<body>

    <h2>Recipes</h2>
    <hr>
    <% if(recepieModelList!=null && !recepieModelList.isEmpty()){ %>
    <form action="/customer/placeOrder">
        <h3>Select location</h3>
        <select id="locationId" name="locationId">
            <% for (LocationModel location : locationModelList) { %>
                <option value="<%= location.getLocationId() %>"><%= location.getStreet() %>, <%= location.getCity() %>, <%= location.getState() %></option>
            <% } %>
        </select>
        <br><br>
        <table>
            <thead>
                <tr>
                    <th>Recipe Name</th>
                    <th>Price</th>
                    <th>RestaurantName</th>
                    <th>Restaurant Location</th>
                    <th>Type</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% for(RecepieModel recepie: recepieModelList) { %>
                    <tr>
                        <% if (customerModel.getMembershipType().equals("Normal")) { %>
                            <% if (!recepie.isPremiumRecepie()) { %>
                                <td><%= recepie.getRecepieName() %></td>
                                <td><%= recepie.getPrice() %></td>
                                <td><%= recepie.getRestaurant().getRestaurantName() %></td>
                                <td><%= recepie.getRestaurant().getStreet()+", "+recepie.getRestaurant().getCity()+", "+recepie.getRestaurant().getState() %></td>
                                <td>Normal Recipe</td>
                                <td><button type="submit" name="recipeId" value="<%= recepie.getRecepieId() %>">Place Order</button></td> <!-- Place Order button -->
                            <% } %>
                        <% } else { %>
                            <td><%= recepie.getRecepieName() %></td>
                            <td><%= recepie.getPrice() %></td>
                            <td><%= recepie.getRestaurant().getRestaurantName() %></td>
                            <td><%= recepie.getRestaurant().getStreet()+", "+recepie.getRestaurant().getCity()+", "+recepie.getRestaurant().getState() %></td>
                            <% if(recepie.isPremiumRecepie()) { %>
                                <td>Normal Recipe</td>
                            <% } else { %>
                                <td>Premium Recipe</td>
                            <% } %>
                            <td><button type="submit" name="recipeId" value="<%= recepie.getRecepieId() %>">Place Order</button></td> <!-- Place Order button -->
                        <% } %>
                    </tr>
                <% } %>
            </tbody>
        </table>
        <br>
        </form>
    <% } else { %>
        <h2>No recipes Available</h2>
    <% } %>
    <br>
    <form action="/customer/dashboard">
        <input type="submit" value="Back to Dashboard">
    </form>
</body>
</html>
