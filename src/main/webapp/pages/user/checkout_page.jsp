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

<!doctype html>
<html>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="container">
    <div class="row" style="margin-top: 10px">




        <form class="form-horizontal" action="controller" method="post">
            <input type="hidden" name="command" value="place_order_command"/>
            <fieldset>
                <button type="submit" class="btn btn-warning btn-block">${locale_order_place}</button>
                <legend class="center-block"${locale_order_confirm}</legend>
                <div class="row">
                    <div class="col-xs-7 text-left">
                        <h3 style="color: darkred"><strong>${locale_order_sum}</strong></h3>
                    </div>
                    <div class="col-xs-5 text-right">
                        <h3><strong style="color: darkred"><span>${basket.totalCost}BYN</span></strong></h3>
                    </div>
                </div>

<%--                <c:if test="${missingRequiredField}">--%>
<%--                    <div>--%>
<%--                        <h5 class="alert alert-warning">There are some fields misssing</h5>--%>
<%--                    </div>--%>
<%--                </c:if>--%>

                <%--address--%>
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordeon" href="#addressInfo">${locale_order_address}</a>
                    </h4>

                            <div class="form-group">
                                <label for="address">${locale_address_detail}</label>
                                <input required type="text" class="form-control" id="address" name="address"
                                       value="${user.address}"/>
                            </div>

                            <div class="form-group">
                                <div class="row">
                                    <div class="col-xs-6">
                                        <label for="date">${locale_order_delivery}</label>
                                        <input required type="date" class="form-control" id="date" name="date"
                                               max="2022-10-15" min="2021-06-20"/>
                                    </div>

                                    <div class="col-xs-6">
                                        <label for="time">${locale_order_time}</label>
                                        <select required id="time" name="time" class="form-control">
                                            <option value="" selected="selected"
                                                    disabled="disabled">${locale_time_select}</option>
                                            <option value="9:00 - 12:00">9:00 - 12:00</option>
                                            <option value="15:00 - 18:00">15:00 - 18:00</option>
                                            <option value="18:00 - 21:00">18:00 - 21:00</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <a data-toggle="collapse" data-parent="#accordeon" class="btn btn-warning pull-right"
                               href="#paymentInfo">${locale_common_next}</a>
                </div>
                <!--2. Payment information-->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordeon" href="#paymentInfo">${locale_order_payment}</a>
                        </h4>
                    </div>
                        <div>
                            <p><b>${locale_payment_cash}</b></p>
                            <p><input name="cash" type="radio" value="true" checked/>${locale_common_yes}</p>
                            <p><input name="cash" type="radio" value="false"/>${locale_common_no}</p>
                        </div>
                        <a data-toggle="collapse" data-parent="#accordeon"
                           class="btn btn-warning pull-right" href="#reviewItems">${locale_common_next}</a>
                    </div>
                </div>

    <!--3. Review Items and Address-->
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordeon" href="#reviewItems">${locale_review_item}</a>
            </h4>
        </div>



            <div class="panel-body">
                <div class="row">
                    <div class="col-xs-8"><h4>${locale_item_name}</h4></div>
                    <div class="col-xs-2"><h4>${locale_item_price}</h4></div>
                    <div class="col-xs-2"><h4>${locale_order_amount}</h4></div>
                </div>
                <!--Display items in basket-->
                <c:forEach items="${basketDessertList}" var="basketDessert">
                    <div class="row">
                        <hr/>
                        <div class="col-xs-2">
                            <img style="width: 70px;" class="img-responsive" src="static/images/${basketDessert.dessert.dessertImage}"/>
                        </div>
                        <div class="col-xs-6">
                            <div class="center-block">
                                <p><h4>${basketDessert.dessert.name}</h4></p>
                                <c:if test="${basketDessert.dessert.storage.count >= 11}">
                                    <h4 style="color: green">${locale_storage_dessert}</h4>
                                </c:if>
                                <c:if test="${basketDessert.dessert.storage.count < 11 && basketDessert.dessert.storage.count > 0}">
                                    <h4 style="color: green">
                                        <span>${locale_storage_only}  ${basketDessert.dessert.storage.count} ${locale_storage_dessert}</span>
                                    </h4>
                                </c:if>
                                <c:if test="${basketDessert.dessert.storage.count == 0}">
                                    <h4 style="color: darkred">${locale_dessert_unavailable}</h4>
                                </c:if>
                            </div>
                        </div>
                        <div class="col-xs-2">
                            <h5 style="color: orangered; font-size: large">Br<span style="<c:if
                                    test="${basketDessert.dessert.storage.count} == 0">text-decoration: line-through</c:if>">${basketDessert.dessert.price}</span>
                            </h5>
                        </div>
                        <div class="col-xs-2">
                            <h5 style="font-size: large">${basketDessert.count}</h5>
                        </div>
                    </div>
                </c:forEach>
                <hr/>
                <h4 class="col-xs-12 text-right"><strong
                        style="font-size: large">${locale_total_price}</strong>
                    <span style="color: orangered; font-size: large">Br<span>${basket.totalCost}</span></span>
                </h4>
                <br/><br/>
                <button type="submit" class="btn btn-warning btn-block">${locale_order_place}</button>
                <p style="font-size: smaller">${locale_order_confirm}</p>
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








