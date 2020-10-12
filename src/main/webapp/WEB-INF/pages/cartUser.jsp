<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <title>Enter Customer Information</title>

<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">--%>

</head>
<body>
<jsp:include page="_header.jsp" />
<jsp:include page="_menu.jsp" />

<div class="page-title">Enter Customer Information</div>

<form:form method="POST" modelAttribute="user"
           action="${pageContext.request.contextPath}/cartUser">

    <table>
        <tr>
            <td>Name *</td>
            <td><form:input path="firstName" /></td>
            <td><form:errors path="firstName" class="error-message" /></td>
        </tr>

        <tr>
            <td>LName *</td>
            <td><form:input path="lastName" /></td>
            <td><form:errors path="lastName" class="error-message" /></td>
        </tr>

        <tr>
            <td>DOB *</td>
            <td><form:input path="dateOfBirth" /></td>
            <td><form:errors path="dateOfBirth" class="error-message" /></td>
        </tr>

        <tr>
            <td>Email *</td>
            <td><form:input path="email" /></td>
            <td><form:errors path="email" class="error-message" /></td>
        </tr>

        <tr>
            <td>Password *</td>
            <td><form:input path="password" /></td>
            <td><form:errors path="password" class="error-message" /></td>
        </tr>

<%--        <tr>--%>
<%--            <td>Phone *</td>--%>
<%--            <td><form:input path="phone" /></td>--%>
<%--            <td><form:errors path="phone" class="error-message" /></td>--%>
<%--        </tr>--%>
<%----%>
<%--        <tr>--%>
<%--            <td>Address *</td>--%>
<%--            <td><form:input path="address" /></td>--%>
<%--            <td><form:errors path="address" class="error-message" /></td>--%>
<%--        </tr>--%>

        <tr>
            <td>&nbsp;</td>
            <td><input type="submit" value="Submit" /> <input type="reset"
                                                              value="Reset" /></td>
        </tr>
    </table>

</form:form>
</body>
</html>