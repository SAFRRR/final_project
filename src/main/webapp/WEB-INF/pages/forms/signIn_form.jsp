<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <div class="form-bg">
        <div class="container">
            <div class="row">
                <div class="col-md-offset-3 col-md-6">

                    <form id="form-signIn" class="form-horizontal" novalidate>

                        <div class="heading">${TEXT[SIGNIN_TITLE]}</div>
                        <div class="form-group">
                             <i class="fas fa-envelope"></i>
                            <input class="form-control" type="email" id="form-signIn-email" placeholder="${TEXT[SIGNIN_EMAIL]}"
                                   pattern="${VALID_EMAIL}"
                                   required>
                            <div id="form-signIn-email-invalid-feedback" class="invalid-feedback">
                                ${STANDARD_EMAIL_FEEDBACK}
                            </div>
                        </div>
                        <div class="form-group">
                            <i class="fa fa-lock"></i>
                            <input class="form-control" id="form-signIn-password" type="password" placeholder="${TEXT[SIGNIN_PASSWORD]}"
                                   pattern="${VALID_PASSWORD}"
                                   required>
                            <div id="form-signIn-password-invalid-feedback" class="invalid-feedback">
                                ${STANDARD_PASSWORD_FEEDBACK}
                            </div>
<%--                            <a href="" class="forgot-password">Forgot password?</a>--%>
                        </div>

                        <button type="submit" class="btn btn-default">${TEXT[SIGNIN_SUBMIT]}</button>

                        <div class="form-footer">
                            <span class="span-text">${TEXT[SIGNIN_SPAN]}</span>
                            <a href="#" onclick="signInPage.openSignUpPage()" class="signUp">${TEXT[SIGNIN_SIGNUP]}</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

