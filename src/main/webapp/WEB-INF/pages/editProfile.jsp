<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        Edit Profile
    </title>
</head>
<body>
<jsp:include page="_header.jsp" />

<jsp:include page="_menu.jsp" />
    <form:form method="POST" modelAttribute="user" acceptCharset="true" action="/profile/editProfile">
        <div>
            <form:input type="hidden" path="id" placeholder="Id"
                        autofocus="true" ></form:input>
            <form:errors path="id"></form:errors>
        </div>
        <div>
            <form:input type="hidden" path="role" placeholder="Id"
                        autofocus="true" ></form:input>
            <form:errors path="role"></form:errors>
        </div>
        <div>
            <form:input type="hidden" path="status" placeholder="Id"
                        autofocus="true" ></form:input>
            <form:errors path="status"></form:errors>
        </div>
        <div>
        <form:input type="text" path="firstName" placeholder="First Name"
                    autofocus="true" ></form:input>
        <form:errors path="firstName"></form:errors>
        </div>
        <div>
        <form:input type="text" path="lastName" placeholder="Last Name"
                    autofocus="true"></form:input>
        <form:errors path="lastName"></form:errors>
        </div>
        <div>
        <form:input type="date" path="stringDateOfBirth" placeholder="Date of Birth"
                    autofocus="true"></form:input>
        <form:errors path="stringDateOfBirth"></form:errors>
        </div>
        <div>
        <form:input type="text" path="email" placeholder="Email"
                    autofocus="true"></form:input>
        <form:errors path="email"></form:errors>
        </div>
        <div>
        <form:input type="password" path="password" placeholder="Password"
                    autofocus="true"></form:input>
        <form:errors path="password"></form:errors>
        </div>
        <div>
        <form:input type="password" path="passwordConfirm" placeholder="Password Confirm"
                    autofocus="true"></form:input>
        <form:errors path="passwordConfirm"></form:errors>
        </div>
        <button type="submit">Edit</button>
    </form:form>
<%--<form action="/profile/editProfile" method="POST">--%>
<%--    <input type="hidden" name="id" value="${user.id}">--%>
<%--    <label for="firstName">First Name</label>--%>
<%--    <input type="text" name="firstName" id="firstName" value="${user.firstName}">--%>
<%--    <label for="lastName">Last Name</label>--%>
<%--    <input type="text" name="lastName" id="lastName" value="${user.lastName}">--%>
<%--    <label for="stringDateOfBirth">Date Of Birth</label>--%>
<%--    <input type="date" name="stringDateOfBirth" id="stringDateOfBirth" value="${user.dateOfBirth}">--%>
<%--    <label for="email">Email</label>--%>
<%--    <input type="text" name="email" id="email" value="${user.email}">--%>
<%--    <label for="password">Password</label>--%>
<%--    <input type="password" name="password" id="password">--%>
<%--    <label for="passwordConfirm">Password Confirm</label>--%>
<%--    <input type="password" name="passwordConfirm" id="passwordConfirm">--%>
<%--    <input type="hidden" name="role" value="${user.role}">--%>
<%--    <input type="hidden" name="status" value="${user.status}">--%>
<%--    <input type="submit" value="Edit profile">--%>
<%--</form>--%>
</body>
</html>
