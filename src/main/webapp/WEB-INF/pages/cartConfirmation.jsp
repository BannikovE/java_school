<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    <h3>Customer Information:</h3>
    <ul>
        <li>Name: ${pageContext.request.cart.customerInfo.name}</li>
        <li>Email: ${myCart.userDTO.email}</li>
        <li>Phone: ${myCart.customerInfo.phone}</li>
        <li>Address: ${myCart.customerInfo.address}</li>
    </ul>
    <h3>Cart Summary:</h3>
    <ul>
        <li>Quantity: ${myCart.quantityTotal}</li>
        <li>Total:
            <span class="total">
            <fmt:formatNumber value="${myCart.amountTotal}" type="currency"/>
          </span></li>
    </ul>
</div>

<form method="POST"
      action="${pageContext.request.contextPath}/cartConfirmation">

    <!-- Edit Cart -->
    <a class="navi-item"
       href="${pageContext.request.contextPath}/cart">Edit Cart</a>

    <!-- Edit Customer Info -->
    <a class="navi-item"
       href="${pageContext.request.contextPath}/cartUser">Edit
        Customer Info</a>

    <!-- Send/Save -->
    <input type="submit" value="Send" class="button-send-sc" />
</form>

<div class="container">

    <c:forEach items="${myCart.cartLines}" var="cartLine">
        <div class="product-preview-container">
            <ul>
                <li>Code: ${cartLine.productInfo.code} <input
                        type="hidden" name="code" value="${cartLine.productInfo.code}" />
                </li>
                <li>Name: ${cartLine.productInfo.name}</li>
                <li>Price: <span class="price">
                     <fmt:formatNumber value="${cartLine.productInfo.price}" type="currency"/>
                  </span>
                </li>
                <li>Quantity: ${cartLine.quantity}</li>
                <li>Subtotal:
                    <span class="subtotal">
                       <fmt:formatNumber value="${cartLine.amount}" type="currency"/>
                    </span>
                </li>
            </ul>
        </div>
    </c:forEach>

</div>
</body>
</html>