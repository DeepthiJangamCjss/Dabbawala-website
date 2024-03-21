<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Recipe</title>
</head>
<body>
    <h2>Add Recipe</h2>
    <form:form action="/restaurant/saveRecipe" method="post" modelAttribute="recepieModel">

        <label for="recepieName">Recipe Name:</label>
        <form:input type="text" path="recepieName"/><br>
        <form:errors path="recepieName" cssClass="error" /><br>

        <label for="price">Price:</label>
        <form:input type="text" path="price"/><br>
        <form:errors path="price" cssClass="error" /><br>

        <label for="premiumRecepie">Premium Recipe:</label>
        <form:checkbox path="premiumRecepie" />
        <br><br>

        <form:hidden path="restaurant" value="${restaurantId}" />
        <button type="submit">Add Recipe</button>
    </form:form>
    <form action="/restaurant/viewRestaurantDetails">
        <input type="hidden" name="restaurantId" value="${restaurantId}">
        <button type="submit">Back</button>
    </form>
</body>
</html>
