package com.safronova.webproject.model.util.localization;

import com.safronova.webproject.controller22.EnumValue;

public enum LocaleKey implements EnumValue {
    FUCK("fuck"),
    COMMON_PASTRIES("common.pastries"),
    COMMON_BAKERY("common.bakery"),
    COMMON_BREAD("common.bread"),
    COMMON_CHOCOLATE("common.chocolate"),
    COMMON_ABOUT("common.about"),
    COMMON_SIGNIN("common.signIn"),
    COMMON_CATEGORY("common.category"),
    SIGNIN_TITLE("signInForm.title"),
    SIGNIN_SUBMIT("signInForm.submit"),
    SIGNIN_PASSWORD("signInForm.password"),
    SIGNIN_SPAN("signInForm.span"),
    SIGNIN_SIGNUP("signInForm.signUp"),
    SIGNUP_TITLE("signUpForm.title"),
    SIGNUP_SPAN("signUpForm.span"),
    SIGNUP_SIGNIN("signUpForm.signIn"),
    SIGNUP_LOGIN("signUpForm.login"),
    SIGNUP_PASSWORD("signUpForm.password"),
    SIGNUP_EMAIL("signUpForm.email"),
    SIGNIN_EMAIL("signInForm.email"),
    SIGNUP_PASSWORD_CONFIRM("signUpForm.passwordConfirm"),
    //feedback
    STANDARD_EMAIL_FEEDBACK("standard.emailFeedback"),
    STANDARD_LOGIN_FEEDBACK("standard.loginFeedback"),
    STANDARD_PASSWORD_FEEDBACK("standard.passwordFeedback"),
    STANDARD_PASSWORD_CONFIRMATION_FEEDBACK("standard.passwordConfirmFeedback"),
    PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL("password.feedbackInvalidPasswordsAreNotEqual"),
    LOGIN_FEEDBACK_INVALID("login.feedbackInvalid"),
    EMAIL_FEEDBACK_INVALID("email.feedbackInvalid"),
    PASSWORD_FEEDBACK_INVALID("password.feedbackInvalid"),
    PASSWORD_CONFIRMATION_FEEDBACK_INVALID("password.confirmationFeedbackInvalid"),
    EMPTY_MESSAGE("empty.message"),
    EMAIL_FEEDBACK_INVALID_USER_EXISTS("email.feedbackInvalidUserExists"),
    LOGIN_FEEDBACK_INVALID_USER_NOT_EXIST("login.feedbackInvalidUserNotExist"),
    LOGIN_FEEDBACK_INVALID_USER_EXISTS("login.feedbackInvalidUserExists"),
    INTERNAL_SERVER_ERROR("internal.serverError"),
    CHECK_YOUR_EMAIL("check.yourEmail"),

    EMAIL_SUBJECT_SIGN_UP("email.subjectSignUp"),
    EMAIL_SUBJECT_CHANGE_EMAIL("email.subjectChangeEmail"),
    EMAIL_CONTENT_SIGN_UP("email.contentSignUp"),
    EMAIL_CONTENT_CHANGE_EMAIL("email.contentChangeEmail"),
    EMAIL_PASSWORD_FEEDBACK_INVALID("email.passwordFeedbackInvalid"),



    // error pages
    ERROR_PAGE("error.page"),
    ERROR_PAGE_400("error.page400"),
    ERROR_PAGE_400_BAD_REQUEST("error.page400BadRequest"),
    ERROR_PAGE_403("error.page403"),
    ERROR_PAGE_403_FORBIDDEN("error.page403Forbidden"),
    ERROR_PAGE_404("error.page404"),
    ERROR_PAGE_404_NOT_FOUND("error.page404NotFound"),
    ERROR_PAGE_500("error.page500"),
    ERROR_PAGE_500_INTERNAL_SERVER_ERROR("error.page500InternalServerError"),

    HOME("home");


//    PASSWORD_FEEDBACK_INVALID("password_feedback_invalid"),
//    PASSWORD_CONFIRMATION_FEEDBACK_INVALID("password_confirmation_feedback_invalid"),
//    PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL("password_feedback_invalid_passwords_are_not_equal"),
//    PASSWORD_FEEDBACK_INVALID_NO_USER_WITH_ID("password_feedback_invalid_no_user_with_id"),




    private String value;

    private LocaleKey(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
