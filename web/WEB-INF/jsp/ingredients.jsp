<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Ингредиенты:</h1>
    <ul>
        <c:forEach var="ingredient" items="${requestScope.ingredients}">
            <li>
                ${ingredient.ingredientName} = ${ingredient.costOfIngredient} руб.
            </li>
        </c:forEach>
    </ul><br>
    <br>
    <h1>Добавить ингредиент:</h1>
    <form action="${pageContext.request.contextPath}/ingredients" method="post">
        <label for="ingredientNameId">Ингредиент:
            <input type="text" name="ingredientName" id="ingredientNameId">
        </label><br>
        <label for="costOfIngredientId">Цена:
            <input type="text" name="costOfIngredient" id="costOfIngredientId"> руб.
        </label><br>
        <button type="submit">Добавить</button>
    </form>
</body>
</html>
