<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="com.example.dabbaWalaWebsite.models.CustomerModel" %>
<%@ page import="com.example.dabbaWalaWebsite.models.RestaurantModel" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.dabbaWalaWebsite.models.CustomerOrderModel" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Dashboard</title>
    <style>
         select {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            width: 200px;
         }
         input[type="submit"] {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
         }
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
    <%
        CustomerModel customerModel=(CustomerModel) request.getAttribute("customerModel");
        List<RestaurantModel> restaurantModelList=(List<RestaurantModel>) request.getAttribute("restaurantModelList");
        List<CustomerOrderModel> customerOrderModelList = (List<CustomerOrderModel>) request.getAttribute("customerOrderModelList");
    %>
</head>
<body>

    <h1>Customer Dashboard</h1>
    <% if (customerModel != null) { %>
        <p>Welcome, <%= customerModel.getName() %>!</p>
        <p>Email: <%= customerModel.getEmail() %></p>
        <p>Membership Type: <%= customerModel.getMembershipType() %></p>
        <p>Account Balance: <%= customerModel.getAccountBalance() %></p>
    <% } %>
    <br>
    <h2>Search Recipe by restaurant</h2>
    <form action="/customer/restaurantRecepies">
        <select name="restaurantId">
            <% if (restaurantModelList != null) { %>
                <% for (RestaurantModel restaurant : restaurantModelList) { %>
                    <option value="<%= restaurant.getRestaurantId() %>"><%= restaurant.getRestaurantName()%>,<%= restaurant.getStreet()%> </option>
                <% } %>
            <% } %>
        </select>
        <input type="submit" value="Submit">
    </form>

    <br><br>
    <h2>Search Recipe</h2>
    <form action="/customer/searchByRecepieName">
        <label for="recipeName">Recipe Name:</label>
        <input type="text" id="recipeName" name="recipeName">
        <input type="submit" value="Search" />
    </form>

    <br><br>

    <h2>Search By price</h2>
        <form action="/customer/searchByPrice">
            <label for="minPrice">Minimum Price  </label>
            <input type="text" id="minPrice" name="minPrice">
            <br>
            <label for="maxPrice">Maximum Price  </label>
            <input type="text" id="maxPrice" name="maxPrice">
            <br>
            <input type="submit" value="Search" />
        </form>

        <br><br>
    <h2>My Order Details</h2>
        <% if (customerOrderModelList != null && !customerOrderModelList.isEmpty()) { %>
            <table>
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Amount</th>
                        <th>Status</th>
                        <th>Restaurant Name</th>
                        <th>Recipe Name</th>
                        <th>Location</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (CustomerOrderModel customerOrderModel : customerOrderModelList) { %>
                        <tr>
                            <td><%= customerOrderModel.getOrderId() %></td>
                            <td><%= customerOrderModel.getAmount() %></td>
                            <td><%= customerOrderModel.getOwnerApproved() %></td>
                            <td><%= customerOrderModel.getRestaurantName() %></td>
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
    <form:form action="/customerLoginForm">
        <input type="submit" value="Logout" />
    </form:form>
</body>
</html>
