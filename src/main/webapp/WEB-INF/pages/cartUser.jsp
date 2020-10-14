<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


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

<form:form method="POST" modelAttribute="userDTO"
           action="${pageContext.request.contextPath}/cartUser">

    <h3>${pageContext.request.userPrincipal.name}</h3>
    <sec:authorize access="!isAuthenticated()">
        <h4><a href="/auth/login">Sign In</a></h4>
        <h4><a href="/auth/signUp">Sign Up</a></h4>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <h4><a href="/auth/logout">Logout</a></h4>
        <table>
            <tr>
                <th>id</th>
                <th>first name</th>
                <th>last name</th>
                <th>date of birth</th>
                <th>email</th>
                <th>password</th>
                <th>role</th>
                <th>status</th>
            </tr>
                <%--    <c:param name="user" value="&{user}">--%>
            <tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.dateOfBirth}</td>
                <td>${user.email}</td>
                <td>${user.password}</td>
                <td>${user.role}</td>
                <td>${user.status}</td>
            </tr>
                <%--    </c:param>--%>
        </table>
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
            <td><form:input path="stringDateOfBirth" /></td>
            <td><form:errors path="stringDateOfBirth" class="error-message" /></td>
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
    </sec:authorize>
</form:form>
</body>
</html>