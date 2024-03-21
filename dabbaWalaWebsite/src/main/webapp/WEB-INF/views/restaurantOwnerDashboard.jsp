<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.dabbaWalaWebsite.models.RestaurantOwnerModel" %>
<%@ page import="com.example.dabbaWalaWebsite.entity.Restaurant" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Restaurant Owner Dashboard</title>
    <style>
        table {
            border-collapse: collapse;
            width: 70%;
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
    <% RestaurantOwnerModel  restaurantOwnerModel=(RestaurantOwnerModel) request.getAttribute("restaurantOwnerModel"); %>
</head>
<body>
    <h1>Welcome, <%= restaurantOwnerModel.getOwnerName() %> !</h1>

    <p>Owner ID: <%= restaurantOwnerModel.getOwnerId() %></p>
    <p>Email: <%= restaurantOwnerModel.getEmail() %></p>

    <h3>Your Restaurants:</h3>
    <% if (restaurantOwnerModel.getRestaurantList() != null && !restaurantOwnerModel.getRestaurantList().isEmpty()) { %>
        <table border="1">
            <thead>
                <tr>
                    <th>Restaurant ID</th>
                    <th>Restaurant Name</th>
                    <th>Street  </th>
                    <th>City </th>
                    <th>State  </th>
                    <th>Details</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <% for (Restaurant restaurant : restaurantOwnerModel.getRestaurantList()) { %>
                    <tr>
                        <td><%= restaurant.getRestaurantId() %></td>
                        <td><%= restaurant.getRestaurantName() %></td>
                        <td><%= restaurant.getStreet() %></td>
                        <td><%= restaurant.getCity() %></td>
                        <td><%= restaurant.getState() %></td>
                        <td>
                            <form action="/restaurant/viewRestaurantDetails" method="post">
                                <input type="hidden" name="restaurantId" value="<%= restaurant.getRestaurantId() %>">
                                <button type="submit">View Details</button>
                            </form>
                        </td>
                        <td>
                            <form action="/restaurant/deleteRestaurant">
                                <input type="hidden" name="restaurantId" value="<%= restaurant.getRestaurantId() %>">
                                <button type="submit">Delete</button>
                            </form>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } %>
    <br>

    <form action="/restaurant/addRestaurantForm" method="get">
        <input type="hidden" name="ownerId" value="<%= restaurantOwnerModel.getOwnerId() %>">
        <button type="submit">Add Restaurant</button>
    </form>
    <br>
    <form action="/restaurant/restaurantOwnerLoginForm" method="get">
        <button type="submit">Logout</button>
    </form>
</body>
</html>
