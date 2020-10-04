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
    <link href="<c:url value="/resources/style.css"/>" rel="stylesheet" type="text/css" />
    <c:if test="${empty product.name}">
        <title>Add</title>
    </c:if>
    <c:if test="${!empty product.name}">
        <title>Edit</title>
    </c:if>
</head>
<body>
<c:if test="${empty product.name}">
    <c:url value="/add" var="var"/>
</c:if>
<c:if test="${!empty product.name}">
    <c:url value="/edit" var="var"/>
</c:if>
<form action="${var}" method="POST">
    <c:if test="${!empty product.name}">
        <input type="hidden" name="id" value="${product.id}">
    </c:if>
    <label for="name">Name</label>
    <input type="text" name="name" id="name">
    <label for="price">Price</label>
    <input type="text" name="price" id="price">
    <label for="categoryId">Category</label>
<%--    <input type="text" name="category" id="category">--%>
    <select name="categoryId" id="categoryId">
        <option value="${categoryList.get(0).id}">Jeans</option>
        <option value="${categoryList.get(1).id}">Shirt</option>
        <option value="${categoryList.get(2).id}">T-Shirt</option>
    </select>
    <label for="size">Size</label>
    <input type="text" name="size" id="size">
    <label for="brand">Brand</label>
    <input type="text" name="brand" id="brand">
    <label for="color">Color</label>
    <input type="text" name="color" id="color">
    <c:if test="${empty product.name}">
        <input type="submit" value="Add new product">
    </c:if>
    <c:if test="${!empty product.name}">
        <input type="submit" value="Edit product">
    </c:if>
</form>
</body>
</html>
