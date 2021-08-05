package com.safronova.webproject.model.validator;

import com.safronova.webproject.model.entity.SignUpData;
import com.safronova.webproject.model.entity.User;
import com.safronova.webproject.model.util.RegexpPropertiesReader;

import java.util.Properties;

public class UserValiodator {
    private static final Properties properties = RegexpPropertiesReader.getProperties();
    private static final String REGEXP_PHONE_NUM = properties.getProperty("regexp.phone_number");
    private static final String REGEXP_USER_FIO = properties.getProperty("regexp.user_fio");
    private static final String REGEXP_USERNAME = properties.getProperty("regexp.username");
    private static final String REGEXP_EMAIL = properties.getProperty("regexp.email");
    private static final String REGEXP_PASSWORD = properties.getProperty("regexp.password");


    public static boolean validate(SignUpData signUpData) {
        return validateData(signUpData.getUsername(), signUpData.getFirstName(), signUpData.getLastName(), signUpData.getPhoneNumber(), signUpData.getEmail());
    }

    public static boolean validate(User user) {
        return validateData(user.getUsername(), user.getFirstName(), user.getLastName(), user.getPhone(), user.getEmail());
    }

    private static boolean validateData(String username, String firstName, String lastName, String phoneNumber, String email) {
        return validateUsername(username) && validateFIO(firstName) && validateFIO(lastName) && validatePhoneNumber(phoneNumber) && validateEmail(email);
    }

    private static boolean validateData(String username, String firstName, String lastName, String phoneNumber, String email) {
        return validateUsername(username) && validateFIO(firstName) && validateFIO(lastName) && validatePhoneNumber(phoneNumber) && validateEmail(email);
    }

    public static boolean validateUsername(String username) {
        return isMatchFounded(username, REGEXP_USERNAME);
    }

    public static boolean validateFIO(String fio) {
        return isMatchFounded(fio, REGEXP_USER_FIO);
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        return isMatchFounded(phoneNumber, REGEXP_PHONE_NUM);
    }

    public static boolean validateEmail(String email) {
        return isMatchFounded(email, REGEXP_EMAIL);
    }

    public static boolean validatePassword(String password) {
        return isMatchFounded(password, REGEXP_PASSWORD);
    }

    private static boolean isMatchFounded(String text, String regex) {
        return text != null && text.matches(regex);
    }
}
