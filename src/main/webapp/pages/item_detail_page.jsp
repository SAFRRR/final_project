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
<fmt:message key="add.unable" var="locale_add_unable"/>
<fmt:message key="add.already" var="locale_add_already"/>
<fmt:message key="not.enough" var="not_enough_value"/>
<fmt:message key="update.quantity" var="invalid_quantity"/>


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

    <form class="form-horizontal needs-validation" action="controller" method="post" style="width: 500px; margin-top: 10px; margin-left: 400px;margin-right: 20px; " novalidate >
        <input type="hidden" name="command" value="add_dessert_to_basket_command">
        <input hidden="hidden" name="dessertId" value="${dessert.id}"/>
        <input hidden="hidden" name="dessertPrice" value="${dessert.price}"/>
        <input hidden="hidden" name="dessertWeight" value="${dessert.weight}"/>
        <input hidden="hidden" name="storageAmount" value="${storage.count}"/>

        <img style="height: 313px; margin-left:50px;" class="img-responsive page-dessert" src="./static/images/${dessert.dessertImage}"/>

        <h4 style="text-align: center; margin-top: 15px">${dessert.name}</h4>

        <p style="width: 413px; text-align: center;">${fn:substring(dessert.description, 0, 300)}</p>

        <p style="text-align: center;"><span > ${dessert.price} BYN (</span><span>${dessert.weight} g )</span></p>
        <c:if test="${user.role != 'ADMIN'}">
            <c:if test="${storage.count > 0}">
                <input required
                       type="number"
                       step="1"
                   class="form-control" name="amount" min="1"
                   max="${storage.count}"
                       pattern="/d+"
<%--                   pattern="${attribute_regexp_count}"--%>
                   placeholder="${locale_order_amount}" style="margin-top: -20px">
                <div class="invalid-feedback">
                        ${invalid_quantity}
                </div>
                <p style="color: red; margin-left: 20px;">
                    <span>${locale_storage_only}  ${storage.count} ${locale_storage_dessert}</span>
                </p>
                <button type="submit" style="margin-left: 108px;" class="btn btn-default">${locale_basket_add}</button>
            </c:if>
        </c:if>
        <c:if test="${storage.count==0}">
            <p style="color: red; text-align: center">${locale_dessert_unavailable}</p>
        </c:if>

        <c:if test="${addDessertSuccess}">
            <div class="form-group">
                <p id='app' style="margin-top: -15px;margin-left: 115px; text-align: center; opacity: 1;height: 30px; width: 200px; transition: opacity 500ms;  color:#14AC2E; ">${locale_add_basket}</p>
            </div>
        </c:if>
        <c:if test="${addDessertFailed}">
            <div class="form-group">
                <p id="fail" style="color: red; margin-top: -20px;margin-left: -8px; text-align: center; opacity: 1;height: 30px; transition: opacity 500ms;">
                    ${locale_add_unable} ${addedCount} ${dessert.name} ${locale_add_already} ${basketCount}</p>
            </div>
        </c:if>

        <c:if test="${notEnoughStorage}">
            <div class="form-group">
                <p id="notEnough" style="color: red; margin-top: -20px;margin-left: -8px; text-align: center; opacity: 1;height: 30px; transition: opacity 500ms;">
                      ${not_enough_value}</p>
            </div>
        </c:if>
        <div class="form-footer" style="padding-top: 1px"></div>
    </form>
</div>
</body>
</html>

<script>
    (function () {
        'use strict'
        var forms = document.querySelectorAll('.needs-validation')
        Array.prototype.slice.call(forms)
            .forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault()
                        event.stopPropagation()
                    }
                    form.classList.add('was-validated')
                }, false)
            })
    })()
    function initialSetup() {
        if (document.getElementById('app') != null) {
            setTimeout(function() {
                document.getElementById('app').style.opacity = '0';
            }, 2000);
        }
        if (document.getElementById('fail') != null) {
            setTimeout(function() {
                document.getElementById('fail').style.opacity = '0';
            }, 3000);
        }
        if (document.getElementById('notEnough') != null) {
            setTimeout(function() {
                document.getElementById('notEnough').style.opacity = '0';
            }, 3000);
        }
    }
    initialSetup();
</script>
