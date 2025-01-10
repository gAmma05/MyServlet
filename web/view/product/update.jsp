<%-- 
    Document   : update
    Created on : Jan 1, 2025, 2:02:09 PM
    Author     : gAmma
--%>

<%@page import="entities.Product"%>
<%@page import="entities.Category"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update product</title>
    </head>
    <body>
        <h1>Update an existing product</h1>
        <c:if test="${not empty msg}">
            <p>${msg}</p>
        </c:if>
        <form action="Product" method="POST">
            <input type="hidden" name="productId" value="${product.id}">

            <label for="name"> Product Name:</label>
            <input type="text" id="name" name="name" value="${product.name}" required><br><br>

            <label for="price">Price:</label>
            <input type="number" id="price" name="price" step="0.01" value="${product.price}" required><br><br>

            <label for="productYear">Product Year:</label>
            <input type="number" id="productYear" name="productYear" value="${product.productYear}" required><br><br>

            <label for="image">Image URL:</label>
            <input type="text" id="image" name="image" value="${product.image}"><br><br>

            <label for="category">Category:</label>
            <select id="category" name="category" required>
                <c:forEach var="category" items="${categories}">
                    <option value="${category.id}" ${category.id == product.category.id ? 'selected' : ''}>${category.name}</option>
                </c:forEach>
            </select><br><br>
            <input type="hidden" name="action" value="update">
            <button type="submit">Update</button>
        </form>

        <br>
        <br>
        <form action="Product" method="GET" style="display:inline;">

            <button type="submit">Back</button>
        </form>
    </body>
</html>
