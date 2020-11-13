<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mi
  Date: 28.10.2020
  Time: 23:52
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
    <c:forEach var="product" items="${errorList}">
        <h4>You can't buy ${product.name}, because quantity in stock is : ${product.quantityInStock}</h4>
    </c:forEach>
    <a href="/cart">Back to cart</a>
</body>
</html>
