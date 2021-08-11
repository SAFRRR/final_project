<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="main.intro" var="locale_main_intro"/>
<fmt:message key="main.intro.second" var="locale_main_intro_second"/>

<!DOCTYPE html>
<!doctype html>
<html lang="en">
<body>
<jsp:include page="common/header.jsp"/>
<img class="home-image" src="../static/images/bakery1.jpg" alt="">
<p class="home-intro" style="margin-top: 90px;">${locale_main_intro}</p>
<p class="home-intro" style="margin-top: 15px;">${locale_main_intro_second}</p>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
