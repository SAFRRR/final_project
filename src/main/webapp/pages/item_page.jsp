<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="common.items" var="locale_common_items"/>
<fmt:message key="common.create.user" var="locale_create_user"/>
<fmt:message key="main.category" var="locale_main_category"/>
<fmt:message key="main.category.enter" var="locale_main_category_enter"/>
<fmt:message key="main.search" var="locale_main_search"/>
<fmt:message key="main.clear" var="locale_main_clear"/>
<fmt:message key="common.dessert.detail" var="locale_dessert_detail"/>


<!doctype html>
<html lang="en">
<body >
<jsp:include page="common/header.jsp"/>

    <div class="row" style="  margin-top: 20px;" >
            <h2 class="section-headline">
                <span>Category</span>
            </h2>
    </div>
    <div class="container" style=" display: flex; flex-direction: row; flex-wrap: wrap;  align-items: center; justify-content: center;">

        <c:if test="${emptyList}">
            <h5 style="font-style: italic">Ooops, no result is found. Try something else)</h5>
        </c:if>

        <c:forEach var="dessert" items="${dessertList}">
        <c:if test="${dessert != null}">

        <form class="form-horizontal" style="width: 400px; height:600px; margin-top: 50px; margin-left: 0;margin-right: 20px; " >
            <input type="hidden" value="${dessert.id}" name="dessertId">

            <a href="${pageContext.request.contextPath}/controller?command=go_to_dessert_detail_page_command&dessertId=${dessert.id}">
                <img class="img-responsive" src="./static/images/${dessert.dessertImage}" style="height: 313px;" alt=""/>
            </a>

            <h4 style="text-align: center; margin-top: 15px">${dessert.name}</h4>
            <p style="text-align: center;margin-top: 10px">${dessert.price} BYN</p>
<%--            <p style="text-align: center;">${dessert.weight} gm</p>--%>

            <p style="width: 313px;text-align: center;">${fn:substring(dessert.description, 0, 500)}</p>
        </form>
        </c:if>
        </c:forEach>
    </div>
</body>
</html>
