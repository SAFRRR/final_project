package com.safronova.webproject.model.service.impl;

import com.safronova.webproject.controller22.command2222.Feedback;
import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.dao22.CustomUserDao;
import com.safronova.webproject.model.dao22.DaoProvider;
import com.safronova.webproject.model.dao22.DatabaseFeedback;
import com.safronova.webproject.model.entity2.CustomUser;
import com.safronova.webproject.model.service.ServiceProvider;
import com.safronova.webproject.model.service.UserService;
import com.safronova.webproject.model.service.ValidationService;
import com.safronova.webproject.model.util.DateHandler;
import com.safronova.webproject.model.util.PasswordEncryptor2;
import com.safronova.webproject.model.util.localization.LocaleAttribute;
import com.safronova.webproject.model.util.localization.LocaleKey;
import com.safronova.webproject.model.util.mail.MailSender;
import jakarta.xml.bind.DatatypeConverter;

import java.util.Calendar;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    @Override
    public Optional<CustomUser> findUserByEmail(String email) throws ServiceException {
        try {
            CustomUserDao userDao = DaoProvider.getInstance().getCustomUserDao();
            Optional<CustomUser> user = userDao.findByEmail(email);
            return user;
        } catch (DaoException e) {
            throw new ServiceException("Error occured when finding user by email " + email + " :" + e.getMessage(), e);
        }
    }

    @Override
    public Map<Feedback.Key, Object> signInUser(String email, String password) throws ServiceException {
        try {
            Map<Feedback.Key, Object> result = new EnumMap<>(Feedback.Key.class);
            ValidationService validationService = ServiceProvider.getInstance().getValidationService();
            validationService.validateEmailFormInput(result, email);
            validationService.validatePasswordFormInput(result, password);

            if (Boolean.TRUE.equals(result.get(Feedback.Key.EMAIL_STATUS)) && Boolean.TRUE.equals(result.get(Feedback.Key.PASSWORD_STATUS))) {
                CustomUserDao userDao = DaoProvider.getInstance().getCustomUserDao();
                Map<DatabaseFeedback.Key, Optional<String>> loginData = userDao.findUserLoginData(email);
                Optional<String> passwordDatabaseOptional = loginData.get(DatabaseFeedback.Key.PASSWORD);
                Optional<String> saltDatabaseOptional = loginData.get(DatabaseFeedback.Key.SALT);
                if (passwordDatabaseOptional.isPresent() && saltDatabaseOptional.isPresent()) {
                    String passwordHash = passwordDatabaseOptional.get();
                    String saltHex = saltDatabaseOptional.get();
                    byte[] salt = DatatypeConverter.parseHexBinary(saltHex);
                    String enteredPassword = DatatypeConverter.printHexBinary(PasswordEncryptor2.hashPassword(password, salt));
                    if (enteredPassword.equals(passwordHash)) {
                        result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.OK);
                        result.put(Feedback.Key.EMAIL_STATUS, Boolean.TRUE);
                        result.put(Feedback.Key.PASSWORD_STATUS, Boolean.TRUE);
                        result.put(Feedback.Key.EMAIL_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
                        result.put(Feedback.Key.PASSWORD_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
                    } else {
                        result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
                        result.put(Feedback.Key.EMAIL_STATUS, Boolean.FALSE);
                        result.put(Feedback.Key.PASSWORD_STATUS, Boolean.FALSE);
                        result.put(Feedback.Key.EMAIL_FEEDBACK, LocaleKey.EMAIL_PASSWORD_FEEDBACK_INVALID.getValue());
                        result.put(Feedback.Key.PASSWORD_FEEDBACK,
                                LocaleKey.EMAIL_PASSWORD_FEEDBACK_INVALID.getValue());
                    }
                } else {
                    result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
                    result.put(Feedback.Key.EMAIL_STATUS, Boolean.FALSE);
                    result.put(Feedback.Key.PASSWORD_STATUS, Boolean.FALSE);
                    result.put(Feedback.Key.EMAIL_FEEDBACK, LocaleKey.EMAIL_PASSWORD_FEEDBACK_INVALID.getValue());
                    result.put(Feedback.Key.PASSWORD_FEEDBACK, LocaleKey.EMAIL_PASSWORD_FEEDBACK_INVALID.getValue());
                }
            } else {
                result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
                result.put(Feedback.Key.EMAIL_STATUS, Boolean.FALSE);
                result.put(Feedback.Key.EMAIL_FEEDBACK, LocaleKey.EMAIL_PASSWORD_FEEDBACK_INVALID.getValue());
                result.put(Feedback.Key.PASSWORD_STATUS, Boolean.FALSE);
                result.put(Feedback.Key.PASSWORD_FEEDBACK,
                        LocaleKey.PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL.getValue());
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException("Can not login with this credentials", e);
        }
    }




    @Override
    public Map<Feedback.Key, Object> signUpUser(String email, String login, String password, String passwordRepeat, LocaleAttribute locale) throws ServiceException {
        try {
            Map<Feedback.Key, Object> result = new EnumMap<>(Feedback.Key.class);

            ValidationService validationService = ServiceProvider.getInstance().getValidationService();

            validationService.validateEmailFormInput(result, email);
            validationService.validateLoginFormInput(result, login);
            validationService.validatePasswordFormInput(result, password);
            validationService.validatePasswordConfirmationFormInput(result, passwordRepeat);

            if (Boolean.TRUE.equals(result.get(Feedback.Key.EMAIL_STATUS))
                    && Boolean.TRUE.equals(result.get(Feedback.Key.LOGIN_STATUS))
                    && Boolean.TRUE.equals(result.get(Feedback.Key.PASSWORD_STATUS))
                    && Boolean.TRUE.equals(result.get(Feedback.Key.PASSWORD_CONFIRMATION_STATUS))) {
                if (password.equals(passwordRepeat)) {
                    byte[] salt = PasswordEncryptor2.createSalt();
                    byte[] hashedPassword = PasswordEncryptor2.hashPassword(password, salt);
                    String saltHex = DatatypeConverter.printHexBinary(salt);
                    String hashedPasswordHex = DatatypeConverter.printHexBinary(hashedPassword);
                    CustomUserDao userDao = DaoProvider.getInstance().getCustomUserDao();
                    Optional<CustomUser> userOptional = userDao.findByEmail(email);
                    boolean registerResult = false;
                    if (!userOptional.isPresent()) {
                        registerResult = userDao.signUpUser(email, login, hashedPasswordHex, saltHex, "/static/images/bakery1.jpg", CustomUser.CustomRole.USER);
                        if (registerResult) {
                            String token = PasswordEncryptor2.createToken();
                            final int minutesToExpriration = 5;

                            String expirationTime = DateHandler.prepareDate(minutesToExpriration, Calendar.MINUTE);
                            userDao.addNewToken(email, token, expirationTime);
                            MailSender.sendEmail(email, token, MailSender.Function.SIGN_UP, locale);


                            result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.OK);
                            result.put(Feedback.Key.EMAIL_FEEDBACK, LocaleKey.CHECK_YOUR_EMAIL.getValue());
                            result.put(Feedback.Key.LOGIN_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
                            result.put(Feedback.Key.PASSWORD_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
                            result.put(Feedback.Key.PASSWORD_CONFIRMATION_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
                        } else{
                            result.put(Feedback.Key.EMAIL_STATUS, Boolean.FALSE);
                            result.put(Feedback.Key.LOGIN_STATUS, Boolean.FALSE);
                            result.put(Feedback.Key.PASSWORD_STATUS, Boolean.FALSE);
                            result.put(Feedback.Key.PASSWORD_CONFIRMATION_STATUS, Boolean.FALSE);
                            result.put(Feedback.Key.EMAIL_FEEDBACK, LocaleKey.INTERNAL_SERVER_ERROR.getValue());
                            result.put(Feedback.Key.LOGIN_FEEDBACK, LocaleKey.INTERNAL_SERVER_ERROR.getValue());
                            result.put(Feedback.Key.PASSWORD_FEEDBACK, LocaleKey.INTERNAL_SERVER_ERROR.getValue());
                            result.put(Feedback.Key.PASSWORD_CONFIRMATION_FEEDBACK, LocaleKey.INTERNAL_SERVER_ERROR.getValue());
                        }
                    }else {
                        result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
                        CustomUser user = userOptional.get();
                        if (user.getEmail().equals(email)) {
                            result.put(Feedback.Key.EMAIL_STATUS, Boolean.FALSE);
                            result.put(Feedback.Key.EMAIL_FEEDBACK,
                                    LocaleKey.EMAIL_FEEDBACK_INVALID_USER_EXISTS.getValue());
                        }
                        if (user.getLogin().equals(login)) {
                            result.put(Feedback.Key.LOGIN_STATUS, Boolean.FALSE);
                            result.put(Feedback.Key.LOGIN_FEEDBACK,
                                    LocaleKey.LOGIN_FEEDBACK_INVALID_USER_EXISTS.getValue());
                        }
                    }
                }else{
                    result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
                    result.put(Feedback.Key.PASSWORD_STATUS, Boolean.FALSE);
                    result.put(Feedback.Key.PASSWORD_CONFIRMATION_STATUS, Boolean.FALSE);
                    result.put(Feedback.Key.PASSWORD_FEEDBACK, LocaleKey.PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL.getValue());
                    result.put(Feedback.Key.PASSWORD_CONFIRMATION_FEEDBACK, LocaleKey.PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL.getValue());
                }
            } else {
                result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.WRONG_INPUT);
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException("Error occured when finding user register: " + e.getMessage(), e);//????
        }
    }
}



