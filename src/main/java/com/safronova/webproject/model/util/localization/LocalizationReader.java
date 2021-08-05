package com.safronova.webproject.model.util.localization;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LocalizationReader {
    private static final Logger logger = LogManager.getLogger();
    private ResourceBundle resourceBundle;

    public LocalizationReader(Locale locale) {
        try {
            resourceBundle = ResourceBundle.getBundle("locale", locale);
        } catch (MissingResourceException e) {
            logger.fatal( "MissingResourceException: {} {}", e.getMessage(), e.getStackTrace());
            throw new ExceptionInInitializerError("MissingResourceException: " + e);
        }
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }
}
