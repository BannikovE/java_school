<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product</title>

<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">--%>

</head>
<body>

<jsp:include page="_header.jsp" />
<jsp:include page="_menu.jsp" />

<div class="page-title">Product</div>

<c:if test="${not empty message }">
    <div class="error-message">
            ${message}
    </div>
</c:if>

<form:form modelAttribute="product" method="POST" enctype="multipart/form-data">
    <table style="text-align:left;">
        <tr>
            <td>Id *</td>
            <td style="color:red;">
                <c:if test="${not empty product.id}">
                    <form:hidden path="id"/>
                    ${product.id}
                </c:if>
                <c:if test="${empty product.id}">
                    <form:input path="quantityInStock" />
                    <form:hidden path="newProduct" />
                </c:if>
            </td>
            <td><form:errors path="id" class="error-message" /></td>
        </tr>

        <tr>
            <td>Name *</td>
            <td><form:input path="name" /></td>
            <td><form:errors path="name" class="error-message" /></td>
        </tr>

        <tr>
            <td>Price *</td>
            <td><form:input path="price" /></td>
            <td><form:errors path="price" class="error-message" /></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td><input type="submit" value="Submit" /> <input type="reset"
                                                              value="Reset" /></td>
        </tr>
    </table>
</form:form>
</body>
</html>