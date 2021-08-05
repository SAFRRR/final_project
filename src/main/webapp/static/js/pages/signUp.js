import { SignUpForm } from "../modules/user.js"
import { changeLocationIfUndefined } from "../modules/util.js"

let signUpForm;
let formSignUpElement;
let enteredEmailElement;
let enteredEmailInvalidFeedback;
let enteredEmailValidFeedback;
let enteredLoginElement;
let enteredLoginInvalidFeedback;
let enteredPasswordElement;
let enteredPasswordInvalidFeedback;
let enteredPasswordConfirmElement;
let enteredPasswordConfirmationInvalidFeedback;

// Setup
$(function() {
    formSignUpElement = document.getElementById("form-signUp");

    enteredEmailElement = document.getElementById("form-signUp-email");
    enteredEmailInvalidFeedback = document.getElementById("form-signUp-email-invalid-feedback");
    enteredEmailValidFeedback = document.getElementById("form-signUp-email-valid-feedback");

    enteredLoginElement = document.getElementById("form-signUp-login");
    enteredLoginInvalidFeedback = document.getElementById("form-signUp-login-invalid-feedback");

    enteredPasswordElement = document.getElementById("form-signUp-password");
    enteredPasswordInvalidFeedback = document.getElementById("form-signUp-password-invalid-feedback");

    enteredPasswordConfirmElement = document.getElementById("form-signUp-password-confirm");
    enteredPasswordConfirmationInvalidFeedback = document.getElementById("form-signUp-password-confirm-invalid-feedback");

    signUpForm = new SignUpForm(formSignUpElement,
        enteredEmailElement, enteredEmailValidFeedback, enteredEmailInvalidFeedback,
        enteredLoginElement, null, enteredLoginInvalidFeedback,
        enteredPasswordElement, null, enteredPasswordInvalidFeedback,
        enteredPasswordConfirmElement, null, enteredPasswordConfirmationInvalidFeedback);
});

function signUpUser() {
    let data = {};
    data[EMAIL] = enteredEmailElement.value;
    data[LOGIN] = enteredLoginElement.value;
    data[PASSWORD] = enteredPasswordElement.value;
    data[PASSWORD_CONFIRM] = enteredPasswordConfirmElement.value;
    let url = "controller?command=signUp_user_command";
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            let url = "controller?command=go_to_email_check_page_command";
            location.assign(url);
            // signUpForm.setEmailFeedback(jqXHR.responseJSON[EMAIL_STATUS], jqXHR.responseJSON[EMAIL_FEEDBACK], jqXHR.responseJSON[EMAIL_FEEDBACK]);
            // signUpForm.setLoginFeedback(jqXHR.responseJSON[LOGIN_STATUS], jqXHR.responseJSON[LOGIN_FEEDBACK], jqXHR.responseJSON[LOGIN_FEEDBACK]);
            // signUpForm.setPasswordFeedback(
            //     jqXHR.responseJSON[PASSWORD_STATUS],
            //     jqXHR.responseJSON[PASSWORD_FEEDBACK],
            //     jqXHR.responseJSON[PASSWORD_FEEDBACK]
            // );
            // signUpForm.setPasswordConfirmationFeedback(
            //     jqXHR.responseJSON[PASSWORD_CONFIRMATION_STATUS],
            //     jqXHR.responseJSON[PASSWORD_CONFIRMATION_FEEDBACK],
            //     jqXHR.responseJSON[PASSWORD_CONFIRMATION_FEEDBACK]
            // );
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            console.log("fuck");
            changeLocationIfUndefined(jqXHR);
            signUpForm.removeValidation();
            signUpForm.setEmailFeedback(jqXHR.responseJSON[EMAIL_STATUS], jqXHR.responseJSON[EMAIL_FEEDBACK], jqXHR.responseJSON[EMAIL_FEEDBACK]);
            signUpForm.setLoginFeedback(jqXHR.responseJSON[LOGIN_STATUS], jqXHR.responseJSON[LOGIN_FEEDBACK], jqXHR.responseJSON[LOGIN_FEEDBACK]);
            signUpForm.setPasswordFeedback(
                jqXHR.responseJSON[PASSWORD_STATUS],
                jqXHR.responseJSON[PASSWORD_FEEDBACK],
                jqXHR.responseJSON[PASSWORD_FEEDBACK]
            );
            signUpForm.setPasswordConfirmationFeedback(
                jqXHR.responseJSON[PASSWORD_CONFIRMATION_STATUS],
                jqXHR.responseJSON[PASSWORD_CONFIRMATION_FEEDBACK],
                jqXHR.responseJSON[PASSWORD_CONFIRMATION_FEEDBACK]
            );
        }
    });
}

function openSignInPage() {
    let url = "controller?command=go_to_signIn_page_command";
    location.assign(url);
}

export const signUpPage = {
    signUpUser: signUpUser,
    openSignInPage: openSignInPage
};

window.signUpPage = signUpPage;

//SignUp
$(document).ready(function () {

    formSignUpElement.addEventListener('submit', function(event) {
        signUpForm.removeValidation();
        let validationResult = signUpForm.validate();
        console.log(validationResult);
        if(!validationResult.some(result => result == false)) {
            event.preventDefault();
            signUpPage.signUpUser();
        } else {
            signUpForm.setEmailFeedback(validationResult[0], "",STANDARD_EMAIL_FEEDBACK);
            signUpForm.setLoginFeedback(validationResult[1], "", STANDARD_LOGIN_FEEDBACK);

            if (validationResult[2] && validationResult[3] && !validationResult[4]) {
                signUpForm.setPasswordFeedback(false, "",  PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL);
                signUpForm.setPasswordConfirmationFeedback(false, "",  PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL);
            } else {
                signUpForm.setPasswordFeedback(validationResult[2], "", STANDARD_PASSWORD_FEEDBACK);
                signUpForm.setPasswordConfirmationFeedback(validationResult[3], "", STANDARD_PASSWORD_FEEDBACK);
            }
            event.preventDefault();
            event.stopPropagation();
        }
    });

    enteredEmailElement.addEventListener("submit", function(event) {
        signUpForm.removeEmailValidation();
    });
    enteredLoginElement.addEventListener("submit", function(event) {
        signUpForm.removeLoginValidation();
    });
    enteredPasswordElement.addEventListener("submit", function(event) {
        signUpForm.removePasswordValidation();
        signUpForm.removePasswordConfirmationValidation();
    });
    enteredPasswordConfirmElement.addEventListener("submit", function(event) {
        signUpForm.removePasswordValidation();
        signUpForm.removePasswordConfirmationValidation();
    });
});
