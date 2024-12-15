<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Пиццы:</h1>
    <ul>
        <c:forEach var="pizza" items="${requestScope.pizzas}">
            <li>
                <a href="${pageContext.request.contextPath}/compositionOfPizza?pizzaId=${pizza.id}">${pizza.pizzaName}</a>
            </li>
        </c:forEach>
    </ul><br>
    <br>
    <h1>Добавить пиццу:</h1>
    <form action="${pageContext.request.contextPath}/pizzas" method="post">
        <label for="pizzaNameId">Название:
            <input type="text" name="pizzaName" id="pizzaNameId">
        </label><br>
        <button type="submit">Добавить</button>
    </form>
</body>
</html>
