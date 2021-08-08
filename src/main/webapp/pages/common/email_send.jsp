<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="email.msg" var="locale_email_msg"/>
<fmt:message key="email.home" var="locale_email_home"/>


<!DOCTYPE html>
<!doctype html>
<html lang="en">
<body>
<jsp:include page="sign_header.jsp"/>

<div class="msg">

    <div class="form-bg">
        <div class="container">
            <div class="row">
                <div class="col-md-offset-3 col-md-6">
                    <form class="form-horizontal">
                        <div class="heading">${locale_email_msg}</div>
                        <div class="form-footer">
                            <span class="span-text"></span>
                            <a href="${pageContext.request.contextPath}/controller?command=go_to_home_page_command" class="signUp" style="text-transform: uppercase">${locale_email_home}</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
