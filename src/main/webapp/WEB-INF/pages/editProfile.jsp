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
<form action="/profile/editProfile" method="POST">
    <input type="hidden" name="id" value="${user.id}">
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
    <label for="stringDateOfBirth">Date Of Birth</label>
    <input type="date" name="stringDateOfBirth" id="stringDateOfBirth">
    <label for="email">Email</label>
    <input type="text" name="email" id="email">
    <label for="password">Password</label>
    <input type="text" name="password" id="password">
    <input type="submit" value="Edit product">
</form>
</body>
</html>
