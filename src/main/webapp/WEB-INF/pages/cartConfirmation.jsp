<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <title>Shopping Cart Confirmation</title>

<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">--%>

</head>
<body>
<jsp:include page="_header.jsp" />

<jsp:include page="_menu.jsp" />

<fmt:setLocale value="en_US" scope="session"/>

<div class="page-title">Confirmation</div>



<div class="customer-info-container">
    <h3>Cart Summary:</h3>
    <ul>
        <li>Quantity: ${cart.quantityTotal}</li>
        <li>Total:
            <span class="total">
            <fmt:formatNumber value="${cart.amountTotal}" type="currency"/>
          </span></li>
    </ul>
</div>
<form:form method="POST" modelAttribute="order" acceptCharset="true" action="${pageContext.request.contextPath}/orders/create">
    <div>
        <form:select path="deliveryMethod" placeholder="DeliveryMethod"
                     autofocus="true">
            <form:option value="">Choose Delivery Method</form:option>
            <c:forEach var="method" items="${deliveryMethods}">
                <form:option value="${method}">${method}</form:option>
            </c:forEach>
        </form:select>
    </div
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
    <button type="submit">Send</button>
</form:form>
<div class="container">
    <c:forEach items="${cart.cartLines}" var="orderList">
        <div class="product-preview-container">
            <ul>
                <li>Name: ${orderList.productDTO.name}</li>
                <li>Price: <span class="price">
                     <fmt:formatNumber value="${orderList.productDTO.price}" type="currency"/>
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