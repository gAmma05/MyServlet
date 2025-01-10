<%-- 
    Document   : product
    Created on : Dec 26, 2024, 10:48:03 PM
    Author     : vothimaihoa
--%>

<%@page import="entities.Product"%>
<%@page import="entities.Category"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Page</title>
    </head>
    <body>
        <!-- Search by category(drop down) and name (text box) - Add -->
        
        <h1>Product Page</h1>
        <br>

        <!--SEARCH FORM-->
        <form action="Product" method="GET">
            <label>Product Name:</label>
            <input type="text" name="productName" value="${requestScope.productName}">

            <label>Category:</label>
            <select name="category" id="category">
                <%
                    List<Category> categories = (List<Category>) request.getAttribute("categories");
                    if (categories != null) {
                        for (Category category : categories) {
                %>
                <option value="<%= category.getId()%>"><%= category.getName()%></option>
                <%
                        }
                    }
                %>
                <option value="<%=""%>"><%= "All categories"%></option>


            </select>
            <br>

            <label>Min Price</label>
            <input type="number" step="0.01" name="minPrice" value="${requestScope.minPrice}">

            <label>Max Price</label>
            <input type="number" step="0.01" name="maxPrice" value="${requestScope.maxPrice}">


            <input type="submit" value="search">
        </form>
            
            <br>
            <form action="Auth" method="GET">
                <input type="hidden" name="action" value="logout">
                <input type="submit" value="Logout">
            </form>

        <br>

        <!--PRODUCT LIST-->
        <%
            String role = (String) session.getAttribute("role");
        %>
        <%
            List<Product> products = (List<Product>) request.getAttribute("products");
            if (products == null) {
                String msg = (String) request.getAttribute("msg");
        %>
        <h3><%= msg%></h3>
        <%
        } else {
            int count = 1;
        %>
        <table>
            <tr>
                <th>No. </th>
                <th>Product Name</th>
                <th>Price</th>
                <th>Product Year</th>
                <th>Image</th>
                <th>Category</th>
            </tr>
            <%
                for (Product p : products) {
            %>
            <tr>
                <td><%= count++%></td>
                <td><%= p.getName()%></td>
                <td><%= p.getPrice()%></td>
                <td><%= p.getProductYear()%></td>
                <td><img src="<%= p.getImage()%>" alt="product image" width="100" height="100"></td>
                <td><%= p.getCategory().getName()%></td>
                <% if("staff".equals(role)) { %>
                <td>
                    <form action="Product" method="GET" style="display:inline;">
                        <input type="hidden" name="action" value="prepare-update">
                        <input type="hidden" name="productId" value="<%= p.getId()%>">
                        <button type="submit">Update</button>
                    </form>
                </td>
            <br>
            <td>
                <form action="Product" method="POST" onsubmit="return confirmDelete();" style="display:inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="productId" value="<%= p.getId()%>">
                    <button type="submit">Delete</button>
                </form>
            </td>
            <% } %>

        </tr>
        <%
            }
        %>
    </table>
    <%
        }
    %>
    <br>
    <form action="Product" method="GET">
        <input type="hidden" name="action" value="prepare-add">
        <button type="submit">Add</button>
    </form>


    <!-- JS -->
    <script>
        const selectedCategoryId = '<%= request.getAttribute("category")%>';
        const selectElement = document.getElementById('category');

        if (selectedCategoryId) {
            selectElement.value = selectedCategoryId;
        } else {
            selectElement.value = "";
        }
    </script>

    <script>
        function confirmDelete() {
            return confirm("Are you sure you want to delete this product?");
        }
    </script>

    <!-- BTVN: Viet them search by price va sort, + viet them update + delete product by id -->
</body>
</html>
