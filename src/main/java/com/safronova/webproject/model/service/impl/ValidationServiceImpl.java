package com.safronova.webproject.model.service.impl;

import com.safronova.webproject.controller22.command2222.Feedback;
import com.safronova.webproject.model.entity2.FormPattern;
import com.safronova.webproject.model.service.ValidationService;
import com.safronova.webproject.model.util.localization.LocaleKey;

import java.util.Map;
import java.util.regex.Pattern;

public class ValidationServiceImpl implements ValidationService {
    @Override
    public void validateLoginFormInput(Map<Feedback.Key, Object> result, String userLogin) {
        if (validateLogin(userLogin)) {
            result.put(Feedback.Key.LOGIN_STATUS, Boolean.TRUE);
            result.put(Feedback.Key.LOGIN_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
        } else {
            result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
            result.put(Feedback.Key.LOGIN_STATUS, Boolean.FALSE);
            result.put(Feedback.Key.LOGIN_FEEDBACK, LocaleKey.LOGIN_FEEDBACK_INVALID.getValue());
        }
    }

    private static boolean validateLogin(String login) {
        return login != null && Pattern.matches(FormPattern.VALID_LOGIN.getValue(), login);
    }

    @Override
    public void validateEmailFormInput(Map<Feedback.Key, Object> result, String email) {
        if (validateEmail(email)) {
            result.put(Feedback.Key.EMAIL_STATUS, Boolean.TRUE);
            result.put(Feedback.Key.EMAIL_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
        } else {
            result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
            result.put(Feedback.Key.EMAIL_STATUS, Boolean.FALSE);
            result.put(Feedback.Key.EMAIL_FEEDBACK, LocaleKey.EMAIL_FEEDBACK_INVALID.getValue());
        }
    }

    private static boolean validateEmail(String email) {
        return email != null && Pattern.matches(FormPattern.VALID_EMAIL.getValue(), email);
    }

    @Override
    public void validatePasswordFormInput(Map<Feedback.Key, Object> result, String password) {
        if (validatePassword(password)) {
            result.put(Feedback.Key.PASSWORD_STATUS, Boolean.TRUE);
            result.put(Feedback.Key.PASSWORD_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
        } else {
            result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
            result.put(Feedback.Key.PASSWORD_STATUS, Boolean.FALSE);
            result.put(Feedback.Key.PASSWORD_FEEDBACK, LocaleKey.PASSWORD_FEEDBACK_INVALID.getValue());
        }
    }

    @Override
    public void validatePasswordConfirmationFormInput(Map<Feedback.Key, Object> result, String password) {
        if (validatePassword(password)) {
            result.put(Feedback.Key.PASSWORD_CONFIRMATION_STATUS, Boolean.TRUE);
            result.put(Feedback.Key.PASSWORD_CONFIRMATION_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
        } else {
            result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
            result.put(Feedback.Key.PASSWORD_CONFIRMATION_STATUS, Boolean.FALSE);
            result.put(Feedback.Key.PASSWORD_CONFIRMATION_FEEDBACK,
                    LocaleKey.PASSWORD_CONFIRMATION_FEEDBACK_INVALID.getValue());
        }
    }

    private static boolean validatePassword(String password) {
        return password != null && Pattern.matches(FormPattern.VALID_PASSWORD.getValue(), password);
    }

    @Override
    public void validatePasswordEquality(Map<Feedback.Key, Object> result, String password, String passwordConfirmation) {
        if (password.equals(passwordConfirmation)) {
            result.put(Feedback.Key.PASSWORD_STATUS, Boolean.TRUE);
            result.put(Feedback.Key.PASSWORD_CONFIRMATION_STATUS, Boolean.TRUE);
            result.put(Feedback.Key.PASSWORD_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
            result.put(Feedback.Key.PASSWORD_CONFIRMATION_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
        } else {
            result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
            result.put(Feedback.Key.PASSWORD_STATUS, Boolean.FALSE);
            result.put(Feedback.Key.PASSWORD_CONFIRMATION_STATUS, Boolean.FALSE);
            result.put(Feedback.Key.PASSWORD_FEEDBACK, LocaleKey.PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL.getValue());
            result.put(Feedback.Key.PASSWORD_CONFIRMATION_FEEDBACK, LocaleKey.PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL.getValue());
        }
    }

}
