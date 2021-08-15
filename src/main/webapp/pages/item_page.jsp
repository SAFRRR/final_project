<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="empty.list" var="locale_empty_list"/>


<!doctype html>
<html lang="en">
<body >
<jsp:include page="common/header.jsp"/>
    <div class="row" style="margin-top: 20px; color: #e1791a; margin-left: 650px" >
            <h2 class="section-headline">
                <span>${dessertTypeSelected.description}</span>
            </h2>
    </div>
    <c:if test="${emptyList}">
        <h3 style="color: #e1791a; text-align: center; margin-top: 20px;">${locale_empty_list}</h3>
    </c:if>
    <div class="container" style=" display: flex; flex-direction: row; flex-wrap: wrap;  align-items: center; justify-content: center;">
        <c:forEach var="dessert" items="${dessertList}">
            <c:if test="${dessert != null}">
                <form class="form-horizontal" style="width: 400px; height:600px; margin: 20px 20px 10px 0;" >
                    <input type="hidden" value="${dessert.id}" name="dessertId">
                    <a href="${pageContext.request.contextPath}/controller?command=go_to_dessert_detail_page_command&dessertId=${dessert.id}">
                        <img class="img-responsive" src="${pageContext.request.contextPath}/static/images/${dessert.dessertImage}" style="height: 313px;" alt="">
                    </a>
                    <h4 style="text-align: center; margin-top: 15px">${dessert.name}</h4>
                    <p style="text-align: center;"><span > ${dessert.price} BYN (</span><span>${dessert.weight} g )</span></p>
                    <p style="width: 313px;text-align: center;">${fn:substring(dessert.description, 0, 300)}</p>
                </form>
            </c:if>
        </c:forEach>

    </div>
</body>
</html>
