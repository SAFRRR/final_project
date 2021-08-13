<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="user.storage.dessert" var="locale_storage_dessert"/>
<fmt:message key="user.storage.only" var="locale_storage_only"/>
<fmt:message key="user.dessert.unavailable" var="locale_dessert_unavailable"/>
<fmt:message key="user.basket.add" var="locale_basket_add"/>
<fmt:message key="main.add.basket" var="locale_add_basket"/>
<fmt:message key="main.problem.basket" var="locale_problem_basket"/>

<!doctype html>
<html lang="en">
<body>
<jsp:include page="common/header.jsp"/>
<div class="container">
    <div class="back" style="float: left">
        <a style="color: #e1791a;" href="${pageContext.request.contextPath}/controller?command=find_dessert_by_category_command&category=${dessert.dessertType.id}">
            <i class="fas fa-chevron-left"></i>
        </a>
    </div>


    <form class="form-horizontal" action="controller" method="post" style="width: 500px; margin-top: 10px; margin-left: 400px;margin-right: 20px; " >
        <input type="hidden" name="command" value="add_dessert_to_basket_command">
        <input hidden="hidden" name="dessertId" value="${dessert.id}"/>
        <input hidden="hidden" name="dessertPrice" value="${dessert.price}"/>
        <input hidden="hidden" name="dessertWeight" value="${dessert.weight}"/>
        <input hidden="hidden" name="storageAmount" value="${storage.count}"/>

        <img style="height: 313px; margin-left:50px;" class="img-responsive page-dessert" src="./static/images/${dessert.dessertImage}"/>

        <h4 style="text-align: center; margin-top: 15px">${dessert.name}</h4>

        <p style="width: 413px; text-align: center;">${fn:substring(dessert.description, 0, 300)}</p>

        <p style="text-align: center;"><span > ${dessert.price} BYN (</span><span>${dessert.weight} g )</span></p>

        <c:if test="${storage.count > 0}">
            <input required type="number" step="1"
                   class="form-control" name="amount" min="1"
                   max="${storage.count}"
                   placeholder="${locale_order_amount}" style="margin-top: -20px">
            <p style="color: red; margin-left: 20px;">
                <span>${locale_storage_only}  ${storage.count} ${locale_storage_dessert}</span>
            </p>
        </c:if>
        <c:if test="${storage.count==0}">
            <p style="color: red; text-align: center">${locale_dessert_unavailable}</p>
        </c:if>

        <button type="submit" style="margin-left: 108px;" class="btn btn-default">${locale_basket_add}</button>

        <c:if test="${addDessertSuccess}">
            <div class="form-group">
                <p id='app' style="margin-top: -15px;margin-left: 115px; text-align: center; opacity: 1;height: 30px; width: 200px; transition: opacity 500ms;  color:#14AC2E; border-style: solid;border-radius: 15px">${locale_add_basket}</p>
            </div>

        </c:if>
        <c:if test="${notEnoughStorage}">
            <div class="form-group">
                <p style="color: red; text-align: center">${locale_problem_basket}</p>
            </div>
        </c:if>
        <div class="form-footer" style="padding-top: 1px"></div>
    </form>
</div>
</body>
</html>
<script>
    function initialSetup() {
        if (document.getElementById('app') != null) {
            setTimeout(function() {
                document.getElementById('app').style.opacity = '0';
            }, 2000);
        }
    }

    initialSetup();


</script>

