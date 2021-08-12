<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="signin.email" var="signin_email_value"/>
<fmt:message key="signin.password" var="signin_password_value"/>
<fmt:message key="signin.submit" var="signin_submit_value"/>
<fmt:message key="signin.title" var="signin_title_value"/>
<fmt:message key="signin.span" var="signin_span_value"/>
<fmt:message key="signin.signup" var="signin_signup_value"/>
<fmt:message key="signin.error" var="signin_error_value"/>

<!doctype html>
<html lang="en">
<body>
<jsp:include page="common/sign_header.jsp"/>
<div>
    <div class="form-bg">
        <div class="container">
            <div class="row">
                <div class="col-md-offset-3 col-md-6">
                    <form id="form-signIn" class="form-horizontal" action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="command" value="sign_in_command">

                        <div class="heading">${signin_title_value}</div>
                        <div class="form-group">
                            <i class="fas fa-envelope"></i>
                            <input class="form-control" type="email" id="form-signIn-email" name="email"
                                   placeholder="${signin_email_value}"
                                   pattern="${attribute_regexp_email}"
                                   required>
                        </div>

                        <div class="form-group">
                            <i class="fa fa-lock"></i>
                            <input class="form-control" id="form-signIn-password" type="password" name="password"
                                   placeholder="${signin_password_value}"
                                   pattern="${attribute_regexp_password}"
                                   required>
                        </div>

                        <c:if test="${wrongData}">
                            <p style="color: red">${signin_error_value}</p>
                        </c:if>

                        <button type="submit" class="btn btn-default">${signin_submit_value}</button>

                        <div class="form-footer">
                            <span class="span-text">${signin_span_value}</span>
                            <a href="${pageContext.request.contextPath}/controller?command=go_to_signup_page_command" class="signUp">${signin_signup_value}</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
