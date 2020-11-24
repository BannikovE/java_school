<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: mi
  Date: 24.11.2020
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Repeat Order</title>
</head>
<body>
<jsp:include page="_header.jsp" />

<jsp:include page="_menu.jsp" />
    <h4>Do you want to repeat this order?</h4>
    <form:form method="POST" modelAttribute="order" acceptCharset="true" action="${pageContext.request.contextPath}/orders/repeat">
        <div>
            <form:input type="hidden" path="id" placeholder="Id"
                        autofocus="true" ></form:input>
        </div>
        <div>
            <form:select path="deliveryMethod" placeholder="DeliveryMethod"
                         autofocus="true">
                <form:option value="">Choose Delivery Method</form:option>
                <c:forEach var="method" items="${deliveryMethods}">
                    <form:option value="${method}">${method}</form:option>
                </c:forEach>
            </form:select>
        </div>
        <div>
            <form:select path="paymentMethod" placeholder="PaymentMethod"
                         autofocus="true">
                <form:option value="">Choose Payment Method</form:option>
                <c:forEach var="method" items="${paymentMethods}">
                    <form:option value="${method}">${method}</form:option>
                </c:forEach>
            </form:select>
        </div>
        <div>
            <form:select path="addressId" placeholder="AddressId"
                         autofocus="true">
                <form:option value="">Choose Address</form:option>
                <c:forEach var="address" items="${addresses}">
                    <form:option value="${address.id}">${address.toString()}</form:option>
                </c:forEach>
            </form:select>
        </div>
        <button type="submit">Repeat</button>
    </form:form>
    <div class="container">
        <c:forEach items="${order.orderList}" var="orderList">
            <div class="product-preview-container">
                <ul>
                    <li>Name: ${orderList.productId}</li>
                    <li>Price: <span class="price">
                         <fmt:formatNumber value="${orderList.price}" type="currency"/>
                      </span>
                    </li>
                    <li>Quantity: ${orderList.quantity}</li>
                    <li>Subtotal:
                        <span class="subtotal">
                           <fmt:formatNumber value="${orderList.amount}" type="currency"/>
                        </span>
                    </li>
                </ul>
            </div>
        </c:forEach>
    </div>
</body>
</html>
