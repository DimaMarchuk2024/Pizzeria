
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${not empty requestScope.compositionOfPizza}">
    <h1>Ингредиенты пиццы "${requestScope.pizzaById.pizzaName}": </h1>
    <ul>
        <c:forEach var="ingredient" items="${requestScope.compositionOfPizza}">
            <li>
                    ${ingredient.ingredientName}
            </li>
        </c:forEach>
    </ul>
</c:if>

</body>
</html>
