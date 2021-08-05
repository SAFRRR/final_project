package com.safronova.webproject.model.validator;

import com.safronova.webproject.model.util.RegexpPropertiesReader;

import java.util.Properties;

public class DessertValidator {
    private static final Properties properties = RegexpPropertiesReader.getProperties();
    private static final String REGEXP_DESSERT_ID = properties.getProperty("regexp.id");
    private static final String REGEXP_DESSERT_TYPE_ID = properties.getProperty("regexp.dessertTypeId");
    private static final String REGEXP_DESSERT_NAME = properties.getProperty("regexp.dessert.name");
    private static final String REGEXP_DESSERT_DESCRIPTION = properties.getProperty("regexp.dessert.description");
    private static final String REGEXP_DESSERT_PRICE = properties.getProperty("regexp.dessert.price");
    private static final String REGEXP_DESSERT_QUANTITY = properties.getProperty("regexp.dessert.quantity");
    private static final String REGEXP_FLOWER_LIGHT = properties.getProperty("regexp.flower.light");
    private static final String REGEXP_FLOWER_WATERING = properties.getProperty("regexp.flower.watering");

    public static boolean validateData(String nameDessert, String description, String price, String soil, String watering, String light) {
        if (!validateName(nameDessert)) {
            return false;
        }
        if (!validateDescription(description)) {
            return false;
        }
        if (!validatePrice(price)) {
            return false;
        }
        if (!validateSoil(soil)) {
            return false;
        }
        if (!validateWatering(watering)) {
            return false;
        }
        return validateLight(light);
    }


    public static boolean validateName(String name) {
        return isMatchFounded(name, REGEXP_DESSERT_NAME);
    }

    public static boolean validateDescription(String description) {
        return isMatchFounded(description, REGEXP_DESSERT_DESCRIPTION);
    }

    public static boolean validatePrice(String price) {
        return isMatchFounded(price, REGEXP_DESSERT_PRICE);
    }

    public static boolean validateLight(String light) {
        return isMatchFounded(light, REGEXP_FLOWER_LIGHT);
    }

    public static boolean validateWatering(String watering) {
        return isMatchFounded(watering, REGEXP_FLOWER_WATERING);
    }

    public static boolean validateDessertTypeId(String dessertTypeId) {
        return isMatchFounded(dessertTypeId, REGEXP_DESSERT_TYPE_ID);
    }

    public static boolean validateId(String dessertId) {
        return isMatchFounded(dessertId, REGEXP_DESSERT_ID);
    }

    public static boolean validateQuantity(String count) {
        return isMatchFounded(count, REGEXP_DESSERT_QUANTITY);
    }

    public static boolean isMatchFounded(String text, String regex) {
        return text != null && text.matches(regex);
    }

    public static boolean validateSoil(String soil) {
        try {
            Soil.valueOf(soil);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
