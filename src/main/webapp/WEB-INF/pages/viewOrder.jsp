<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: mi
  Date: 16.10.2020
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <th>product</th>
        <th>quantity</th>
        <th>price</th>
        <th>amount</th>
    </tr>
    <c:forEach var="orderList" items="${order.orderList}">
        <tr>
            <td>${orderList.productId}</td>
            <td>${orderList.quantity}</td>
            <td>${orderList.price}</td>
            <td>${orderList.amount}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
