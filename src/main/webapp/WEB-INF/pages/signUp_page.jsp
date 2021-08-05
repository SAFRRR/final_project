<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="custom-tag" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Title</title>
    <script type="module" src="${pageContext.request.contextPath}/static/js/pages/signUp.js"></script>
</head>
<body>

<jsp:include page="common/sign/header.jsp"/>
<jsp:include page="forms/signUp_form.jsp"/>
<custom-tag:var />



</body>
</html>
