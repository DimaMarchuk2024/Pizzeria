<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="header.jsp"%>

    <h1><fmt:message key="page.pizzas"/>:</h1>
    <ul>
        <c:forEach var="pizza" items="${requestScope.pizzas}">
            <li>
                <a href="${pageContext.request.contextPath}/compositionOfPizza?pizzaId=${pizza.id}">${pizza.pizzaName}</a>
            </li>
        </c:forEach>
    </ul><br>
    <br>
    <c:if test="${sessionScope.userPizzeriaDto.roleName eq 'Admin'}">
    <h1><fmt:message key="page.pizzas.savePizza"/>:</h1>
    <form action="${pageContext.request.contextPath}/pizzas" method="post">
        <label for="pizzaNameId"><fmt:message key="page.pizzas.name"/>:
            <input type="text" name="pizzaName" id="pizzaNameId">
        </label><br>
        <button type="submit"><fmt:message key="page.pizzas.add.button"/></button>
    </form>
    </c:if>
</body>
</html>
