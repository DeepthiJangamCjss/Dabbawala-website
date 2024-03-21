<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.dabbaWalaWebsite.models.LocationModel" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View All Locations</title>
    <link rel="stylesheet" type="text/css" href="../style.css">
    <% List<LocationModel> locationModelList = (List<LocationModel>) request.getAttribute("locationModelList"); %>
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

    <h2>All Locations</h2>
    <% if (!locationModelList.isEmpty()) { %>
        <table>
            <tr>
                <th>id </th>
                <th>Street</th>
                <th>City</th>
                <th>State</th>
                <th>Delete</th>
            </tr>
            <% for(LocationModel locationModel : locationModelList) { %>
                <tr>
                    <td> <%= locationModel.getLocationId() %> </td>
                    <td><%= locationModel.getStreet() %> </td>
                    <td><%= locationModel.getCity() %> </td>
                    <td><%= locationModel.getState() %> </td>
                    <td>
                        <a href="/admin/deleteLocation?locationId=<%= locationModel.getLocationId() %>">Delete</a>
                    </td>
                </tr>
            <% } %>
        </table>
    <% } else { %>
        <h1>No locations added yet</h1>
    <% } %>
    <br>
    <form action="/admin/addLocationForm" method="get">
        <input type="submit" value="Add Location" />
    </form>
    <br>
    <form action="/admin/dashboard" method="get">
        <input type="submit" value="Back" />
    </form>

</body>
</html>
