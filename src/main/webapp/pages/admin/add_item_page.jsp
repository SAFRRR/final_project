<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="main.add.dessert" var="locale_add_dessert"/>
<fmt:message key="admin.dessert.name" var="locale_dessert_name"/>
<fmt:message key="main.name" var="locale_main_name"/>
<fmt:message key="main.description" var="locale_main_description"/>
<fmt:message key="admin.description.item" var="locale_description_item"/>
<fmt:message key="main.category" var="locale_main_category"/>
<fmt:message key="main.category.enter" var="locale_category_enter"/>
<fmt:message key="admin.dessert.weight" var="locale_dessert_weight"/>
<fmt:message key="admin.dessert.price" var="locale_dessert_price"/>
<fmt:message key="admin.price.name" var="locale_price_name"/>
<fmt:message key="admin.storage.name" var="locale_storage_name"/>
<fmt:message key="admin.dessert.image" var="locale_dessert_image"/>
<fmt:message key="main.add.item" var="locale_add_item"/>
<fmt:message key="main.cancel" var="locale_main_cancel"/>



<!doctype html>
<html>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="container">
    <div class="row">
<%--        <c:if test="${wrongInput}">--%>
<%--            <div class="alert alert-danger">--%>
<%--                Oooops, some fields have incorrect input.--%>
<%--                Remember: watering, price and count in storage should have positive amount--%>
<%--            </div>--%>
<%--        </c:if>--%>
        <form class="form-horizontal" style="margin-top: 10px; margin-left: 220px" action="controller" method="post" enctype="multipart/form-data">
            <fieldset>
                <input type="hidden" name="command" value="add_dessert_command"/>
                <legend class="center-block">${locale_add_dessert}</legend>

                <!--Name of Item-->
                <div class="form-group">
                        <input type="text" class="form-control" id="name" max="50"
                               name="name"
                               placeholder="${locale_main_name}"
                               pattern="${attribute_regexp_name}" required/>
                </div>

                <!--Description-->
                <div class="form-group">
                    <textarea rows="5" class="form-control" id="description" name="description" maxlength="700" minlength="5" placeholder="${locale_main_description}" required></textarea>
                </div>

                <!--Category-->
                <div class="form-group">
                        <select required id="category" name="category" class="form-control">
                            <option value="" selected="selected" disabled="disabled">${locale_category_enter}</option>
                            <c:forEach var="dessertType" items="${dessertTypeList}">
                                <option value="${dessertType.id}">${dessertType.description}</option>
                            </c:forEach>
                        </select>
                </div>

                <!--Weight-->
                <div class="form-group">
                        <input required type="text" min="0"
                               class="form-control" id="weight" name="weight"
                               placeholder="${locale_dessert_weight}" pattern="${attribute_regexp_weight}"/>
                </div>

                <!--Price-->
                <div class="form-group">
                            <input required type="text" step="0.1" min="1"
                                   class="form-control" id="price" name="price"
                                   placeholder="${locale_price_name}" pattern="${attribute_regexp_price}"/>
                </div>

                <!--In storage-->
                <div class="form-group">
                        <input required type="text" min="1" max="99"
                               class="form-control" id="count" name="count"
                               placeholder="${locale_storage_name}" pattern="${attribute_regexp_count}"/>
                </div>

                <!--Upload image-->
                <div class="example-1" style="padding:1em;margin:1em">
                    <div class="form-group">
                        <label class="label" style="width:120px;border:2px dashed grey;border-radius:5px;display:block;padding:1.2em;transition:border 300ms ease;cursor:pointer;text-align:center">
<%--                            <i class="fas fa-paperclip" style="display:block;font-size:42px;padding-bottom:16px"></i>--%>
<%--                            <span class="title">Add file</span>--%>
<%--                    <label class="col-md-2 control-label" for="image">${locale_dessert_image}</label>--%>
<%--                    <div class="col-md-8">--%>
                            <input style="outline:0;opacity:0;pointer-events:none;user-select:none" id="image" type="file" name="image"/>
                        </label>
                </div>
                </div>



<%--                .example-1 .label:hover{border:2px solid #000}--%>
<%--                .example-1 .label:hover i,.example-1 .label:hover .title{color:#000}--%>





                <div class="form-group" style="margin-left: 150px">
                        <button type="submit" class="btn btn-success">${locale_add_item}</button>
                        <a class="btn btn-danger" href="controller?command=go_to_dessert_page_command">${locale_main_cancel}</a>
                </div>
            </fieldset>
        </form>
    </div>
</div>
</body>
</html>
