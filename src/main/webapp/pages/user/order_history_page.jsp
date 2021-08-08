<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>


<fmt:message key="user.order.date" var="locale_order_date"/>
<fmt:message key="user.order.number" var="locale_order_number"/>
<fmt:message key="admin.order.total" var="locale_order_total"/>
<fmt:message key="common.status" var="locale_common_status"/>
<fmt:message key="admin.order.approved" var="locale_order_approved"/>
<fmt:message key="admin.order.inprocess" var="locale_order_inprocess"/>
<fmt:message key="admin.order.rejected" var="locale_order_rejected"/>

<!DOCTYPE html>
<!doctype html>
<html lang="en">
<body>
<jsp:include page="../common/header.jsp"/>

<div class="container">
    <div class="panel-group">
        <div class="panel panel-default">
            <div class="panel-body" style="background-color: lightgray; margin-top: 20px;">
                <table class="table table-sm table-inverse">
                    <thead>
                    <tr>
                        <th>${locale_order_date}</th>
                        <th>${locale_order_number}</th>
                        <th>${locale_order_total}</th>
                        <th>${locale_common_status}</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="order" items="${orderList}">
                        <tr>
                            <td>${order.dateOrder}</td>
                            <td>${order.id}</td>
                            <td>${order.totalCost}</td>
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
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>


<!--Order Info-->



