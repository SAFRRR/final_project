<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="admin.order.sum" var="locale_order_sum"/>
<fmt:message key="admin.item.name" var="locale_item_name"/>
<fmt:message key="admin.dessert.price" var="locale_dessert_price"/>
<fmt:message key="admin.item.quantity" var="locale_item_quantity"/>
<fmt:message key="admin.order.subtotal" var="locale_order_subtotal"/>
<fmt:message key="common.status" var="locale_common_status"/>
<fmt:message key="admin.order.status" var="locale_order_status"/>
<fmt:message key="admin.order.approved" var="locale_order_approved"/>
<fmt:message key="admin.order.inprocess" var="locale_order_inprocess"/>
<fmt:message key="admin.order.rejected" var="locale_order_rejected"/>
<fmt:message key="admin.order.total" var="locale_order_total"/>
<fmt:message key="main.save" var="locale_main_save"/>

<!doctype html>
<html lang="en">
<body>
<jsp:include page="../common/header.jsp"/>
<div class="table-responsive">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="change_order_status_command"/>
        <input type="hidden" name="orderId" value="${order.id}"/>
        <div class="container">
            <h3><strong>${locale_order_sum}</strong></h3>
            <div class="table-responsive">
                <table class="table table-bordered table-hover table-striped">
                    <thead>
                    <tr>
                        <td><strong>${locale_item_name}</strong></td>
                        <td class="text-center"><strong>${locale_dessert_price}</strong></td>
                        <td class="text-center"><strong>${locale_item_quantity}</strong></td>
                        <td class="text-right"><strong>${locale_order_subtotal}</strong></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${orderDessertList}" var="orderDessert">
                        <tr>
                            <td><span>${orderDessert.dessert.name}</span></td>
                            <td class="text-center">${orderDessert.dessert.price}</td>
                            <td class="text-center">${orderDessert.count}</td>
                            <td class="text-right">${orderDessert.subTotal}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td>
                            <label class="col-md-5 control-label">${locale_order_status}</label>
                            <select required name="statusOrder" class="form-control">
                                <option disabled="disabled">${locale_order_status}</option>
                                <option value="APPROVED"
                                        <c:if test="${order.statusOrder == 'APPROVED'}">selected</c:if>>
                                    ${locale_order_approved}</option>
                                <option value="INPROCESS"
                                        <c:if test="${order.statusOrder == 'INPROCESS'}">selected</c:if>>
                                    ${locale_order_inprocess}</option>
                                <option value="REJECTED"
                                        <c:if test="${order.statusOrder == 'REJECTED'}">selected</c:if>>
                                    ${locale_order_rejected}</option>
                            </select>
                        </td>
                        <td class="highrow"></td>
                        <td class="highrow text-center"><strong>${locale_order_total}</strong></td>
                        <td class="highrow text-right">${order.totalCost}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <br/>
            <button class="btn btn-success" type="submit">${locale_main_save}</button>
        </div>
    </form>
</div>
</body>
</html>
