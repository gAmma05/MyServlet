<%-- 
    Document   : register
    Created on : Jan 8, 2025, 8:25:59 PM
    Author     : gAmma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <%
            String error = (String) request.getAttribute("msg");
        %>

        <h1>Register</h1>
        <%
            if (error != null) {
        %> 
        <p><%=error%></p>
        <%
            }
        %>
        <form action="Register" method="POST">
            <label>Username: </label>
            <input type="text" name="username" required>

            <label>Password: </label>
            <input type="text" name="password" required>

            <label>Confirm password</label>
            <input type="text" name="confirm-password" required>

            <input type="hidden" name="action" value="confirm-register">
            <input type="submit" value="Register">
        </form>

        <br>
        <form action="Auth" method="GET" style="display:inline;">
            <button type="submit">Back</button>
        </form>


        <!--JSTL time!!!-->
        <c:set var="password" value="${param.password}" ></c:set>
        <c:set var="confirmPassword" value="${param['confirm-password']}"></c:set>

        <c:choose>
            <c:when test="${password != confirmPassword}">
                The passwords do not match. Try again
            </c:when>
        </c:choose>
    </body>
</html>
