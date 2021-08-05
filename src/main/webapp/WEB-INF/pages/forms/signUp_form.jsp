<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset = "UTF-8" />
<%--<script type="module" src="${pageContext.request.contextPath}/static/js/pages/signUp.js"></script>--%>
<div>
    <div class="form-bg">
        <div class="container">
            <div class="row">
                <div class="col-md-offset-3 col-md-6">

                    <form class="form-horizontal"  id="form-signUp" novalidate> <%-- <form id="form-register" class="needs-validation" novalidate>--%>

                        <div class="heading">${TEXT[SIGNUP_TITLE]}</div>

                        <div class="form-group">
                            <i class="fas fa-envelope"></i>
                            <input class="form-control" type="email" id="form-signUp-email" name="form-signUp-email"
                                   placeholder="${TEXT[SIGNUP_EMAIL]}"
                                    pattern="${VALID_EMAIL}" required>
                            <div id="form-signUp-email-valid-feedback" class="valid-feedback">
                            </div>
                            <div id="form-signUp-email-invalid-feedback" class="invalid-feedback">
                               ${STANDARD_EMAIL_FEEDBACK}
                            </div>
                        </div>


                        <div class="form-group">
                            <i class="fa fa-user"></i>
                            <input class="form-control" type="text" id="form-signUp-login" name="form-signUp-login"
                                   placeholder="${TEXT[SIGNUP_LOGIN]}"
                            pattern="${VALID_LOGIN}" required>
                            <div id="form-signUp-login-invalid-feedback" class="invalid-feedback">
                                ${STANDARD_LOGIN_FEEDBACK}
                            </div>
                        </div>



                        <div class="password">
                            <div class="form-group">
                                <i class="fa fa-lock"></i>
                                <input class="form-control" type="password" id="form-signUp-password" name="form-signUp-password"
                                       placeholder="${TEXT[SIGNUP_PASSWORD]}" pattern="${VALID_PASSWORD}"
                                       required>
                                <div id="form-signUp-password-invalid-feedback" class="invalid-feedback">
                                    ${STANDARD_PASSWORD_FEEDBACK}
                                </div>
                            </div>


                            <div class="form-group">
                                <i class="fa fa-lock"></i>
                                <input class="form-control" type="password" id="form-signUp-password-confirm" name="form-signUp-password-confirm"
                                       placeholder="${TEXT[SIGNUP_PASSWORD_CONFIRM]}"
                                        pattern="${VALID_PASSWORD}"
                                        required>
                                <div id="form-signUp-password-confirm-invalid-feedback" class="invalid-feedback">
                                    ${STANDARD_PASSWORD_CONFIRMATION_FEEDBACK}
                                </div>
                            </div>


                        </div>


                        <button type="submit"  class="btn btn-default">${TEXT[SIGNIN_SUBMIT]}</button>


                        <div class="form-footer">
                            <span class="span-text">${TEXT[SIGNUP_SPAN]}</span>
                            <a href="#" onclick="signUpPage.openSignInPage()" class="signUp">${TEXT[SIGNUP_SIGNIN]}</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

