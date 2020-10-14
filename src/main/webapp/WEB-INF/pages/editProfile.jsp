<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mi
  Date: 26.09.2020
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <c:if test="${empty product.name}">
        <title>Add</title>
    </c:if>
    <c:if test="${!empty product.name}">
        <title>Edit</title>
    </c:if>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">MyMarket</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/products">Products</a>
            <a class="nav-link" href="#">Cart</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/auth/login">Login</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/profile">Profile</a>
        </div>
    </div>
</nav>
<form action="/profile/editProfile" method="POST">
    <label for="firstName">First Name</label>
    <input type="text" name="firstName" id="firstName">
    <label for="lastName">Last Name</label>
    <input type="text" name="lastName" id="lastName">
<%--    <label for="categoryId">Category</label>--%>
<%--    <select name="categoryId" id="categoryId">--%>
<%--        <option value="">-----</option>--%>
<%--        <c:forEach var="category" items="${categoryList}">--%>
<%--            <option value="${category.id}">${category.name}</option>--%>
<%--        </c:forEach>--%>
<%--    </select>--%>
    <label for="dateOfBirth">Date Of Birth</label>
    <input type="date" name="dateOfBirth" id="dateOfBirth">
    <label for="email">Email</label>
    <input type="text" name="email" id="email">
    <label for="password">Password</label>
    <input type="text" name="password" id="password">
    <input type="submit" value="Edit product">
</form>
</body>
</html>
