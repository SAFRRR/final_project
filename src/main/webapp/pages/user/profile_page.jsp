<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


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
<fmt:message key="signup.current.password" var="signup_current_password_value"/>
<fmt:message key="signup.new.password" var="signup_new_password_value"/>
<fmt:message key="password.feedbackInvalidPasswordsAreNotEqual" var="signup_password_notequal_value"/>
<fmt:message key="signup.new.password.confirm" var="signup_new_password_confirm_value"/>
<fmt:message key="profile.save.changes" var="profile_save_changes_value"/>
<fmt:message key="profile.settings" var="profile_settings_value"/>
<fmt:message key="edit.passwords" var="edit_passwords_value"/>
<fmt:message key="edit.password" var="edit_password_value"/>
<fmt:message key="edit.result" var="edit_result_value"/>



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
                    <form id="form-signIn" class="form-horizontal" style="margin-top: 20px" action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="command" value="settings_edit_command">
                        <div class="heading" style="margin-top: -20px">${profile_settings_value}</div>

                        <div class="form-group">
                                <input class="form-control" style="margin-top: -10px" type="text" id="firstName" name="firstName"
                                       placeholder="${signup_name_value}"
                                   pattern="${attribute_regexp_fio}" value="${user.firstName}" required>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="lastName" name="lastName"
                                   placeholder="${signup_surname_value}"
                                   pattern="${attribute_regexp_fio}" value="${user.lastName}" required>
                        </div>

                        <div class="form-group">
                            <input type="text" class="form-control" id="username" name="username"
                                   placeholder="${signup_username_value}"
                                   pattern="${attribute_regexp_username}" value="${user.username}" required>
                        </div>

                        <div class="form-group">
                            <input type="text" class="form-control" id="phone" name="phone"
                                   placeholder="${signup_phone_value}"
                                   pattern="${attribute_regexp_phone_number}" value="${user.phone}" required>
                        </div>

                        <div class="form-group">
                            <input type="text" class="form-control" id="address" name="address"
                                   placeholder="${signup_address_value}"
                                   value="${user.address}" required />
                        </div>

                        <div class="form-group">
                            <input  type="password" class="form-control" id="currentPassword"
                                   placeholder="${signup_current_password_value}"
                                   name="currentPassword" pattern="${attribute_regexp_password}" required>
                        </div>

                        <div class="form-group">
                            <c:if test="${currentPasswordNotEquals}">
                                <p style="color: red">${edit_password_value}</p>
                            </c:if>

                            <input type="password" class="form-control" id="newPassword"
                                   placeholder="${signup_new_password_value}"
                                   name="newPassword" pattern="${attribute_regexp_password}" required>
                        </div>

                        <div class="form-group">
                            <input type="password" class="form-control" id="confirmPassword"
                                   placeholder="${signup_new_password_confirm_value}"
                                   name="confirmPassword" pattern="${attribute_regexp_password}" required>
                        </div>
                        <c:if test="${passwordNotEquals}">
                            <p style="color: red">${edit_passwords_value}</p>
                        </c:if>

                        <c:if test="${updateUserInfo}">
                            <p style="color: green">${edit_result_value}</p>
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







