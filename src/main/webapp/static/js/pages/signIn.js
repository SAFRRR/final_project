import { SignInForm } from "../modules/user.js"
import { removeParameterFromUrl, changeLocationIfUndefined } from "../modules/util.js"

let signInForm;
let formSignIn;
let email;
let password;
let emailInvalidFeedback;
let passwordInvalidFeedback;


$(function(){
    removeParameterFromUrl("token");
});

$(function() {
    email = document.getElementById("form-signIn-email");
    password = document.getElementById("form-signIn-password");
    formSignIn = document.getElementById("form-signIn");
    emailInvalidFeedback = document.getElementById("form-signIn-email-invalid-feedback");
    passwordInvalidFeedback =  document.getElementById("form-signIn-password-invalid-feedback");
    signInForm = new SignInForm(formSignIn, email, null, emailInvalidFeedback, password, null, passwordInvalidFeedback);
});


function signInUser() {
    let data = {}
    data[EMAIL] = email.value
    data[PASSWORD] = password.value
    let url = "controller?command=signIn_user_command";
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            signInForm.setFeedback(jqXHR.responseJSON[EMAIL_STATUS],
                jqXHR.responseJSON[EMAIL_FEEDBACK],
                jqXHR.responseJSON[EMAIL_FEEDBACK],
                jqXHR.responseJSON[PASSWORD_STATUS],
                jqXHR.responseJSON[PASSWORD_FEEDBACK],
                jqXHR.responseJSON[PASSWORD_FEEDBACK]
            );
            url = "controller?command=go_to_home_page_command";
            location.assign(url);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            changeLocationIfUndefined(jqXHR);
            signInForm.removeValidation();
            signInForm.setFeedback(jqXHR.responseJSON[EMAIL_STATUS],
                jqXHR.responseJSON[EMAIL_FEEDBACK],
                jqXHR.responseJSON[EMAIL_FEEDBACK],
                jqXHR.responseJSON[PASSWORD_STATUS],
                jqXHR.responseJSON[PASSWORD_FEEDBACK],
                jqXHR.responseJSON[PASSWORD_FEEDBACK]
            );
        }
    });
}

$(document).ready(function () {
    formSignIn.addEventListener('submit', function(event) {
        signInForm.removeValidation();
        let validationResult = signInForm.validate();
        if (!validationResult.some(result => result == false)) {
            event.preventDefault();
            signInPage.signInUser();
        } else {
            signInForm.setFeedback(validationResult[0], "", STANDARD_EMAIL_FEEDBACK, validationResult[1], "", STANDARD_PASSWORD_FEEDBACK);
            event.preventDefault();
            event.stopPropagation();
        }
    });
    email.addEventListener("input", function(event) {
        signInForm.removeEmailValidation();
    });
    password.addEventListener("input", function(event) {
        signInForm.removePasswordValidation();
    });
});



function openSignUpPage() {
    let url = "controller?command=go_to_signUp_page_command";
    location.assign(url);
}

function openHomePage() {
    let url = "controller?command=go_to_home_page_command";
    location.assign(url);
}

export const signInPage = {
    signInUser: signInUser,
    openSignUpPage: openSignUpPage,
    openHomePage: openHomePage
};
window.signInPage = signInPage;



