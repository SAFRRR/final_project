package com.safronova.webproject.controller22.command2222;

import com.safronova.webproject.controller22.EnumValue;

public class Feedback {
    public enum Key implements EnumValue {
        EMPTY_MESSAGE(""),
        RESPONSE_CODE("response_code"),
        EMAIL_STATUS("email_status"),
        EMAIL_FEEDBACK("email_feedback"),
        LOGIN_STATUS("login_status"),
        LOGIN_FEEDBACK("login_feedback"),
        PASSWORD_STATUS("password_status"),
        PASSWORD_FEEDBACK("password_feedback"),
        PASSWORD_CONFIRMATION_STATUS("password_confirmation_status"),
        PASSWORD_CONFIRMATION_FEEDBACK("password_confirmation_feedback"),
        LOGOUT_STATUS("logout_status");

        private String value;

        private Key(String value) {
            this.value = value;
        }

        @Override
        public String getValue() {

            return value;
        }
    }

    public enum Code {
        OK(200),
        WRONG_INPUT(400),
        NOT_AUTHORIZED(403),
        NOT_FOUND(404),
        INTERNAL_SERVER_ERROR(500);

        private Integer statusCode;

        private Code(Integer statusCode) {

            this.statusCode = statusCode;
        }

        public Integer getStatusCode() {

            return statusCode;
        }
    }
}
