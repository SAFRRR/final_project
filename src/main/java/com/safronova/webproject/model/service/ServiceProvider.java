package com.safronova.webproject.model.service;

import com.safronova.webproject.model.service.impl.UserServiceImpl;
import com.safronova.webproject.model.service.impl.ValidationServiceImpl;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();
    private static final UserService userService = new UserServiceImpl();
    private static final ValidationService validationService = new ValidationServiceImpl();
    private ServiceProvider() {
    }

    public static ServiceProvider getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public ValidationService getValidationService() {
        return validationService;
    }

}

