<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="header.jsp" %>

<c:if test="${not empty requestScope.compositionOfPizza}">
    <h1><fmt:message key="page.compositionOfPizza"/> "${requestScope.pizzaById.pizzaName}": </h1>
    <ul>
        <c:forEach var="ingredient" items="${requestScope.compositionOfPizza}">
            <li>
                    ${ingredient.ingredientName}
            </li>
        </c:forEach>
    </ul>
</c:if>

<h1><fmt:message key="page.compositionOfPizza.size"/>:</h1>
<c:forEach var="pizzaSize" items="${requestScope.sizes}">
    <label>
        <input type="radio" name="pizzaSize" value="${pizzaSize.size}">
    </label>${pizzaSize.size}
</c:forEach>
<br>
<h1><fmt:message key="page.compositionOfPizza.typeDough"/>:</h1>
<c:forEach var="typeOfPizzaDough" items="${requestScope.typesOfPizzaDough}">
    <label>
        <input type="radio" name="typeOfPizzaDough" value="${typeOfPizzaDough.typeDough}">
    </label>${typeOfPizzaDough.typeDough}
</c:forEach>
<br>
<br>
<label for="numberOfPizzaId"><fmt:message key="page.compositionOfPizza.numberPizza"/>:
    <input type="number" name="numberOfPizza" min="1" id="numberOfPizzaId">
</label><br>
</body>
</html>
