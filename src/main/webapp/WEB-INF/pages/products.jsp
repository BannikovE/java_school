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
    <title>PRODUCTS</title>
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
            <a class="nav-link active" href="${pageContext.request.contextPath}/products">Products</a>
            <a class="nav-link" href="#">Cart</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/auth/login">Login</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/profile">Profile</a>
        </div>
    </div>
</nav>

<h2>Products</h2>
<form action="<c:url value="/products/filter"/>" enctype="application/x-www-form-urlencoded" method="POST">
    <table>
        <tr>
            <th>
                <label for="name">name</label>
                <input type="text" name="name" id="name">
            </th>
            <th>
                <label for="price">price</label>
                <input type="text" name="price" id="price">
            </th>
            <th>
                <label for="category">category</label>
                <select name="category" id="category">
                    <option value="">-----</option>
                    <c:forEach var="category" items="${categoryList}">
                        <option value="${category.id}">${category.name}</option>
                    </c:forEach>
                </select>
            </th>
            <th>
                <label for="size">size</label>
                <input type="text" name="size" id="size">
            </th>
            <th>
                <label for="brand">brand</label>
                <input type="text" name="brand" id="brand">
            </th>
            <th>
                <label for="color">color</label>
                <input type="text" name="color" id="color">
            </th>
            <th>
                <label for="action">action</label>
                <input type="text" name="action" id="action">
            </th>
        </tr>
    </table>
    <button type="submit" value="Filter">Filter</button>
    <input type="reset" value="Reset">
</form>
<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>price</th>
        <th>category</th>
        <th>size</th>
        <th>brand</th>
        <th>color</th>
        <th>action</th>
    </tr>
    <c:forEach var="product" items="${productList}">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.category.name}</td>
            <td>${product.size}</td>
            <td>${product.brand}</td>
            <td>${product.color}</td>
            <td>
                <a href="/edit/${product.id}">edit</a>
                <a href="/delete/${product.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
    <c:forEach begin="1" end="${pagesCount}" step="1" varStatus="i">
        <c:url value="/products" var="url">
            <c:param name="page" value="${i.index}"/>
        </c:url>
        <a href="${url}">${i.index}</a>
    </c:forEach>
</table>

<h2>Add</h2>
<c:url value="/add" var="add"/>
<a href="${add}">Add new product</a>
</body>
</html>