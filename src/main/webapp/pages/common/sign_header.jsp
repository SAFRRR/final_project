<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Dessert_Safrr</title>

    <link href="${pageContext.request.contextPath}/static/lib/fonts/css/all.min.css" rel="stylesheet">
    <!-- Bootstrap 5 -->
    <link href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/static/lib/bootstrap/js/bootstrap.bundle.min.js"></script>


    <!-- jQuery -->
    <%--    <script src="${pageContext.request.contextPath}/static/lib/jquery/jquery.min.js"></script>--%>
    <script src="${pageContext.request.contextPath}/static/lib/jquery/jquery-3.6.0.min.js"></script>

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/static/css/hStyle.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/static/lib/fonts/Geometria-Light/style.css" rel="stylesheet" type="text/css">





</head>
<body>
<header>
    <div class="page-top" style="width: 100%; height: 20px; background-color: #1b6d85;"></div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <jsp:include page="logo.jsp"/>
<%--            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">--%>
<%--                <span class="navbar-toggler-icon"></span>--%>
<%--            </button>--%>

            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="nav navbar-nav ms-auto mb-2 mb-lg-0">

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false" style="color: #e1791a;">
                            <i class="fas fa-lg fa-language" style="color: #e1791a;"></i>
                        </a>
                        <ul class="dropdown-menu" style="min-width: 0px;" aria-labelledby="navbarDropdownMenuLink">
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
</header>

<form id="localeForm" action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="change_locale_command">
</form>
