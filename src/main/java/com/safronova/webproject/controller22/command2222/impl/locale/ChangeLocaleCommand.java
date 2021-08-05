package com.safronova.webproject.controller22.command2222.impl.locale;

import com.safronova.webproject.controller22.command2222.*;
import com.safronova.webproject.model.util.localization.LocaleAttribute;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {
    @Override
//    public Router execute(HttpServletRequest request) {
//        String requestedLocale = request.getParameter(RequestParameter.LOCALE.getValue());
//
//        LocaleAttribute locale = LocaleAttribute.fromString(requestedLocale);
//
//        HttpSession session = request.getSession();
//        request.getSession().setAttribute(SessionAttribute.CURRENT_LOCALIZATION_NAME.name(), locale.getValue());
//        request.getSession().setAttribute(SessionAttribute.TEXT.name(), locale.getResourceBundle());
//        request.getSession().setAttribute(SessionAttribute.CURRENT_LOCALE.name(), locale);
//        session.setAttribute(SessionAttribute.CURRENT_LOCALE_ABBREVIATION.name(), locale.getLocale().toString());
//
//        Router router = new Router(null, Boolean.TRUE.toString(), Router.RouterType.AJAX_RESPONSE);
//        return router;
//
//    }
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        String requestedLocale = request.getParameter(RequestParameter.LOCALE.getValue());

        LocaleAttribute locale = LocaleAttribute.fromString(requestedLocale);

        HttpSession session = request.getSession();
        request.getSession().setAttribute(SessionAttribute.CURRENT_LOCALIZATION_NAME.name(), locale.getValue());
        request.getSession().setAttribute(SessionAttribute.TEXT.name(), locale.getResourceBundle());
        request.getSession().setAttribute(SessionAttribute.CURRENT_LOCALE.name(), locale);
        session.setAttribute(SessionAttribute.CURRENT_LOCALE_ABBREVIATION.name(), locale.getLocale().toString());

        Router router = new Router(null, Boolean.TRUE.toString(), Router.RouterType.AJAX_RESPONSE);
        return router;
    }
}
