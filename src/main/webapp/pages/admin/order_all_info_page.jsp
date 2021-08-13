<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="common.address.detail" var="locale_order_address"/>
<fmt:message key="common.order.payment" var="locale_order_payment"/>
<fmt:message key="common.order.delivery" var="locale_order_delivery"/>
<fmt:message key="common.order.date" var="locale_order_date"/>
<fmt:message key="common.status" var="locale_order_status"/>
<fmt:message key="common.detail" var="locale_order_detail"/>
<fmt:message key="admin.order.cash" var="locale_order_cash"/>
<fmt:message key="admin.order.card" var="locale_order_card"/>
<fmt:message key="common.detail" var="locale_common_detail"/>
<fmt:message key="admin.order.approved" var="locale_order_approved"/>
<fmt:message key="admin.order.inprocess" var="locale_order_inprocess"/>
<fmt:message key="admin.order.rejected" var="locale_order_rejected"/>

<!doctype html>
<html lang="en">
<link rel="stylesheet">
<body>
<jsp:include page="../common/header.jsp"/>
<div class="container">
    <br/>
    <div class="table-responsive">
            <table style="text-align: center;" class="table table-bordered table-hover">
                <style>
                    tr:hover {
                        background: #ffe1be;
                    }
                </style>
            <thead>
            <tr>
                <th style="text-align: center; color: #e1791a;">${locale_order_address}</th>
                <th style="text-align: center; color: #e1791a;">${locale_order_payment}</th>
                <th style="text-align: center; color: #e1791a;">${locale_order_delivery}</th>
                <th style="text-align: center; color: #e1791a;">${locale_order_date}</th>
                <th style="text-align: center; color: #e1791a;">${locale_order_status}</th>
                <th style="text-align: center; color: #e1791a;">${locale_order_detail}</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orderList}">
                <tr>
                    <td>${order.address}</td>
                    <td>
                        <c:if test="${order.cash}">
                            <span>${locale_order_cash}</span>
                        </c:if>
                        <c:if test="${!order.cash}">
                            <span>${locale_order_card}</span>
                        </c:if>
                    </td>
                    <td>${order.dateDelivery}</td>
                    <td>${order.dateOrder}</td>
                    <c:if test="${order.statusOrder == 'APPROVED'}">
                        <td>
                                ${locale_order_approved}
                        </td>
                    </c:if>
                    <c:if test="${order.statusOrder == 'INPROCESS'}">
                        <td>
                                ${locale_order_inprocess}
                        </td>
                    </c:if>
                    <c:if test="${order.statusOrder == 'REJECTED'}">
                        <td>
                                ${locale_order_rejected}
                        </td>
                    </c:if>
                    <td>
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="go_to_order_detail_page_command"/>
                            <input type="hidden" name="orderId" value="${order.id}"/>
                            <button type="submit" style="color:  #e1791a;" class="btn btn-link">${locale_common_detail}</button>
                        </form>
                        <br/>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
