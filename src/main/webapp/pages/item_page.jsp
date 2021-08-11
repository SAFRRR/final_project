<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<body >
<jsp:include page="common/header.jsp"/>
    <div class="row" style="margin-top: 20px; color: #e1791a; margin-left: 650px" >
            <h2 class="section-headline">
                <span>${dessertTypeSelected.description}</span>
            </h2>
    </div>
    <div class="container" style=" display: flex; flex-direction: row; flex-wrap: wrap;  align-items: center; justify-content: center;">
        <c:forEach var="dessert" items="${dessertList}">
        <c:if test="${dessert != null}">
            <form class="form-horizontal" style="width: 400px; height:600px; margin-top: 50px; margin-left: 0;margin-right: 20px; " >
                <input type="hidden" value="${dessert.id}" name="dessertId">
                <a href="${pageContext.request.contextPath}/controller?command=go_to_dessert_detail_page_command&dessertId=${dessert.id}">
                    <img class="img-responsive" src="./static/images/${dessert.dessertImage}" style="height: 313px;" alt=""/>
                </a>
                <h4 style="text-align: center; margin-top: 15px">${dessert.name}</h4>
                <p style="text-align: center;margin-top: 10px">${dessert.price} BYN</p>
                <p style="width: 313px;text-align: center;">${fn:substring(dessert.description, 0, 300)}</p>
         </form>
        </c:if>
        </c:forEach>
    </div>
</body>
</html>
