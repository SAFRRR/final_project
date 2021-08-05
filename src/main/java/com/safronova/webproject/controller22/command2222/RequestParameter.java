package com.safronova.webproject.controller22.command2222;

import com.safronova.webproject.controller22.EnumValue;

public enum RequestParameter implements EnumValue {
    CONTROLLER("controller"),
    COMMAND("command"),
    LOCALE("locale"),
    PAGE("page"),
    EMAIL("email"),
    NEW_EMAIL("new_email"),
    LOGIN("login"),
    NEW_LOGIN("new_login"),
    USER_ID("user-id"),
    PASSWORD("password"),
    TOKEN("token"),
    PASSWORD_CONFIRM("password_confirm");






    private String value;

    private RequestParameter(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

}

