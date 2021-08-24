<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="admin.storage.dessert" var="locale_storage_dessert"/>
<fmt:message key="admin.update.item" var="locale_update_item"/>
<fmt:message key="main.edit.dessert" var="locale_edit_dessert"/>
<fmt:message key="admin.dessert.name" var="locale_dessert_name"/>
<fmt:message key="main.name" var="locale_main_name"/>
<fmt:message key="main.description" var="locale_main_description"/>
<fmt:message key="main.category.enter" var="locale_category_enter"/>
<fmt:message key="admin.dessert.weight" var="locale_dessert_weight"/>
<fmt:message key="admin.price.name" var="locale_price_name"/>
<fmt:message key="main.add.item" var="locale_add_item"/>
<fmt:message key="update.name" var="update_name_value"/>
<fmt:message key="update.description" var="update_description_value"/>
<fmt:message key="update.price" var="update_price_value"/>
<fmt:message key="update.weight" var="update_weight_value"/>
<fmt:message key="update.quantity" var="update_quantity_value"/>
<fmt:message key="update.unable" var="update_unable_value"/>


<!doctype html>
<html>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="container">
    <div class="row">

    <div class="back" style="float: left">
        <a style="color: #e1791a;" href="${pageContext.request.contextPath}/controller?command=go_to_dessert_list_page_command">
            <i class="fas fa-chevron-left"></i>
        </a>
    </div>
        <form class="form-horizontal needs-validation" style="margin-top: 10px; margin-left: 220px"  action="controller" method="post" enctype="multipart/form-data" novalidate>
            <fieldset>
                <input type="hidden" name="command" value="update_dessert_command"/>
                <legend class="center-block" style="color: #e1791a;margin-left: 29px">${locale_edit_dessert}</legend>
                <input type="hidden" name="dessertId" value="${dessert.id}"/>

                <div class="form-group">
                        <input required type="text" class="form-control" id="name" max="45"
                               name="name" value="${dessert.name}"
                               placeholder="${locale_main_name}" pattern="${attribute_regexp_name}"/>
                    <div class="invalid-feedback">
                        ${update_name_value}
                    </div>
                </div>

                <div class="form-group">
                    <input class="form-control"
                           type="text"
                           id="description" name="description"
                           max="300" min="5"
                           pattern="${attribute_regexp_description}"
                           placeholder="${locale_main_description}"
                           value="${dessert.description}" required>
                    <div class="invalid-feedback">
                        ${update_description_value}
                    </div>
                </div>

                <div class="form-group">
                        <select required title="${dessert.dessertType}" id="category" name="category" class="form-control">
                            <option value="" selected="selected" disabled="disabled">${locale_category_enter}</option>
                            <c:forEach var="dessertType" items="${dessertTypeList}">
                                <option value="${dessertType.id}"
                                        <c:if test="${dessert.dessertType.id == dessertType.id}">selected</c:if>>
                                        ${dessertType.description}</option>
                            </c:forEach>
                        </select>
                    <div class="invalid-feedback">
                    </div>
                </div>

                <div class="form-group">
                        <input required value="${dessert.weight}" type="text" min="0"
                               class="form-control" id="weight" name="weight"
                               placeholder="${locale_dessert_weight}" pattern="${attribute_regexp_weight}"/>
                    <div class="invalid-feedback">
                        ${update_weight_value}
                    </div>
                </div>

                <div class="form-group">
                            <input required value="${dessert.price}" type="text" step="0.1" min="1"
                                   class="form-control" id="price" name="price"
                                   placeholder="${locale_price_name}" pattern="${attribute_regexp_price}"/>
                    <div class="invalid-feedback">
                        ${update_price_value}
                    </div>
                </div>

                <div class="form-group">
                       <input required value="${dessert.storage.count}" type="text" min="1" max="100"
                               class="form-control" id="count" name="count"
                               placeholder="${locale_storage_dessert}" pattern="${attribute_regexp_count}"/>
                    <div class="invalid-feedback">
                        ${update_quantity_value}
                    </div>
                </div>

                <c:if test="${notEnoughStorage}">
                    <div class="form-group">
                        <p id="fail" style="color: red;  text-align: center; opacity: 1;height: 30px; transition: opacity 500ms;">
                                ${update_unable_value}</p>
                    </div>
                </c:if>

                <div class="form-group" style="margin-left: 150px">
                        <button type="submit" class="btn btn-success">${locale_update_item}</button>
                </div>
            </fieldset>
        </form>
    </div>
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
</script>
