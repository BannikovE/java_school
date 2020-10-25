<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mi
  Date: 16.10.2020
  Time: 12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Top products</title>
</head>
<body>
<jsp:include page="_header.jsp" />

<jsp:include page="_menu.jsp" />
<div>
    <h4>Top products</h4>
    <table>
        <tr>
            <th>name</th>
            <th>price</th>
            <th>category</th>
            <th>total quantity</th>
        </tr>
        <c:forEach var="entry" items="${products}">
            <tr>
                <td>${entry.key.name}</td>
                <td>${entry.key.price}</td>
                <td>${entry.key.category.name}</td>
                <td>${entry.value}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<div>
    <h4>Top clients</h4>
    <table>
        <tr>
            <th>email</th>
            <th>total amount</th>
        </tr>
        <c:forEach var="entry" items="${users}">
            <tr>
                <td>${entry.key.email}</td>
                <td>${entry.value}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<div>
    <h4>Monthly income</h4>
    <table>
        <tr>
            <th>total amount</th>
        </tr>
        <tr>
            <td>${monthlyIncome}</td>
        </tr>
    </table>
</div>
<div>
    <h4>Daily income</h4>
    <table>
        <tr>
            <th>total amount</th>
        </tr>
        <tr>
            <td>${dailyIncome}</td>
        </tr>
    </table>
</div>
</body>
</html>
