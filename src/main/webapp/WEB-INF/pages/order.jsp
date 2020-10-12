<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>

<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">--%>

</head>
<body>

<jsp:include page="_header.jsp" />
<jsp:include page="_menu.jsp" />

<fmt:setLocale value="en_US" scope="session"/>

<div class="page-title">Order Info</div>

<div class="customer-info-container">
    <h3>Customer Information:</h3>
    <ul>
        <li>Name: ${user.firstName} ${user.lastName}</li>
        <li>Email: ${user.email}</li>
    </ul>
    <h3>Order Summary:</h3>
    <ul>
        <li>Total:
            <span class="total">
           <fmt:formatNumber value="${orderDTO.amount}" type="currency"/>
           </span></li>
    </ul>
</div>

<br/>

<table border="1" style="width:100%">
    <tr>
        <th>Product Code</th>
        <th>Product Name</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Amount</th>
    </tr>
    <c:forEach items="${orderDTO.list}" var="orderListDTO">
        <tr>
            <td>${orderListDTO.product.id}</td>
            <td>${orderListDTO.product.name}</td>
            <td>${orderListDTO.quantity}</td>
            <td>
                <fmt:formatNumber value="${orderListDTO.price}" type="currency"/>
            </td>
            <td>
                <fmt:formatNumber value="${orderListDTO.amount}" type="currency"/>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>