<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <title>Shopping Cart</title>

<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">--%>

</head>
<body>
<jsp:include page="_header.jsp" />

<jsp:include page="_menu.jsp" />

<fmt:setLocale value="en_US" scope="session"/>

<div class="page-title">My Cart</div>

<c:if test="${empty cart or empty cart.cartLines}">
    <h2>There is no items in Cart</h2>
    <a href="${pageContext.request.contextPath}/products">Show
        Product List</a>
</c:if>

<c:if test="${not empty cart and not empty cart.cartLines   }">
    <form:form method="POST" modelAttribute="cart"
               action="${pageContext.request.contextPath}/cart">

        <c:forEach items="${cart.cartLines}" var="cartLine"
                   varStatus="varStatus">
            <div class="product-preview-container">
                <ul>
                    <li>Id: ${cartLine.product.id}

                    </li>
                    <li>Name: ${cartLine.product.name}</li>
                    <li>Price: <span class="price">

                         <fmt:formatNumber value="${cartLine.product.price}" type="currency"/>

                       </span></li>
                    <li>Quantity: ${cartLine.quantity}
                    <li>Subtotal:
                        <span class="subtotal">

                            <fmt:formatNumber value="${cartLine.amount}" type="currency"/>

                         </span>
                    </li>
                    <li><a
                            href="${pageContext.request.contextPath}/cartRemoveProduct?id=${cartLine.product.id}">
                        Delete </a></li>
                </ul>
            </div>
        </c:forEach>
        <div style="clear: both"></div>
        <input class="button-update-sc" type="submit" value="Update Quantity" />
        <a class="navi-item"
           href="${pageContext.request.contextPath}/cartUser">Enter
            Customer Info</a>
        <a class="navi-item"
           href="${pageContext.request.contextPath}/products">Continue
            Buy</a>
    </form:form>


</c:if>
</body>
</html>