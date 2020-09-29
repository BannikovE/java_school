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
        <c:url value="/" var="url">
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