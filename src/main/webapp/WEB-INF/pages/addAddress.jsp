<%--
  Created by IntelliJ IDEA.
  User: mi
  Date: 16.10.2020
  Time: 1:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add address</title>
</head>
<body>
<jsp:include page="_header.jsp" />

<jsp:include page="_menu.jsp" />
<form action="/profile/addAddress" method="POST">
    <label for="country">Country</label>
    <input type="text" name="country" id="country">
    <label for="city">City</label>
    <input type="text" name="city" id="city">
    <label for="index">Index</label>
    <input type="number" name="index" id="index">
    <label for="street">Street</label>
    <input type="text" name="street" id="street">
    <label for="building">Building</label>
    <input type="number" name="building" id="building">
    <label for="room">Room</label>
    <input type="number" name="room" id="room">
    <input type="hidden" name="userId" value="${user.id}">
    <input type="submit" value="Add address">
</form>
</body>
</html>
