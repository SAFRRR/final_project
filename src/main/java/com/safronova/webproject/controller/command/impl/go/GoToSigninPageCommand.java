package com.safronova.webproject.controller.command.impl.go;

import com.safronova.webproject.controller.command.Command;
import com.safronova.webproject.controller.command.PagePath;
import com.safronova.webproject.controller.command.RequestAttribute;
import com.safronova.webproject.controller.command.Router;
import com.safronova.webproject.controller.command.Router.RouterType;
import com.safronova.webproject.model.util.RegexpPropertiesReader;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Properties;

public class GoToSigninPageCommand implements Command {
    private static final Properties properties = RegexpPropertiesReader.getProperties();
    private static final String REGEXP_PASSWORD = properties.getProperty("regexp.password");
    private static final String REGEXP_EMAIL = properties.getProperty("regexp.email");

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        request.setAttribute(RequestAttribute.REGEXP_PASSWORD, REGEXP_PASSWORD);
        request.setAttribute(RequestAttribute.REGEXP_EMAIL, REGEXP_EMAIL);
        request.setAttribute(RequestAttribute.ACTIVE_LOGIN, true);
        router = new Router(PagePath.SIGNIN_PAGE, RouterType.FORWARD);
        return router;
    }
}
