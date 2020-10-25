<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
     <c:if test="${empty product.name}">
        <title>Add</title>
    </c:if>
    <c:if test="${!empty product.name}">
        <title>Edit</title>
    </c:if>
</head>
<body>
<jsp:include page="_header.jsp" />

<jsp:include page="_menu.jsp" />
<c:if test="${empty product.name}">
    <c:url value="/products/add" var="var"/>
</c:if>
<c:if test="${!empty product.name}">
    <c:url value="/products/edit" var="var"/>
</c:if>
<div>
    <form:form method="POST" modelAttribute="product" acceptCharset="true" action="${var}">
<%--        <c:if test="${!empty product.name}">--%>
<%--            <input type="hidden" name="id" value="${product.id}">--%>
<%--        </c:if>--%>
        <div>
            <form:input type="text" path="name" placeholder="Name"
                        autofocus="true"></form:input>
            <form:errors path="name"></form:errors>
        </div>
        <div>
            <form:input type="number" path="price" placeholder="Price"
                        autofocus="true"></form:input>
            <form:errors path="price"></form:errors>
        </div>
        <div>
            <form:select path="categoryId" placeholder="CategoryId"
                        autofocus="true">
                <form:option value="">Choose Category</form:option>
                <c:forEach var="category" items="${categoryList}">
                    <form:option value="${category.id}">${category.name}</form:option>
                </c:forEach>
            </form:select>
            <form:errors path="categoryId"></form:errors>
        </div>
        <div>
            <form:input type="number" path="size" placeholder="Size"
                        autofocus="true"></form:input>
            <form:errors path="size"></form:errors>
        </div>
        <div>
            <form:input type="text" path="brand" placeholder="Brand"
                        autofocus="true"></form:input>
            <form:errors path="brand"></form:errors>
        </div>
        <div>
            <form:input type="text" path="color" placeholder="Color"
                        autofocus="true"></form:input>
            <form:errors path="color"></form:errors>
        </div>
        <div>
            <form:input type="number" path="quantityInStock" placeholder="Quantity"
                        autofocus="true"></form:input>
            <form:errors path="quantityInStock"></form:errors>
        </div>
        <button type="submit">Add</button>
    </form:form>
</div>
<%--<form action="${var}" method="POST">--%>
<%--    <c:if test="${!empty product.name}">--%>
<%--        <input type="hidden" name="id" value="${product.id}">--%>
<%--    </c:if>--%>
<%--    <label for="name">Name</label>--%>
<%--    <input type="text" name="name" id="name">--%>
<%--    <label for="price">Price</label>--%>
<%--    <input type="text" name="price" id="price">--%>
<%--    <label for="categoryId">Category</label>--%>
<%--    <select name="categoryId" id="categoryId">--%>
<%--        <option value="">-----</option>--%>
<%--        <c:forEach var="category" items="${categoryList}">--%>
<%--            <option value="${category.id}">${category.name}</option>--%>
<%--        </c:forEach>--%>
<%--    </select>--%>
<%--    <label for="size">Size</label>--%>
<%--    <input type="text" name="size" id="size">--%>
<%--    <label for="brand">Brand</label>--%>
<%--    <input type="text" name="brand" id="brand">--%>
<%--    <label for="color">Color</label>--%>
<%--    <input type="text" name="color" id="color">--%>
<%--    <label for="quantityInStock">Quantity In Stock</label>--%>
<%--    <input type="text" name="quantityInStock" id="quantityInStock">--%>
<%--    <c:if test="${empty product.name}">--%>
<%--        <input type="submit" value="Add new product">--%>
<%--    </c:if>--%>
<%--    <c:if test="${!empty product.name}">--%>
<%--        <input type="submit" value="Edit product">--%>
<%--    </c:if>--%>
<%--</form>--%>
</body>
</html>
