<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/registration" method="post">
    <label for="firstNameId">Firstname:
        <input type="text" name="firstName" id="firstNameId">
    </label><br>
    <label for="lastNameId">Lastname:
        <input type="text" name="lastName" id="lastNameId">
    </label><br>
    <label for="phoneNumberId">Phonenumber:
        <input type="text" name="phoneNumber" id="phoneNumberId">
    </label><br>
    <label for="emailId">Email:
        <input type="text" name="email" id="emailId">
    </label><br>
    <label for="birthDateId">Birthdate:
        <input type="date" name="birthDate" id="birthDateId">
    </label><br>
    <label for="passwordId">Password:
        <input type="password" name="password" id="passwordId">
    </label><br>
    <select name="role" id="roleId">
        <c:forEach var="role" items="${requestScope.roles}">
            <option value="${role}">${role}</option>
        </c:forEach>
    </select><br>
    <c:if test="${not empty requestScope.errors}">
    <div style="color: red">
        <c:forEach var="error" items="${requestScope.errors}">
            <span>${error.message}</span>
        </c:forEach>
    </div>
    </c:if>
    <button type="submit">Send</button>

</form>

</body>
</html>
