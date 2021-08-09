<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


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
    <form action="controller" method="post">
        <input type="hidden" name="command" value="add_dessert_to_basket_command">
        <input hidden="hidden" name="dessertId" value="${dessert.id}"/>
        <input hidden="hidden" name="dessertPrice" value="${dessert.price}"/>
        <input hidden="hidden" name="storageAmount" value="${storage.count}"/>

        <div class="row">
            <div class="col-xs-3">
                <a href="${pageContext.request.contextPath}/controller?command=go_to_item_page_command" >${locale_continue_shopping}</a><br/>
                <img style="height: 313px;" class="img-responsive page-dessert" src="./static/images/${dessert.dessertImage}"/>
            </div>

            <div class="col-xs-9">
                <c:if test="${addDessertSuccess}">
                    <div class="alert alert-success">An item has been added to your shopping basket</div>
                </c:if>
                <c:if test="${notEnoughStorage}">
                    <div class="alert alert-warning">Oooops, some of the products don't have enough count in storage.
                        Please update product quantity
                    </div>
                </c:if>



                <p>${dessert.name}</p>
                <p><strong>${locale_dessert_weight} </strong><span>${dessert.weight}gm</span></p>
                <p>${dessert.description}</p>

                <div class="row">
                    <div class="col-xs-7">
                        <div class="panel panel-default" style="border-width: 5px; margin-top: 20px;">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-xs-6">
                                        <h4>${locale_price_name} <span style="color: darkorange;">Br<span>${dessert.price}</span></span></h4>

                                        <span>${locale_order_amount}</span>
                                        <input required type="number" step="1"
                                               class="form-control" name="amount" min="1"
                                               placeholder="${locale_order_amount}"/>
                                    </div>

                                    <div class="col-xs-6">
                                        <c:if test="${storage.count >= 11}">
                                            <h4 style="color: green">${locale_storage_dessert}</h4>
                                        </c:if>
                                        <c:if test="${storage.count < 11 && storage.count > 0}">
                                            <h4 style="color: green">
                                                <span>${locale_storage_only}  ${storage.count} ${locale_storage_dessert}</span>
                                            </h4>
                                        </c:if>
                                        <c:if test="${storage.count==0}">
                                            <h4 style="color: darkred">${locale_dessert_unavailable}</h4>
                                        </c:if>
                                        <button type="submit" class="btn btn-outline-light"
                                                style="color: black; border: 1px solid black; padding: 10px 40px 10px 40px;">${locale_basket_add}</button>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </form>
</div>
</body>
</html>

