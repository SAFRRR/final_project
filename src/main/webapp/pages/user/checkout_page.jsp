<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="user.account" var="locale_user_account"/>
<fmt:message key="user.order.place" var="locale_order_place"/>
<fmt:message key="user.order.confirm" var="locale_order_confirm"/>
<fmt:message key="admin.order.sum" var="locale_order_sum"/>
<fmt:message key="user.order.address" var="locale_order_address"/>
<fmt:message key="common.address.detail" var="locale_address_detail"/>
<fmt:message key="user.order.delivery" var="locale_order_delivery"/>
<fmt:message key="user.order.time" var="locale_order_time"/>
<fmt:message key="user.time.select" var="locale_time_select"/>
<fmt:message key="common.next" var="locale_common_next"/>
<fmt:message key="user.order.payment" var="locale_order_payment"/>
<fmt:message key="user.payment.cash" var="locale_payment_cash"/>
<fmt:message key="common.yes" var="locale_common_yes"/>
<fmt:message key="common.no" var="locale_common_no"/>
<fmt:message key="user.review.item" var="locale_review_item"/>
<fmt:message key="admin.item.name" var="locale_item_name"/>
<fmt:message key="admin.price.name" var="locale_item_price"/>
<fmt:message key="user.storage.dessert" var="locale_storage_dessert"/>
<fmt:message key="user.storage.only" var="locale_storage_only"/>
<fmt:message key="user.dessert.unavailable" var="locale_dessert_unavailable"/>
<fmt:message key="user.order.amount" var="locale_order_amount"/>
<fmt:message key="user.total.price" var="locale_total_price"/>
<fmt:message key="admin.operation.delete" var="locale_operation_delete"/>
<fmt:message key="order.info" var="order_info_value"/>
<fmt:message key="signup.address" var="signup_address_value"/>
<fmt:message key="admin.order.cash" var="cash_value"/>
<fmt:message key="admin.order.card" var="card_value"/>



<!doctype html>
<html>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="container">
    <form class="form-horizontal" style="margin-left: 400px;margin-top: 40px; width: 500px" action="controller" method="post">
        <input type="hidden" name="command" value="place_order_command"/>
        <div class="heading">${order_info_value}</div>
        <fieldset>
            <div class="panel-heading">
                <div class="form-group">
                    <i class="fas fa-map-marker-alt"></i>
                    <input required type="text" class="form-control" id="address" name="address" placeholder="${signup_address_value}"
                           value="${user.address}"/>
                </div>
                <div class="form-group">
                            <i class="fas fa-calendar-day"></i>
                            <input required type="date" class="form-control" id="date" name="date" value=""
                                   max="2022-08-14" min="2021-08-14"/>
                </div>
                <div class="form-group">
                            <i class="far fa-clock"></i>
                            <select required id="time" name="time" class="form-control">
                                <option value="" selected="selected"
                                        disabled="disabled">${locale_time_select}</option>
                                <option value="9:00 - 12:00">9:00 - 12:00</option>
                                <option value="15:00 - 18:00">15:00 - 18:00</option>
                                <option value="18:00 - 21:00">18:00 - 21:00</option>
                            </select>
                </div>
                <div class="form-group">
                    <p style="color: #e1791a;"><b>${locale_payment_cash}</b></p>
                    <p><input name="cash" type="radio" value="true" checked/>&nbsp ${cash_value}</p>
                    <p><input name="cash" type="radio" value="false"/>&nbsp; ${card_value}</p>
                </div>

                <div class="form-group">
                    <hr/>
                    <h4 class="col-xs-12 text-right"><strong style="font-size: large; color: #e1791a;">${locale_total_price}</strong>
                        <span style="color: #464D49; font-size: large"><span>${basket.totalCost}BYN</span></span>
                    </h4>
                    <br/><br/>
                    <button style="margin-top: -20px; margin-left: 100px" type="submit" class="btn btn-warning btn-block">${locale_order_place}</button>
                </div>
            </div>
        </fieldset>
    </form>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        var d = new Date();
        var day = d.getDate();
        var month = d.getMonth() + 1;
        var year = d.getFullYear();
        var name_input = document.getElementById('date')
        name_input.value = day + "-" + month + "-" + year;
    });
</script>
</body>
</html>








