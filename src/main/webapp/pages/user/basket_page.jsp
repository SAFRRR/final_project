<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="user.continue.shopping" var="locale_continue_shopping"/>
<fmt:message key="user.order.check" var="locale_order_check"/>
<fmt:message key="main.dessert.list" var="locale_dessert_list"/>
<fmt:message key="admin.price.name" var="locale_admin_price_name"/>
<fmt:message key="user.order.count" var="locale_order_count"/>
<fmt:message key="user.storage.dessert" var="locale_storage_dessert"/>
<fmt:message key="user.storage.only" var="locale_storage_only"/>
<fmt:message key="user.dessert.unavailable" var="locale_dessert_unavailable"/>
<fmt:message key="admin.operation.delete" var="locale_operation_delete"/>
<fmt:message key="common.update" var="locale_common_update"/>
<fmt:message key="user.total.price" var="locale_total_price"/>
<fmt:message key="update.quantity" var="invalid_quantity"/>

<!doctype html>
<html>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="container">
    <div class="container2" style=" display: flex; flex-direction: row; flex-wrap: wrap;  align-items: center; justify-content: center;">
    <c:forEach items="${basketDessertList}" var="basketDessert">
        <div class="my">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="remove_dessert_command"/>
            <input type="hidden" name="basketDessertId" value="${basketDessert.id}"/>
            <button type="submit" style="margin-left: 243px"  class="btn btn-outline-danger"><i class="fas fa-times"></i></button>
        </form>
        <form class="form-horizontal needs-validation" style="width: 280px; height:580px; margin-top: 5px; margin-bottom: 10px; margin-left: 0;margin-right: 20px; " novalidate>
           <img style="width: 200px" class="img-responsive dessert" src="./static/images/${basketDessert.dessert.dessertImage}">
            <h4 style="text-align: center; margin-top: 15px">${basketDessert.dessert.name}</h4>
            <p style="text-align: center;margin-top: 10px">${basketDessert.dessert.price} BYN</p>
            <c:if test="${basketDessert.dessert.storage.count > 0}">
            <p style="color: red; text-align: center">
                <span>${locale_storage_only}  ${basketDessert.dessert.storage.count} ${locale_storage_dessert}</span>
            </p>
            </c:if>
            <input id="count" style="width: 100px; margin-left: 60px;margin-top: 0" name="count" type="number"
                   step="1"  pattern="/d+" min="1" max="${basketDessert.dessert.storage.count}"
                <c:if test="${basketDessert.dessert.storage.count==0}">disabled</c:if>
                   class="form-control basketDessertCount" value="${basketDessert.count}"/>
            <div class="invalid-feedback" style="margin-right: 5px">
                    ${invalid_quantity}
            </div>
            <c:if test="${notEnoughStorage}">
                <div class="form-group">
                    <p id="fail" style="color: red; margin-top: -20px;margin-left: -8px; text-align: center; opacity: 1;height: 30px; transition: opacity 500ms;">
                            ${locale_add_unable}</p>
                </div>
            </c:if>

            <form action="controller" method="post">
                <input type="hidden" name="command" value="update_basket_command"/>
                <input hidden="hidden" name="id" value="${basketDessert.id}"/>
                <input hidden="hidden" name="storageAmount" value="${basketDessert.dessert.quantity}"/>
                <button style="margin-left: 40px; width: 120px" type="submit" class="btn btn-warning btn-xs">${locale_common_update}</button>
            </form>
        </form>
        </div>
    </c:forEach>
    </div>
    <form class="form-horizontal" style="width: 450px; height:80px; margin-top: 5px; margin-left: 18px;margin-right: 20px;">
            <h4 style="float: left; margin-top: -15px"><strong style="font-size: large">${locale_total_price}</strong>
                <span style="color: orangered; font-size: large"><span>${basket.totalCost}</span>&nbsp;BYN&nbsp;&nbsp;</span></h4>
            <c:if test="${!emptyBasket}">
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="go_to_check_out_page_command">
                    <input type="hidden" name="id" value="${basket.id}">
                    <button style="margin-top: -20px; width: 150px; height: 40px; margin-left: 20px; text-align: center;" class="btn btn-default" type="submit">${locale_order_check}</button>
                </form>
            </c:if>
    </form>
</div>
</body>
</html>


<script>
    {
        const
            intRx = /\d/,
            integerChange = (event) => {
                if (
                    (event.key.length > 1) ||
                    ( (event.key === "-") && (!event.currentTarget.value.length) ) ||
                    intRx.test(event.key)
                ) return;
                event.preventDefault();
            };

        for (let input of document.querySelectorAll(
            'input[type="number"][step="1"]'
        )) input.addEventListener("keydown", integerChange);

    }

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
        if (document.getElementById('fail') != null) {
            setTimeout(function() {
                document.getElementById('fail').style.opacity = '0';
            }, 3000);
        }
    }
    initialSetup();

</script>
