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
    <h1><fmt:message key="page.ingredients"/>:</h1>
    <ul>
        <c:forEach var="ingredient" items="${requestScope.ingredients}">
            <li>
                ${ingredient.ingredientName} = ${ingredient.costOfIngredient} руб.
            </li>
        </c:forEach>
    </ul><br>
    <br>
    <h1><fmt:message key="page.ingredients.saveIngredient"/>:</h1>
    <form action="${pageContext.request.contextPath}/ingredients" method="post">
        <label for="ingredientNameId"><fmt:message key="page.ingredients.name"/>:
            <input type="text" name="ingredientName" id="ingredientNameId">
        </label><br>
        <label for="costOfIngredientId"><fmt:message key="page.ingredients.cost"/>:
            <input type="text" name="costOfIngredient" id="costOfIngredientId"> <fmt:message key="page.ingredients.currency"/>
        </label><br>
        <button type="submit"><fmt:message key="page.ingredients.add.button"/></button>
    </form>
</body>
</html>
