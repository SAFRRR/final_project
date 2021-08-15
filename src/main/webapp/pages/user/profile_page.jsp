<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="signup.username" var="signup_username_value"/>
<fmt:message key="signup.phone" var="signup_phone_value"/>
<fmt:message key="signup.address" var="signup_address_value"/>
<fmt:message key="signup.name" var="signup_name_value"/>
<fmt:message key="signup.surname" var="signup_surname_value"/>
<fmt:message key="signup.current.password" var="signup_current_password_value"/>
<fmt:message key="signup.new.password" var="signup_new_password_value"/>
<fmt:message key="signup.new.password.confirm" var="signup_new_password_confirm_value"/>
<fmt:message key="profile.save.changes" var="profile_save_changes_value"/>
<fmt:message key="profile.settings" var="profile_settings_value"/>
<fmt:message key="edit.passwords" var="edit_passwords_value"/>
<fmt:message key="edit.password" var="edit_password_value"/>
<fmt:message key="edit.result" var="edit_result_value"/>
<fmt:message key="signup.valid.phone" var="signup_valid_phone_value"/>
<fmt:message key="signup.valid.username" var="signup_valid_username_value"/>
<fmt:message key="signup.valid.name" var="signup_valid_name_value"/>
<fmt:message key="signup.valid.surname" var="signup_valid_surname_value"/>
<fmt:message key="signup.valid.address" var="signup_valid_address_value"/>
<fmt:message key="signup.valid.password" var="signup_valid_password_value"/>

<!DOCTYPE html>
<!doctype html>
<html lang="en">
<body>
<jsp:include page="../common/header.jsp"/>
<div class="container">
    <div class="form-bg">
        <div class="container">
            <div class="row">
                <div class="col-md-offset-3 col-md-6">
                    <form id="form-signIn" class="form-horizontal needs-validation" style="margin-top: 20px" action="${pageContext.request.contextPath}/controller" method="post" novalidate>
                        <input type="hidden" name="command" value="settings_edit_command">
                        <div class="heading" style="margin-top: -20px">${profile_settings_value}</div>

                        <div class="form-group">
                                <input class="form-control" style="margin-top: -10px" type="text" id="firstName" name="firstName"
                                       placeholder="${signup_name_value}"
                                   pattern="${attribute_regexp_fio}" value="${user.firstName}" required>
                            <div class="invalid-feedback">
                                ${signup_valid_name_value}
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="lastName" name="lastName"
                                   placeholder="${signup_surname_value}"
                                   pattern="${attribute_regexp_fio}" value="${user.lastName}" required>
                            <div class="invalid-feedback">
                                ${signup_valid_surname_value}
                            </div>
                        </div>

                        <div class="form-group">
                            <input type="text" class="form-control" id="username" name="username"
                                   placeholder="${signup_username_value}"
                                   pattern="${attribute_regexp_username}" value="${user.username}" required>
                            <div class="invalid-feedback">
                                ${signup_valid_username_value}
                            </div>
                        </div>

                        <div class="form-group">
                            <input type="text" class="form-control" id="phone" name="phone"
                                   placeholder="${signup_phone_value}"
                                   pattern="${attribute_regexp_phone_number}" value="${user.phone}" required>
                            <div class="invalid-feedback">
                                ${signup_valid_phone_value}
                            </div>
                        </div>

                        <div class="form-group">
                            <input type="text" class="form-control" id="address" name="address"
                                   placeholder="${signup_address_value}"
                                   value="${user.address}" required />
                            <div class="invalid-feedback">
                                ${signup_valid_address_value}
                            </div>
                        </div>

                        <div class="form-group">
                            <input  type="password" class="form-control" id="currentPassword"
                                   placeholder="${signup_current_password_value}"
                                   name="currentPassword" pattern="${attribute_regexp_password}" required>
                            <div class="invalid-feedback">
                                ${signup_valid_password_value}
                            </div>
                        </div>

                        <div class="form-group">
                            <c:if test="${currentPasswordNotEquals}">
                                <p id="wrong" style="color: red;  opacity: 1; transition: opacity 500ms;" >${edit_password_value}</p>
                            </c:if>

                            <input type="password" class="form-control" id="newPassword"
                                   placeholder="${signup_new_password_value}"
                                   name="newPassword" pattern="${attribute_regexp_password}" required>
                            <div class="invalid-feedback">
                                ${signup_valid_password_value}
                            </div>
                        </div>

                        <div class="form-group">
                            <input type="password" class="form-control" id="confirmPassword"
                                   placeholder="${signup_new_password_confirm_value}"
                                   name="confirmPassword" pattern="${attribute_regexp_password}" required>
                            <div class="invalid-feedback">
                                ${signup_valid_password_value}
                            </div>
                        </div>
                        <c:if test="${passwordNotEquals}">
                            <p id="wrong" style="color: red;  opacity: 1; transition: opacity 500ms;">${edit_passwords_value}</p>
                        </c:if>

                        <c:if test="${updateUserInfo}">
                            <p id="wrong" style="color: green;  opacity: 1; transition: opacity 500ms;">${edit_result_value}</p>
                        </c:if>

                        <button type="submit" style="width: 300px; margin-left: 30px" class="btn btn-default">${profile_save_changes_value}</button>
                        <div class="form-footer">
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
