<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="main.add.dessert" var="locale_add_dessert"/>
<fmt:message key="main.name" var="locale_main_name"/>
<fmt:message key="main.description" var="locale_main_description"/>
<fmt:message key="main.category.enter" var="locale_category_enter"/>
<fmt:message key="admin.dessert.weight" var="locale_dessert_weight"/>
<fmt:message key="admin.price.name" var="locale_price_name"/>
<fmt:message key="admin.storage.name" var="locale_storage_name"/>
<fmt:message key="admin.dessert.image" var="locale_dessert_image"/>
<fmt:message key="main.add.item" var="locale_add_item"/>
<fmt:message key="main.exist" var="locale_main_exist"/>
<fmt:message key="update.name" var="update_name_value"/>
<fmt:message key="update.description" var="update_description_value"/>
<fmt:message key="update.price" var="update_price_value"/>
<fmt:message key="update.weight" var="update_weight_value"/>
<fmt:message key="update.quantity" var="update_quantity_value"/>


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
        <form class="form-horizontal needs-validation" style="margin-top: 10px; margin-left: 220px" action="controller" method="post" enctype="multipart/form-data" novalidate>
            <fieldset>
                <input type="hidden" name="command" value="add_dessert_command"/>
                <legend class="center-block">${locale_add_dessert}</legend>

                <div class="form-group">
                        <input type="text" class="form-control" id="name" max="45"
                               name="name"
                               placeholder="${locale_main_name}"
                               pattern="${attribute_regexp_name}" required>
                    <div class="invalid-feedback">
                        ${update_name_value}
                    </div>
                </div>

                <c:if test="${duplicateName}">
                    <p style="margin-left: 30px;color: red">${locale_main_exist}</p>
                </c:if>

                <div class="form-group">
                    <textarea rows="5" class="form-control" id="description" name="description" maxlength="300" minlength="5" placeholder="${locale_main_description}" required></textarea>
                    <div class="invalid-feedback">
                        ${update_description_value}
                    </div>
                </div>

                <div class="form-group">
                        <select required id="category" name="category" class="form-control">
                            <option value="" selected="selected" disabled="disabled">${locale_category_enter}</option>
                            <c:forEach var="dessertType" items="${dessertTypeList}">
                                <option value="${dessertType.id}">${dessertType.description}</option>
                            </c:forEach>
                        </select>
                    <div class="invalid-feedback">
                    </div>
                </div>

                <div class="form-group">
                        <input required type="text" min="0"
                               class="form-control" id="weight" name="weight"
                               placeholder="${locale_dessert_weight}" pattern="${attribute_regexp_weight}"/>
                    <div class="invalid-feedback">
                        ${update_weight_value}
                    </div>
                </div>

                <div class="form-group">
                            <input required type="text" step="0.1" min="1"
                                   class="form-control" id="price" name="price"
                                   placeholder="${locale_price_name}" pattern="${attribute_regexp_price}"/>
                    <div class="invalid-feedback">
                        ${update_price_value}
                    </div>
                </div>

                <div class="form-group">
                        <input required type="text" min="1" max="100"
                               class="form-control" id="count" name="count"
                               placeholder="${locale_storage_name}" pattern="${attribute_regexp_count}"/>
                    <div class="invalid-feedback">
                        ${update_quantity_value}
                    </div>
                </div>

                <div class="form-group">
                    <i class="fas fa-paperclip" style="margin-top:-10px;  padding-left: 25px; display:block;font-size:22px;"></i>
                    <label style="margin-top:-50px; margin-left: 55px">${locale_dessert_image}</label>
                     <input style="margin-left: -130px;opacity:0;" id="image" type="file" name="image" accept=".jpg, .png"/>
                </div>

                <div class="form-group" style="margin-left: 150px">
                        <button type="submit" class="btn btn-success">${locale_add_item}</button>
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
