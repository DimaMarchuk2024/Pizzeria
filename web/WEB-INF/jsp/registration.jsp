<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="header.jsp"%>
<form action="${pageContext.request.contextPath}/registration" method="post">
    <label for="firstNameId"><fmt:message key="page.register.firstName"/>:
        <input type="text" name="firstName" id="firstNameId">
    </label><br>
    <label for="lastNameId"><fmt:message key="page.register.lastName"/>:
        <input type="text" name="lastName" id="lastNameId">
    </label><br>
    <label for="phoneNumberId"><fmt:message key="page.register.phoneNumber"/>:
        <input type="text" name="phoneNumber" id="phoneNumberId">
    </label><br>
    <label for="emailId"><fmt:message key="page.register.email"/>:
        <input type="text" name="email" id="emailId">
    </label><br>
    <label for="birthDateId"><fmt:message key="page.register.birthdate"/>:
        <input type="date" name="birthDate" id="birthDateId">
    </label><br>
    <label for="passwordId"><fmt:message key="page.register.password"/>:
        <input type="password" name="password" id="passwordId" >
    </label><br>
    <select name="role" id="roleId">
        <c:forEach var="role" items="${requestScope.roles}">
            <option value="${role.roleName}">${role.roleName}</option>
        </c:forEach>
    </select><br>
    <c:if test="${not empty requestScope.errors}">
    <div style="color: red">
        <c:forEach var="error" items="${requestScope.errors}">
            <span>${error.message}</span>
        </c:forEach>
    </div>
    </c:if>
    <button type="submit"><fmt:message key="page.register.submit.button"/></button>

</form>

</body>
</html>
