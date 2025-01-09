<%-- 
    Document   : update
    Created on : Jan 1, 2025, 2:02:09 PM
    Author     : gAmma
--%>

<%@page import="entities.Product"%>
<%@page import="entities.Category"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update product</title>
    </head>
    <body>
        <h1>Update an existing product</h1>
        <%
            String error = (String) request.getAttribute("msg");
            Product product = (Product) request.getAttribute("product");
        %>
        <%
            if (error != null) {
        %>   
        <p><%=error%></p>
        <%
            }
        %>
        <form action="Product" method="POST">
            <input type="hidden" name="productId" value="<%= product.getId()%>">

            <label for="name"> Product Name:</label>
            <input type="text" id="name" name="name" value="<%= product.getName()%>" required><br><br>

            <label for="price">Price:</label>
            <input type="number" id="price" name="price" step="0.01" value="<%= product.getPrice()%>" required><br><br>

            <label for="productYear">Product Year:</label>
            <input type="number" id="productYear" name="productYear" value="<%= product.getProductYear()%>" required><br><br>

            <label for="image">Image URL:</label>
            <input type="text" id="image" name="image" value="<%=product.getImage()%>"><br><br>

            <label for="category">Category:</label>
            <select id="category" name="category" required>
                <%
                    List<Category> categories = (List<Category>) request.getAttribute("categories");
                    if (categories != null) {
                        for (Category category : categories) {
                            boolean prec = product != null && category.getId() == product.getCategory().getId();
                %>
                <option value="<%= category.getId()%>" <%= prec ? "selected" : ""%>><%= category.getName()%></option>
                <%
                        }
                    }
                %>
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
