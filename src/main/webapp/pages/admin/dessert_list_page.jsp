<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="main.category" var="locale_main_category"/>
<fmt:message key="admin.dessert.weight" var="locale_dessert_weight"/>
<fmt:message key="main.name" var="locale_main_name"/>
<fmt:message key="main.description" var="locale_main_description"/>
<fmt:message key="admin.price.name" var="locale_price_name"/>
<fmt:message key="admin.storage.name" var="locale_storage_name"/>
<fmt:message key="admin.choose.operation" var="locale_choose_operation"/>
<fmt:message key="admin.operation.delete" var="locale_operation_delete"/>
<fmt:message key="common.edit" var="locale_common_edit"/>

<!doctype html>
<html lang="en">
<body>
<jsp:include page="../common/header.jsp"/>
<div class="container">
    <br/>
    <div class="table-responsive" >
        <table class="table table-bordered table-hover">
            <style>tr:hover {background: #ffe1be;}</style>
            <thead>
            <tr>
                <th style="text-align: center; color: #e1791a;">${locale_main_name}</th>
                <th style="text-align: center; color: #e1791a;">${locale_main_category}</th>
                <th style="text-align: center; color: #e1791a;">${locale_dessert_weight}</th>
                <th style="text-align: center; color: #e1791a;">${locale_main_description}</th>
                <th style="text-align: center; color: #e1791a;">${locale_price_name}</th>
                <th style="text-align: center; color: #e1791a;">${locale_storage_name}</th>
                <th style="text-align: center; color: #e1791a;">${locale_choose_operation}</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${dessertList}" var="dessert">
                <tr>
                    <td style="text-align: center">${dessert.name}</td>

                    <td style="text-align: center">${dessert.dessertType.description}</td>

                    <td style="text-align: center">${dessert.weight}</td>

                    <td style="width: 450px">${dessert.description}</td>

                    <td style="text-align: center">${dessert.price}</td>

                    <td style="text-align: center">${dessert.storage.count}</td>

                    <td>
                        <a  style="color:  #e1791a; text-decoration: underline; margin-left: 12px;" href="${pageContext.request.contextPath}/controller?command=go_to_update_dessert_page_command&dessertId=${dessert.id}">${locale_common_edit}</a>

                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="delete_dessert_command">
                            <input type="hidden" name="dessertId" value="${dessert.id}">
                            <button style="color:  #e1791a; text-decoration: underline; " type="submit" class="btn btn-default">${locale_operation_delete}</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
