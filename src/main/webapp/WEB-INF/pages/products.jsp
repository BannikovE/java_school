<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>PRODUCTS</title>
</head>
<body>
<jsp:include page="_header.jsp" />

<jsp:include page="_menu.jsp" />
<h2>Products</h2>
<form action="<c:url value="/products/filter"/>" enctype="application/x-www-form-urlencoded" method="POST">
    <table>
        <tr>
            <th>
                <label for="name">name</label>
                <input type="text" name="name" id="name">
            </th>
            <th>
                <label for="price">price</label>
                <input type="text" name="price" id="price">
            </th>
            <th>
                <label for="category">category</label>
                <select name="category" id="category">
                    <option value="">-----</option>
                    <c:forEach var="category" items="${categoryList}">
                        <option value="${category.id}">${category.name}</option>
                    </c:forEach>
                </select>
            </th>
            <th>
                <label for="size">size</label>
                <input type="text" name="size" id="size">
            </th>
            <th>
                <label for="brand">brand</label>
                <input type="text" name="brand" id="brand">
            </th>
            <th>
                <label for="color">color</label>
                <input type="text" name="color" id="color">
            </th>
            <th>
                <label for="quantityInStock">Quantity In Stock</label>
                <input type="text" name="quantityInStock" id="quantityInStock">
            </th>
        </tr>
    </table>
    <button type="submit" value="Filter">Filter</button>
    <input type="reset" value="Reset">
</form>
<table>
    <tr>
        <th>name</th>
        <th>price</th>
        <th>category</th>
        <th>size</th>
        <th>brand</th>
        <th>color</th>
        <th>quantity in stock</th>
<%--        <th>action</th>--%>
    </tr>
    <c:forEach var="product" items="${productList}">
        <tr>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.category.name}</td>
            <td>${product.size}</td>
            <td>${product.brand}</td>
            <td>${product.color}</td>
            <td>${product.quantityInStock}</td>
<%--            <sec:authorize access="hasAuthority('write')">--%>
<%--                <td>--%>
<%--                    <a href="/products/edit/${product.id}">edit</a>--%>
<%--                    <a href="/products/delete/${product.id}">delete</a>--%>
<%--                </td>--%>
<%--            </sec:authorize>--%>
            <sec:authorize access="!isAuthenticated()">
                <td>
                    <button onclick="addProduct(${product.id})" type="button" class="button" value="Buy">Buy</button>
                </td>
            </sec:authorize>
            <sec:authorize access="hasAuthority('read')">
                <td>
                    <button onclick="addProduct(${product.id})" type="button" class="button" value="Buy">Buy</button>
                </td>
            </sec:authorize>
        </tr>
    </c:forEach>
    <c:forEach begin="1" end="${pagesCount}" step="1" varStatus="i">
        <c:url value="/products" var="url">
            <c:param name="page" value="${i.index}"/>
        </c:url>
        <a href="${url}">${i.index}</a>
    </c:forEach>
</table>
<sec:authorize access="hasAuthority('write')">
    <h2>Add Product</h2>
    <c:url value="/products/add" var="add"/>
    <a href="${add}">Add new product</a>
    <h2>Add Category</h2>
    <a href="/products/addCategory">Add new category</a>
</sec:authorize>
<script>
    function addProduct(name) {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                alert("Product added to card");
            }
        };
        xhttp.open("POST", "${pageContext.request.contextPath}/products/buy/" + name, true);
        xhttp.send();
    }
</script>
</body>
</html>