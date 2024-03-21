<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.dabbaWalaWebsite.models.RestaurantModel" %>
<%@ page import="com.example.dabbaWalaWebsite.entity.Location" %>
<%@ page import="com.example.dabbaWalaWebsite.entity.Recepie" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Restaurants</title>
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
    <%
        List<RestaurantModel> restaurantModelList = (List<RestaurantModel>) request.getAttribute("restaurantModelList");
     %>
</head>
<body>
    <h2>View Restaurants</h2>
    <table>
        <thead>
            <tr>
                <th>Restaurant ID</th>
                <th>Restaurant Name</th>
                <th>Street</th>
                <th>City</th>
                <th>State</th>
                <th>Owner</th>
                <th>Available Recipes</th>
                <th>Deliverable Locations</th>
            </tr>
        </thead>
        <tbody>
            <% for (RestaurantModel restaurantModel : restaurantModelList) { %>
                <tr>
                    <td><%= restaurantModel.getRestaurantId() %></td>
                    <td><%= restaurantModel.getRestaurantName() %></td>
                    <td><%= restaurantModel.getStreet() %></td>
                    <td><%= restaurantModel.getCity() %></td>
                    <td><%= restaurantModel.getState() %></td>
                    <td><%= restaurantModel.getRestaurantOwner().getOwnerName() %></td>
                    <td>
                        <ul>
                            <% for (Recepie recepie : restaurantModel.getRecepieList()) { %>
                                <li><%= recepie.getRecepieName() %></li>
                            <% } %>
                        </ul>
                    </td>
                    <td>
                        <ul>
                            <% for (Location location : restaurantModel.getLocationList()) { %>
                                <li><%= location.getStreet() %>, <%= location.getCity() %>, <%= location.getState() %></li>
                            <% } %>
                        </ul>
                    </td>
                </tr>
            <% } %>
        </tbody>
    </table>
    <br>
    <form action="/admin/dashboard" method="get">
        <input type="submit" value="Back" />
    </form>
</body>
</html>
