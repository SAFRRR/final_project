package com.safronova.webproject.model.util.localization;

import com.safronova.webproject.controller22.EnumValue;

import java.util.Locale;
import java.util.ResourceBundle;

public enum LocaleAttribute implements EnumValue {
    LOCALIZATION_RU("Русский", new LocalizationReader(new Locale("ru")).getResourceBundle()),
    LOCALIZATION_EN("English", new LocalizationReader(new Locale("en")).getResourceBundle());

    private String value;
    private Locale locale;
    private ResourceBundle resourceBundle;


    private LocaleAttribute(String value, ResourceBundle resourceBundle) {
        this.value = value;
        this.locale = resourceBundle.getLocale();
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String getValue() {
        return value;
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public Locale getLocale() {
        return locale;
    }

    public String getLocalizedMessage(String key) {
        return resourceBundle.getString(key);
    }

    public static LocaleAttribute fromString(String requestedLocale) {
        LocaleAttribute result = LOCALIZATION_EN;
        for (LocaleAttribute locale : LocaleAttribute.values()) {
            if (locale.getValue().equals(requestedLocale)) {
                result = locale;
            }
        }
        return result;
    }

}
