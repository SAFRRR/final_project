package com.safronova.webproject.controller.command.impl;

import com.safronova.webproject.controller.command.Command;
import com.safronova.webproject.controller.command.RequestAttribute;
import com.safronova.webproject.controller.command.RequestParameter;
import com.safronova.webproject.controller.command.Router;
import com.safronova.webproject.controller.command.Router.RouterType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        final HttpSession SESSION = request.getSession();
        final String LOCALE = request.getParameter(RequestParameter.LOCALE);
        final String PREVIOUS_REQUEST = (String) SESSION.getAttribute(RequestAttribute.PREV_REQUEST);
        SESSION.setAttribute(RequestAttribute.LOCALE, LOCALE);
        Router router = new Router(PREVIOUS_REQUEST, RouterType.REDIRECT);
        return router;
    }
}
