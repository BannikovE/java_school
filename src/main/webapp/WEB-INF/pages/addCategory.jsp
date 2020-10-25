<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: mi
  Date: 16.10.2020
  Time: 3:43
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
<form:form method="POST" modelAttribute="category" acceptCharset="true">
    <div>
        <form:input type="text" path="name" placeholder="Name"
                    autofocus="true"></form:input>
        <form:errors path="name"></form:errors>
    </div>
    <input type="submit" value="Add category">
</form:form>
</body>
</html>
