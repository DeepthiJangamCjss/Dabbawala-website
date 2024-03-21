<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
</head>
<body>

    <h2>Admin Dashboard</h2>

    <div>
        <form action="/admin/viewLocations" method="get">
            <input type="submit" value="View Locations" />
        </form>
        <br>

        <form action="/admin/viewRestaurants" method="get">
            <input type="submit" value="View Restaurants" />
        </form>
        <br>

        <form action="/admin/viewRestaurantOwners" method="get">
            <input type="submit" value="View Restaurant Owners" />
        </form>
        <br>
        <form action="/admin/viewCustomers" method="get">
            <input type="submit" value="View Customers" />
        </form>
        <br>

        <form action="/admin/loginForm" method="get">
            <input type="submit" value="Logout" />
        </form>
    </div>

</body>
</html>