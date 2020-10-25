<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mi
  Date: 16.10.2020
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="_header.jsp" />

<jsp:include page="_menu.jsp" />
<form action="/orders/manage" method="POST">
    <input type="hidden" name="id" value="${order.id}">
    <input type="hidden" name="deliveryMethod" value="${order.deliveryMethod}">
    <label for="orderStatus">Status</label>
    <select name="orderStatus" id="orderStatus">
        <c:forEach items="${statuses}" var="value">
            <option value="${value}">${value}</option>
        </c:forEach>
    </select>
    <input type="hidden" name="paymentMethod" value="${order.paymentMethod}">
    <label for="paymentState">Payment State</label>
    <select name="paymentState" id="paymentState">
        <c:forEach items="${states}" var="value">
            <option value="${value}">${value}</option>
        </c:forEach>
    </select>
    <input type="hidden" name="addressId" value="${order.address.id}">
    <input type="hidden" name="userId" value="${order.userId}">
    <input type="hidden" name="amount" value="${order.amount}">
    <input type="hidden" name="ordersNum" value="${order.orderNum}">
    <input type="submit" value="Edit order">
</form>
</body>
</html>
