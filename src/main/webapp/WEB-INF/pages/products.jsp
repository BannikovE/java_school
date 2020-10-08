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
    <title>PRODUCTS</title>
</head>
<body>

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