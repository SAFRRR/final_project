package com.safronova.webproject.controller.command.impl.go;

import com.safronova.webproject.controller.command.PagePath;
import com.safronova.webproject.controller.command.RequestAttribute;
import com.safronova.webproject.controller.command.Router;
import com.safronova.webproject.controller.command.Router.RouterType;
import com.safronova.webproject.controller.command.impl.user.UserCommand;
import com.safronova.webproject.model.util.RegexpPropertiesReader;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Properties;

public class GoToProfilePageCommand extends UserCommand {
    private static final Properties properties = RegexpPropertiesReader.getProperties();
    private static final String REGEXP_USERNAME = properties.getProperty("regexp.username");
    private static final String REGEXP_PASSWORD = properties.getProperty("regexp.password");
    private static final String REGEXP_USER_FIO = properties.getProperty("regexp.user_fio");
    private static final String REGEXP_PHONE_NUMBER = properties.getProperty("regexp.phone_number");

    @Override
    public Router handle(HttpServletRequest request) {
        Router router;
        request.setAttribute(RequestAttribute.REGEXP_USERNAME, REGEXP_USERNAME);
        request.setAttribute(RequestAttribute.REGEXP_FIO, REGEXP_USER_FIO);
        request.setAttribute(RequestAttribute.REGEXP_PHONE, REGEXP_PHONE_NUMBER);
        request.setAttribute(RequestAttribute.REGEXP_PASSWORD, REGEXP_PASSWORD);
        router = new Router(PagePath.PROFILE_PAGE, RouterType.FORWARD);
        return router;
    }
}

