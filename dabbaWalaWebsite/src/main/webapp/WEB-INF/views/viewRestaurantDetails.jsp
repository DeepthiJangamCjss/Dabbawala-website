<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.dabbaWalaWebsite.models.RestaurantModel" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.dabbaWalaWebsite.entity.Recepie" %>
<%@ page import="com.example.dabbaWalaWebsite.entity.Location" %>
<%@ page import="com.example.dabbaWalaWebsite.entity.Recepie" %>
<%@ page import="com.example.dabbaWalaWebsite.entity.Customer" %>
<%@ page import="com.example.dabbaWalaWebsite.models.CustomerOrderModel" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Restaurant Details</title>
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
    </style>
    <%
        RestaurantModel restaurantModel = (RestaurantModel) request.getAttribute("restaurantModel");
        List<CustomerOrderModel> customerOrderModelList = (List<CustomerOrderModel>) request.getAttribute("customerOrderModelList");
    %>
</head>
<body>
    <h2>Restaurant Details</h2>
    <p>Restaurant Name: <%= restaurantModel.getRestaurantName() %></p>
    <p>Location: <%= restaurantModel.getStreet() %>, <%= restaurantModel.getCity() %>, <%= restaurantModel.getState() %></p>
    <br>

    <h3>Recipes</h3>
    <table border="1">
        <thead>
            <tr>
                <th>Recipe Name</th>
                <th>Price</th>
                <th>Type</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% for (Recepie recipe : restaurantModel.getRecepieList()) { %>
                <tr>
                    <td><%= recipe.getRecepieName() %></td>
                    <td><%= recipe.getPrice() %></td>
                    <td>
                        <% if(recipe.isPremiumRecepie()) { %>
                            Premium
                        <% } else { %>
                            Normal
                        <% } %>
                     </td>
                    <td>
                        <form action="/restaurant/deleteRecipe" method="post">
                            <input type="hidden" name="recepieId" value="<%= recipe.getRecepieId() %>">
                            <button type="submit">Delete</button>
                        </form>
                    </td>
                </tr>
            <% } %>
        </tbody>
    </table>
    <br>
    <form action="/restaurant/addRecipe" method="post">
        <input type="hidden" name="restaurantId" value="<%= restaurantModel.getRestaurantId() %>">
        <button type="submit">Add Recipe</button>
    </form>
    <br>
    <br>

    <h3>Locations</h3>
    <table border="1">
        <thead>
            <tr>
                <th>Street</th>
                <th>City</th>
                <th>State</th>
            </tr>
        </thead>
        <tbody>
            <% for (Location location : restaurantModel.getLocationList()) { %>
                <tr>
                    <td><%= location.getStreet() %></td>
                    <td><%= location.getCity() %></td>
                    <td><%= location.getState() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
    <br>
    <form action="/restaurant/addLocation" method="post">
        <input type="hidden" id="restaurantId" name="restaurantId" value="<%= restaurantModel.getRestaurantId()%>">
        <button type="submit">Manage Locations</button>
    </form>
    <br>
    <br>
    <h2>Customer Order Details</h2>
    <% if (customerOrderModelList != null && !customerOrderModelList.isEmpty()) { %>
        <table>
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Amount</th>
                    <th>Status</th>
                    <th>Customer Name</th>
                    <th>Recipe Name</th>
                    <th>Location</th>
                </tr>
            </thead>
            <tbody>
                <% for (CustomerOrderModel customerOrderModel : customerOrderModelList) { %>
                    <tr>
                        <td><%= customerOrderModel.getOrderId() %></td>
                        <td><%= customerOrderModel.getAmount() %></td>
                        <td>
                            <% if (customerOrderModel.getOwnerApproved().equals("PENDING")) { %>
                                <form action="/restaurant/deliverOrder" method="post">
                                    <input type="hidden" name="orderId" value="<%= customerOrderModel.getOrderId() %>">
                                    <input type="hidden" name="restaurantId" value="<%= restaurantModel.getRestaurantId() %>">
                                    <button type="submit">DELIVER</button>
                                </form>
                                <form action="/restaurant/cancelOrder" method="post">
                                    <input type="hidden" name="orderId" value="<%= customerOrderModel.getOrderId() %>">
                                    <input type="hidden" name="restaurantId" value="<%= restaurantModel.getRestaurantId() %>">
                                    <button type="submit">CANCEL</button>
                                </form>
                            <% } else { %>
                                <%= customerOrderModel.getOwnerApproved() %>
                            <% } %>
                        </td>
                        <td><%= customerOrderModel.getCustomer().getName() %></td>
                        <td><%= customerOrderModel.getRecepieName() %></td>
                        <td><%= customerOrderModel.getLocationName() %></td>
                    </tr>
                <% } %>

            </tbody>
        </table>
    <% } else { %>
        <p>No orders found.</p>
    <% } %>
        <br><br>
    <form action="/restaurant/dashboard" method="get">
        <button type="submit">Back To DashBoard</button>
    </form>

</body>
</html>
