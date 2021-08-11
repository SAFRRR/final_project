<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="common.all.items" var="locale_common_all_items"/>
<fmt:message key="user.continue.shopping" var="locale_continue_shopping"/>
<fmt:message key="main.category" var="locale_main_category"/>
<fmt:message key="main.soil" var="locale_main_soil"/>
<fmt:message key="admin.dessert.weight" var="locale_dessert_weight"/>
<fmt:message key="admin.price.name" var="locale_price_name"/>
<fmt:message key="user.order.amount" var="locale_order_amount"/>
<fmt:message key="user.storage.dessert" var="locale_storage_dessert"/>
<fmt:message key="user.storage.only" var="locale_storage_only"/>
<fmt:message key="user.dessert.unavailable" var="locale_dessert_unavailable"/>
<fmt:message key="user.basket.add" var="locale_basket_add"/>

<!doctype html>
<html lang="en">
<body>
<jsp:include page="common/header.jsp"/>

<div class="container">

    <form class="form-horizontal" action="controller" method="post" style="width: 500px; margin-top: 10px; margin-left: 400px;margin-right: 20px; " >
        <input type="hidden" name="command" value="add_dessert_to_basket_command">
        <input hidden="hidden" name="dessertId" value="${dessert.id}"/>
        <input hidden="hidden" name="dessertPrice" value="${dessert.price}"/>
        <input hidden="hidden" name="storageAmount" value="${storage.count}"/>


        <img style="height: 313px; margin-left:50px;" class="img-responsive page-dessert" src="./static/images/${dessert.dessertImage}"/>

        <c:if test="${addDessertSuccess}">
            <div class="alert alert-success">An item has been added to your shopping basket</div>
        </c:if>
        <c:if test="${notEnoughStorage}">
            <div class="alert alert-warning">Oooops, some of the products don't have enough count in storage.
                Please update product quantity
            </div>
        </c:if>


        <h4 style="text-align: center; margin-top: 15px">${dessert.name}</h4>

        <p style="width: 413px; text-align: center;">${fn:substring(dessert.description, 0, 500)}</p>

        <p style="text-align: center;"><span > ${dessert.price} BYN (</span><span>${dessert.weight} g )</span></p>


        <input required type="number" step="1"
                   class="form-control" name="amount" min="1"
                   max="${storage.count}"
                   placeholder="${locale_order_amount}"/>


        <c:if test="${storage.count >= 11}">
            <h4 style="color: green">${locale_storage_dessert}</h4>
        </c:if>
        <c:if test="${storage.count < 11 && storage.count > 0}">
            <p style="color: red">
                <span>${locale_storage_only}  ${storage.count} ${locale_storage_dessert}</span>
            </p>
        </c:if>
        <c:if test="${storage.count==0}">
            <h4 style="color: darkred">${locale_dessert_unavailable}</h4>
        </c:if>
        <button type="submit"  class="btn btn-default">${locale_basket_add}</button>

        <div class="form-footer" style="padding-top: 1px"></div>
    </form>

</div>
</body>
</html>
