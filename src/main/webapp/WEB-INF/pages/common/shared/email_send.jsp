<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="module" src="${pageContext.request.contextPath}/static/js/pages/signIn.js"></script>
</head>
<body>
<jsp:include page="../sign/header.jsp"/>
<div class="msg">

    <div class="form-bg">
        <div class="container">
            <div class="row">
                <div class="col-md-offset-3 col-md-6">
                    <form class="form-horizontal">
                        <div class="heading">${TEXT[CHECK_YOUR_EMAIL]}</div>
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
