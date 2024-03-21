<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.dabbaWalaWebsite.entity.Location" %>
<%@ page import="com.example.dabbaWalaWebsite.models.RestaurantModel" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Locations</title>
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
    <link rel="stylesheet" type="text/css" href="../style.css">
</head>
<body>
    <h2>All Locations</h2>
    <%
        RestaurantModel restaurantModel = (RestaurantModel) request.getAttribute("restaurantModel");
        List<Location> allLocations = (List<Location>) request.getAttribute("allLocations");
    %>
    <p>Restaurant Name: <%= restaurantModel.getRestaurantName() %></p>
    <p>Location: <%= restaurantModel.getStreet() %>, <%= restaurantModel.getCity() %>, <%= restaurantModel.getState() %></p>

    <h3>Available Locations</h3>
    <table>
        <thead>
            <tr>
                <th>Location ID</th>
                <th>Street</th>
                <th>City</th>
                <th>State</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% for (Location location : allLocations) { %>
                <tr>
                    <td><%= location.getLocationId() %></td>
                    <td><%= location.getStreet() %></td>
                    <td><%= location.getCity() %></td>
                    <td><%= location.getState() %></td>
                    <td>
                        <form action="/restaurant/addOrRemoveLocation" method="post">
                            <input type="hidden" name="restaurantId" value="<%= restaurantModel.getRestaurantId() %>">
                            <input type="hidden" name="locationId" value="<%= location.getLocationId() %>">
                            <% if (restaurantModel.getLocationList().contains(location)) { %>
                                <button type="submit" name="action" value="remove">Remove</button>
                            <% } else { %>
                                <button type="submit" name="action" value="add">Add</button>
                            <% } %>
                        </form>
                    </td>
                </tr>
            <% } %>
        </tbody>
    </table>
    <br>
    <form action="/restaurant/viewRestaurantDetails">
        <input type="hidden" name="restaurantId" value="<%= restaurantModel.getRestaurantId() %>">
        <button type="submit">Back</button>
    </form>
</body>
</html>
