package com.safronova.webproject.model.dao22;

import com.safronova.webproject.controller22.EnumValue;

public class DatabaseFeedback {
    public enum Key implements EnumValue {
        EMPTY_MESSAGE(""), PASSWORD("password"), SALT("salt");

        private String value;

        private Key(String value) {
            this.value = value;
        }

        @Override
        public String getValue() {
            return value;
        }
    }
}
