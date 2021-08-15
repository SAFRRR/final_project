<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="signup.title" var="signup_title_value"/>
<fmt:message key="signup.username" var="signup_username_value"/>
<fmt:message key="signup.email" var="signup_email_value"/>
<fmt:message key="signup.phone" var="signup_phone_value"/>
<fmt:message key="signup.address" var="signup_address_value"/>
<fmt:message key="signup.submit" var="signup_submit_value"/>
<fmt:message key="signup.name" var="signup_name_value"/>
<fmt:message key="signup.surname" var="signup_surname_value"/>
<fmt:message key="signup.span" var="signup_span_value"/>
<fmt:message key="signup.signin" var="signup_signin_value"/>
<fmt:message key="signup.error.email" var="signup_error_email_value"/>
<fmt:message key="signup.valid.email" var="signup_valid_email_value"/>
<fmt:message key="signup.valid.phone" var="signup_valid_phone_value"/>
<fmt:message key="signup.valid.username" var="signup_valid_username_value"/>
<fmt:message key="signup.valid.name" var="signup_valid_name_value"/>
<fmt:message key="signup.valid.surname" var="signup_valid_surname_value"/>
<fmt:message key="signup.valid.address" var="signup_valid_address_value"/>


<!doctype html>
<html lang="en">
<body>
<jsp:include page="common/sign_header.jsp"/>


<div>
    <div class="form-bg">
        <div class="container">
            <div class="row">
                <div class="col-md-offset-3 col-md-6">
                    <form class="form-horizontal needs-validation" style="margin-top: 20px" action="${pageContext.request.contextPath}/controller" method="post"  id="form-signUp" novalidate>
                        <input type="hidden" name="command" value="sign_up_command"/>
                        <div class="heading">${signup_title_value}</div>
                        <div class="form-group">
                            <i class="fas fa-envelope"></i>
                            <input class="form-control" type="email"
                                   name="email"
                                   placeholder="${signup_email_value}"
                                   pattern="${attribute_regexp_email}" required>
                            <div class="invalid-feedback">
                                ${signup_valid_email_value}
                            </div>
                        </div>
                        <div class="form-group">
                            <i class="fa fa-user"></i>
                            <input class="form-control" type="text" id="form-signUp-login" name="username"
                                   placeholder="${signup_username_value}"
                                   pattern="${attribute_regexp_username}" required>
                            <div class="invalid-feedback">
                                ${signup_valid_username_value}
                            </div>
                        </div>
                        <div class="form-group">
                            <i class="fas fa-id-card"></i>
                            <input class="form-control" type="text" id="form-signUp-name" name="firstName"
                                   placeholder="${signup_name_value}"
                                   pattern="${attribute_regexp_fio}" required>
                            <div class="invalid-feedback">
                                ${signup_valid_name_value}
                            </div>
                        </div>
                        <div class="form-group">
                            <i class="fas fa-id-card"></i>
                            <input class="form-control" type="text" id="form-signUp-surname" name="lastName"
                                   placeholder="${signup_surname_value}"
                                   pattern="${attribute_regexp_fio}" required>
                            <div class="invalid-feedback">
                                ${signup_valid_surname_value}
                            </div>
                        </div>
                        <div class="form-group">
                            <i class="fas fa-phone-alt"></i>
                            <input class="form-control" type="text" id="form-signUp-phone" name="phone"
                                   placeholder="${signup_phone_value}"
                                   pattern="${attribute_regexp_phone_number}" required>
                            <div class="invalid-feedback">
                                ${signup_valid_phone_value}
                            </div>
                        </div>
                        <div class="form-group">
                            <i class="fas fa-map-marker-alt"></i>
                            <input class="form-control" type="text" id="form-signUp-address" name="address"
                                   placeholder="${signup_address_value}"
                                   pattern="${attribute_regexp_address}"
                                   required>
                            <div class="invalid-feedback">
                                ${signup_valid_address_value}
                            </div>
                        </div>
                        <c:if test="${duplicateEmail}">
                            <p id="wrong" style="color: red;  opacity: 1; transition: opacity 500ms;">${signup_error_email_value}</p>

                        </c:if>
                        <button type="submit"  class="btn btn-default">${signup_submit_value}</button>
                        <div class="form-footer">
                            <span class="span-text">${signup_span_value}</span>
                            <a href="${pageContext.request.contextPath}/controller?command=go_to_signin_page_command" class="signUp">${signup_signin_value}</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>


<script>
    (function () {
        'use strict'
        var forms = document.querySelectorAll('.needs-validation')
        Array.prototype.slice.call(forms)
            .forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault()
                        event.stopPropagation()
                    }
                    form.classList.add('was-validated')
                }, false)
            })
    })()

    function initialSetup() {
        if (document.getElementById('wrong') != null) {
            setTimeout(function() {
                document.getElementById('wrong').style.opacity = '0';
            }, 2000);
        }
    }
    initialSetup();
</script>


