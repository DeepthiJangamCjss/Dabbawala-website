<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Location</title>
    <link rel="stylesheet" type="text/css" href="../style.css">
</head>
<body>

    <h2>Add Location</h2>

    <form:form action="/admin/saveLocation" method="post" modelAttribute="locationModel">
        <label for="street">Street:</label>
        <form:input type="text" id="street" path="street"/>
        <form:errors path="street" cssClass="error" /><br>

        <label for="city">City:</label>
        <form:input type="text" id="city" path="city" />
        <form:errors path="city" cssClass="error" /><br>

        <label for="state">State:</label>
        <form:input type="text" id="state" path="state"/>
        <form:errors path="state" cssClass="error" /><br>

        <button type="submit">Add Location</button>
    </form:form>

    <form action="/admin/dashboard" method="get">
        <input type="submit" value="Back to Dashboard" />
    </form>

</body>
</html>
