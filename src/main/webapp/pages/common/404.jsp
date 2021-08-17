<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="error.page404" var="error_title_value"/>
<fmt:message key="error.page404NotFound" var="error_description_value"/>
<fmt:message key="email.home" var="locale_email_home"/>

<!doctype html>
<html lang="en">
<body>
<jsp:include page="sign_header.jsp"/>
<div class="content-section">
    <div class="form-bg">
        <div class="container">
            <div class="row">
                <div class="col-md-offset-3 col-md-6">
                    <form class="form-horizontal">
                        <div class="heading">
                            <div>${error_title_value}</div>
                            <div>${error_description_value}</div>
                        </div>
                        <div class="form-footer">
                            <span class="span-text"></span>
                            <a href="${pageContext.request.contextPath}/controller?command=go_to_home_page_command"  class="signUp" style="text-transform: uppercase">${locale_email_home}</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
