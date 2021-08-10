<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="main.title" var="locale_main_title"/>
<fmt:message key="main.catalog" var="locale_main_catalog"/>
<fmt:message key="main.desserts" var="locale_main_desserts"/>
<fmt:message key="main.add.item" var="locale_main_add_item"/>
<fmt:message key="main.admin.desserts" var="locale_main_admin_desserts"/>
<fmt:message key="main.admin.create" var="locale_main_admin_create"/>
<fmt:message key="main.dessert.title" var="locale_main_dessert_title"/>
<fmt:message key="main.admin.order" var="locale_main_admin_order"/>
<fmt:message key="main.basket.title" var="locale_main_basket_title"/>
<fmt:message key="main.profile.title" var="locale_main_profile_title"/>
<fmt:message key="main.signin" var="locale_main_signin"/>
<fmt:message key="main.order.history" var="locale_main_order_history"/>
<fmt:message key="main.logout" var="locale_main_logout"/>
<fmt:message key="common.desserts" var="locale_desserts_card"/>
<fmt:message key="common.bakery" var="locale_bakery"/>
<fmt:message key="common.chocolate" var="locale_chocolate"/>

<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <title>${locale_main_title}</title>

    <!-- Bootstrap 5 -->
    <link href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/static/lib/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/static/lib/jquery/jquery-3.6.0.min.js"></script>
    <!-- Font -->
    <link href="${pageContext.request.contextPath}/static/lib/fonts/css/all.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/hStyle.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/static/css/list.css" rel="stylesheet" type="text/css">


</head>

<body>
<div class="page-top" style="width: 100%; height: 20px; background-color: #1b6d85;"></div>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <c:set var="user" value="${sessionScope.user}"/>
        <jsp:include page="logo.jsp"/>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="nav navbar-nav ms-auto mb-2 mb-lg-0">

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" role="button" aria-expanded="false" href="#" style="color: #e1791a;" >
                        ${locale_main_catalog}<span class="caret"></span>
                        <%--"Controller?command=go_to_about_page_command"--%>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end" style="min-width: 0px;" aria-labelledby="navbarDropdownMenuLink">
                            <li><a class="dropdown-item" style="color: #e1791a;"   href="${pageContext.request.contextPath}/controller?command=find_dessert_by_category_command&category=1">${locale_desserts_card}</a></li>
                            <li><a class="dropdown-item" style="color: #e1791a;"   href="${pageContext.request.contextPath}/controller?command=find_dessert_by_category_command&category=2">${locale_bakery}</a></li>
                            <li><a class="dropdown-item" style="color: #e1791a;"   href="${pageContext.request.contextPath}/controller?command=find_dessert_by_category_command&category=3">${locale_chocolate}</a></li>


                            <c:if test="${user.role == 'ADMIN'}">
                                <li><a class="dropdown-item" style="color: #e1791a;" href="${pageContext.request.contextPath}/controller?command=go_to_add_dessert_page_command">${locale_main_add_item}</a></li>
                                <li><a class="dropdown-item" style="color: #e1791a;" href="${pageContext.request.contextPath}/controller?command=go_to_dessert_list_page_command " >${locale_main_admin_desserts}</a></li>
                            </c:if>
                    </ul>
                </li>

                <c:if test="${user.email == null}">
                    <li class="nav-item">
                        <a class="nav-link" style="color: #e1791a;" href="${pageContext.request.contextPath}/controller?command=go_to_signin_page_command"><i class="fas fa-lg fa-sign-in-alt"></i>&nbsp${locale_main_signin}</a>
                    </li>
                </c:if>

                <c:if test="${user.role == 'USER'}">
                    <li class="nav-item">
                        <a class="nav-link" style="color: #e1791a;" href="${pageContext.request.contextPath}/controller?command=go_to_basket_page_command "><i class="fas fa-lg fa-shopping-basket"></i>${locale_main_basket_title}</a>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
                           data-toggle="dropdown" aria-expanded="false" style="color: #e1791a;">
                            <i class="fas fa-lg fa-user"></i>
                        </a>

                        <ul class="dropdown-menu dropdown-menu-end" style="min-width: 0px;" aria-labelledby="navbarDropdownMenuLink">
                            <li><a class="dropdown-item" style="color: #e1791a;" href="${pageContext.request.contextPath}/controller?command=go_to_profile_page_command">${locale_main_profile_title}</a></li>
                            <li><a class="dropdown-item" style="color: #e1791a;" href="${pageContext.request.contextPath}/controller?command=go_to_order_history_page_command">${locale_main_order_history}</a></li>
                            <li><a class="dropdown-item" style="color: #e1791a;" href="${pageContext.request.contextPath}/controller?command=log_out_command">${locale_main_logout}</a></li>
                        </ul>
                    </li>

                </c:if>


                <c:if test="${user.role == 'ADMIN'}">
                    <li class="nav-item">
                        <a class="nav-link" style="color: #e1791a;" href="${pageContext.request.contextPath}/controller?command=go_to_orders_page_command">${locale_main_admin_order}</a>
                    </li>
                </c:if>


                <c:if test="${user.role == 'ADMIN'}">
<%--                    <li class="nav-item">--%>
<%--                        <a class="nav-link" style="color: #e1791a;" href="${pageContext.request.contextPath}/controller?command=go_to_basket_page_command"><i class="fas fa-lg fa-shopping-basket"></i>${locale_main_basket_title}</a>--%>
<%--                            &lt;%&ndash;           Controller?command=go_to_basket_page_command             &ndash;%&gt;--%>
<%--                    </li>--%>



                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
                           data-toggle="dropdown" aria-expanded="false" style="color: #e1791a;">
                                <%--                                ${user.username}--%>
                            <i class="fas fa-lg fa-user"></i>
                        </a>

                        <ul class="dropdown-menu dropdown-menu-end" style="min-width: 0px;" aria-labelledby="navbarDropdownMenuLink">
                            <li><a class="dropdown-item" style="color: #e1791a;" href="${pageContext.request.contextPath}/controller?command=go_to_profile_page_command">${locale_main_profile_title}</a></li>
                                <%--Controller?command=go_to_profile_page_command--%>
                            <li>
                                    <%--                                <button form="headerForm" class="btn btn-primary btn-group-justified" type="submit"--%>
                                    <%--                                        name="command" value="log_out_command">${locale_main_logout}--%>
                                    <%--                                </button>--%>
                                <a class="dropdown-item" style="color: #e1791a;" href="${pageContext.request.contextPath}/controller?command=log_out_command">${locale_main_logout}</a>
                            </li>
                        </ul>
                    </li>

                </c:if>













                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false" style="color: #e1791a;">
                        <i class="fas fa-lg fa-language" style="color: #e1791a;"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end" style="min-width: 0px;" aria-labelledby="navbarDropdownMenuLink">
                        <li>
                            <button class="dropdown-item" style="color: #e1791a;" form="localeForm" type="submit" name="locale" value="ru">ru</button>
                        </li>
                        <li>
                            <button class="dropdown-item" style="color: #e1791a;" form="localeForm" type="submit" name="locale" value="en">en</button>
                        </li>

                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<form id="localeForm" action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="change_locale_command">
</form>

<form id="headerForm" action="controller" method="post"></form>
</body>
