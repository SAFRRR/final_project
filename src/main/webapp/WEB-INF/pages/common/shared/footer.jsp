<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:message key="common.contact" var="locale_common_contact"/>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" />


<footer class="footer custom-footer">
    <div class="container" style="background-color:rgb(251,241,233); padding: 20px; text-align: center;">
         2021, Anna Safronova:
        <a class="text-reset fw-bold" href="https://github.com/" target="_blank"> <i class="fab fa-lg fa-github" style="color: black"></i></a>



    </div>
</footer>
