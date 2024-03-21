<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.dabbaWalaWebsite.models.CustomerModel" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View All Locations</title>
    <link rel="stylesheet" type="text/css" href="../style.css">
    <% List<CustomerModel> customerModelList = (List<CustomerModel>) request.getAttribute("customerModelList"); %>
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

    <h2>All Customers</h2>
    <% if (!customerModelList.isEmpty()) { %>
        <table>
            <tr>
                <th>Customer Id</th>
                <th>Name </th>
                <th>Age</th>
                <th>Email</th>
                <th>MemberShip type</th>
            </tr>
            <% for(CustomerModel customerModel : customerModelList) { %>
                <tr>
                    <td> <%= customerModel.getCustomerId() %> </td>
                    <td> <%= customerModel.getName() %> </td>
                    <td><%= customerModel.getAge() %> </td>
                    <td><%= customerModel.getEmail() %> </td>
                    <td><%= customerModel.getMembershipType() %> </td>
                </tr>
            <% } %>
        </table>
    <% } else { %>
        <h1>No customers added yet</h1>
    <% } %>
    <br>
    <form action="/admin/dashboard" method="get">
        <input type="submit" value="Back" />
    </form>

</body>
</html>
