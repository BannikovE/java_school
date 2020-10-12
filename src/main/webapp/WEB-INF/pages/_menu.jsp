<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>


<div class="menu-container">

    <a href="${pageContext.request.contextPath}/">Home</a>
    |
    <a href="${pageContext.request.contextPath}/products">
        Product List
    </a>
    |
    <a href="${pageContext.request.contextPath}/cart">
        My Cart
    </a>
    |
    <security:authorize  access="hasAnyAuthority('READ', 'WRITE')">
        <a href="${pageContext.request.contextPath}/orderList">
            Order List
        </a>
        |
    </security:authorize>

    <security:authorize  access="hasAuthority('WRITE')">
        <a href="${pageContext.request.contextPath}/product">
            Create Product
        </a>
        |
    </security:authorize>

</div>