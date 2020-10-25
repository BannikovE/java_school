<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: mi
  Date: 16.10.2020
  Time: 9:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage orders</title>
</head>
<body>
<jsp:include page="_header.jsp" />

<jsp:include page="_menu.jsp" />
<table border="1" style="width:100%">
    <tr>
        <th>Delivery Method</th>
        <th>Order Status</th>
        <th>Payment Method</th>
        <th>Order Payment State</th>
        <th>Order Address</th>
        <th>Amount</th>
        <th>View</th>
    </tr>
    <c:forEach items="${orders}" var="order">
    <tr>
        <td>${order.deliveryMethod}</td>
        <td>${order.orderStatus}</td>
        <td>${order.paymentMethod}</td>
        <td>${order.paymentState}</td>
        <td>${order.address.toString()}</td>
        <td style="color:red;">
            <fmt:formatNumber value="${order.amount}" type="number"/>
        </td>
        <td><a href="${pageContext.request.contextPath}/orders/manage/${order.id}">
            View</a></td>
    </tr>
    </c:forEach>
</body>
</html>
