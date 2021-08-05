package com.safronova.webproject.controller22.command2222;

import com.safronova.webproject.controller22.EnumValue;

public enum PagePath implements EnumValue {
    HOME_PAGE("/WEB-INF/pages/home_page.jsp"),
    SIGNIN_PAGE("/WEB-INF/pages/signIn_page.jsp"),
    SIGNUP_PAGE("/WEB-INF/pages/signUp_page.jsp"),
    EMAIL_SEND_PAGE("/WEB-INF/pages/common/shared/email_send.jsp"),

    //error page
    ERROR_400_PAGE("/WEB-INF/pages/errors/400.jsp"),
    ERROR_403_PAGE("/WEB-INF/pages/errors/403.jsp"),
    ERROR_404_PAGE("/WEB-INF/pages/errors/404.jsp"),
    ERROR_500_PAGE("/WEB-INF/pages/errors/500.jsp");

    private String value;

    private PagePath(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}




