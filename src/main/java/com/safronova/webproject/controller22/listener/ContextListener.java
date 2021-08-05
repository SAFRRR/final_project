package com.safronova.webproject.controller22.listener;

import com.safronova.webproject.controller22.EnumValue;
import com.safronova.webproject.controller22.command2222.CommandDefiner;
import com.safronova.webproject.controller22.command2222.Feedback;
import com.safronova.webproject.controller22.command2222.PagePath;
import com.safronova.webproject.controller22.command2222.RequestParameter;
import com.safronova.webproject.model.entity2.FormPattern;
import com.safronova.webproject.model.pool.ConnectionPool;
import com.safronova.webproject.model.util.localization.LocaleAttribute;
import com.safronova.webproject.model.util.localization.LocaleKey;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContextListener.super.contextInitialized(servletContextEvent);
        ConnectionPool.getInstance();
//        DaoProvider.getInstance();
//        EmailPropertiesReader.getPropeties();
//        ServiceProvider.getInstance();
        setServletContextVariables(servletContextEvent.getServletContext());
        LocaleAttribute.LOCALIZATION_EN.getResourceBundle();
//        LocaleAttribute.LOCALIZATION_RU.getResourceBundle();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContextListener.super.contextDestroyed(servletContextEvent);
        ConnectionPool.getInstance().destroyPool();
    }

    private void setServletContextVariables(ServletContext servletContext) {
        setEnumApplicationVariables(servletContext, Feedback.Key.values());
        setEnumApplicationVariables(servletContext, CommandDefiner.values());
        setEnumApplicationVariables(servletContext, PagePath.values());
        setEnumApplicationVariables(servletContext, RequestParameter.values());
//        setEnumApplicationVariables(servletContext, Role.values());
        setEnumApplicationVariables(servletContext, FormPattern.values());
        setEnumApplicationVariables(servletContext, LocaleAttribute.values());
        setEnumApplicationVariables(servletContext, LocaleKey.values());
//        setEnumApplicationVariables(servletContext, DeploymentPropertiesReader.Deployment.values());
    }




    private <T extends Enum<?>> void setEnumApplicationVariables(ServletContext  servletContext, T[] enumValues) {
        for(T enumValue: enumValues) {
            String enumName = enumValue.name();
            EnumValue enumValueInterface = (EnumValue) enumValue;
            servletContext.setAttribute(enumName, enumValueInterface.getValue());
        }
    }


}
