<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../common/sign/header.jsp"/>

<div class="content-section">
    <div class="form-bg">
        <div class="container">
            <div class="row">
                <div class="col-md-offset-3 col-md-6">
                    <form class="form-horizontal">
                        <div class="heading">
                            <div>${TEXT[ERROR_PAGE_400]}</div>
                            <div>${TEXT[ERROR_PAGE_400_BAD_REQUEST]}</div>
                        </div>
                        <div class="form-footer">
                            <span class="span-text"></span>
                            <a href="#" onclick="signInPage.openHomePage()" class="signUp" style="text-transform: uppercase">${TEXT[HOME]}</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>



