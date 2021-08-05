package com.safronova.webproject.controller22.listener;

import com.safronova.webproject.controller22.EnumValue;
import com.safronova.webproject.controller22.command2222.SessionAttribute;
import com.safronova.webproject.model.util.localization.LocaleAttribute;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSessionListener.super.sessionCreated(se);
        setSessionVariables(se.getSession());
    }
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSessionListener.super.sessionDestroyed(se);
    }
    private void setSessionVariables(HttpSession session){
        session.setAttribute(SessionAttribute.CURRENT_LOCALIZATION_NAME.name(), LocaleAttribute.LOCALIZATION_EN.getValue());
        session.setAttribute(SessionAttribute.TEXT.name(), LocaleAttribute.LOCALIZATION_EN.getResourceBundle());
        session.setAttribute(SessionAttribute.CURRENT_LOCALE.name(), LocaleAttribute.LOCALIZATION_EN);
        session.setAttribute(SessionAttribute.CURRENT_LOCALE_ABBREVIATION.name(), LocaleAttribute.LOCALIZATION_EN.getLocale().toString());
    }
    private <T extends Enum<?>> void setEnumSessionVariables(HttpSession session, T[] enumValues) {
        for(T enumValue: enumValues) {
            String enumName = enumValue.name();
            EnumValue enumValueInterface = (EnumValue) enumValue;
            session.setAttribute(enumName, enumValueInterface.getValue());
        }
    }
}




