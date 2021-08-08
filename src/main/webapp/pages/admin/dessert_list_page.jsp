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
<link rel="stylesheet" href="../../static/css/dessert.css">
<body>
<jsp:include page="../common/header.jsp"/>
<div class="container">
    <br/>
    <!--Table Header-->
    <div class="table-responsive" >
        <table class="table table-bordered table-hover table-striped">
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
            <!--Table body-->
            <tbody>
            <c:forEach items="${dessertList}" var="dessert">
                <tr>
                    <td>${dessert.name}</td>

                    <td>${dessert.dessertType.description}</td>

                    <td>${dessert.weight}</td>

                    <td>${dessert.description}</td>

                    <td>${dessert.price}</td>

                    <td>${dessert.storage.count}</td>

                    <td>
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="go_to_update_dessert_page_command"/>
                            <input type="hidden" name="dessertId" value="${dessert.id}"/>
                            <button class="btn btn-default" type="submit">${locale_common_edit}</button>
                        </form>

                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="delete_dessert_command"/>
                            <input type="hidden" name="dessertId" value="${dessert.id}"/>
                            <button type="submit" class="btn btn-default">${locale_operation_delete}</button>
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
