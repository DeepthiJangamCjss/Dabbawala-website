<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.dabbaWalaWebsite.models.RestaurantOwnerModel" %>
<%@ page import="com.example.dabbaWalaWebsite.entity.Restaurant" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Restaurant Owners</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            border: 1px solid black;
            padding : 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
    <%
        List<RestaurantOwnerModel> restaurantOwnerModelList = (List<RestaurantOwnerModel>) request.getAttribute("restaurantOwnerModelList");
     %>
</head>
<body>
    <h2>View Restaurant Owners</h2>
    <table>
        <thead>
            <tr>
                <th>Owner ID</th>
                <th>Owner Name</th>
                <th>Number of Restaurants</th>
                <th>Restaurants</th>
            </tr>
        </thead>
        <tbody>
            <% for (RestaurantOwnerModel owner : restaurantOwnerModelList) { %>
                <tr>
                    <td><%= owner.getOwnerId() %></td>
                    <td><%= owner.getOwnerName() %></td>
                    <td><%= owner.getRestaurantList().size() %></td>
                    <td>
                        <% for (Restaurant restaurant : owner.getRestaurantList()) { %>
                            <p>  <%= restaurant.getRestaurantName() %> -- <%= restaurant.getStreet()+"  "+restaurant.getCity()+"  "+restaurant.getState() %> </p>
                        <% } %>
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
