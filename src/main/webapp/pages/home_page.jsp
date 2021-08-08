<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<!DOCTYPE html>
<!doctype html>
<html lang="en">
<body>
<jsp:include page="common/header.jsp"/>

<img class="home-image" src="../static/images/bakery1.jpg" style="height: 500px; margin-left: 60px; margin-top: 55px ">

<jsp:include page="common/footer.jsp"/>
</body>
</html>
