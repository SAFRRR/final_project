package com.safronova.webproject.model.service;

import com.safronova.webproject.controller22.command2222.Feedback;
import com.safronova.webproject.exception.ServiceException;

import java.util.Map;

public interface ValidationService {
    void validateLoginFormInput(Map<Feedback.Key, Object> result, String login);


    void validateEmailFormInput(Map<Feedback.Key, Object> result, String email);

    void validatePasswordFormInput(Map<Feedback.Key, Object> result, String password);


    void validatePasswordConfirmationFormInput(Map<Feedback.Key, Object> result, String password);

    void validatePasswordEquality(Map<Feedback.Key, Object> result, String password, String passwordConfirmation) throws ServiceException;
}



