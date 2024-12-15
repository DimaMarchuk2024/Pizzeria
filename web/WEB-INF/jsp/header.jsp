<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

 <div>
     <c:if test="${not empty sessionScope.userPizzeriaDto}">
         <div id="logoutId">
             <form action="${pageContext.request.contextPath}/logout" method="post">
                 <button type="submit">Logout</button>
             </form>
         </div>
     </c:if>

     <div id="localeId">
         <form action="${pageContext.request.contextPath}/locale" method="post">
             <button type="submit" name="lang" value="ru_RU">RU</button>
             <button type="submit" name="lang" value="en_US">EN</button>
         </form>
     </div>
     <fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : (param.lang != null ? param.lang : 'ru_RU')}"/>
     <fmt:setBundle basename="translations"/>
 </div>

