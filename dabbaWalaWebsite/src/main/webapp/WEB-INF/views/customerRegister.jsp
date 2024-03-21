<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Registration</title>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
    <h1>Customer Registration</h1>

    <form:form action="/saveCustomer" method="post" modelAttribute="customerModel">

        <div>
            <label for="username">Username:</label>
            <form:input path="username" id="username" />
            <br>
            <form:errors path="username" cssClass="error" />
        </div>

        <div>
            <label for="password">Password:</label>
            <form:password path="password" id="password" />
            <br>
            <form:errors path="password" cssClass="error" />
        </div>

        <div>
            <label for="name">Name:</label>
            <form:input path="name" id="name" />
            <br>
            <form:errors path="name" cssClass="error" />
        </div>

        <div>
            <label for="age">Age:</label>
            <form:input type="number" path="age" id="age" />
            <br>
            <form:errors path="age" cssClass="error" />
        </div>

        <div>
            <label for="email">Email:</label>
            <form:input path="email" id="email" />
            <br>
            <form:errors path="email" cssClass="error" />
        </div>
        <div>
            <label for="accountBalance">Account Balance:</label>
            <form:input type="number" path="accountBalance" id="accountBalance" />
            <br>
            <form:errors path="accountBalance" cssClass="error" />
        </div>

        <div>
            <input type="submit" value="Register">
        </div>
    </form:form>
    <form:form action="/">
        <input type="submit" value="Back" />
    </form:form>
</body>
</html>
