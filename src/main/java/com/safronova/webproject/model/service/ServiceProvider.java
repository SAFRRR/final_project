package com.safronova.webproject.model.service;

import com.safronova.webproject.model.dao.*;
import com.safronova.webproject.model.dao.impl.UserDaoImpl;
import com.safronova.webproject.model.service.impl.*;

/**
 * Factory class that provides implementations of Service interfaces.
 */
public class ServiceProvider {
    /**
     * Instance of the class
     */
    private static final ServiceProvider instance = new ServiceProvider();

    /**
     * An object of {@link UserServiceImpl}
     */
    private final UserService userService = new UserServiceImpl();

    /**
     * An object of {@link DessertServiceImpl}
     */
    private final DessertService dessertService = new DessertServiceImpl();

    /**
     * An object of {@link DessertTypeServiceImpl}
     */
    private final DessertTypeService dessertTypeService = new DessertTypeServiceImpl();

    /**
     * An object of {@link StorageServiceImpl}
     */
    private final StorageService storageService = new StorageServiceImpl();

    /**
     * An object of {@link BasketServiceImpl}
     */
    private final BasketService basketService = new BasketServiceImpl();

    /**
     * An object of {@link OrderServiceImpl}
     */
    private final OrderService orderService = new OrderServiceImpl();

    /**
     * An object of {@link BasketDessertServiceImpl}
     */
    private final BasketDessertService basketDessertService = new BasketDessertServiceImpl();

    /**
     * Private constructor without parameters
     */
    private ServiceProvider() {}

    /**
     * Returns the instance of the class
     *
     * @return Object of {@link ServiceProvider}
     */
    public static ServiceProvider getInstance() {
        return instance;
    }

    /**
     * Returns field of {@link UserService} object
     *
     * @return {@link UserService} object
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Returns field of {@link DessertService} object
     *
     * @return {@link DessertService} object
     */
    public DessertService getDessertService() {
        return dessertService;
    }

    /**
     * Returns field of {@link DessertTypeService} object
     *
     * @return {@link DessertTypeService} object
     */
    public DessertTypeService getDessertTypeService() {
        return dessertTypeService;
    }

    /**
     * Returns field of {@link StorageService} object
     *
     * @return {@link StorageService} object
     */
    public StorageService getStorageService() {
        return storageService;
    }

    /**
     * Returns field of {@link BasketService} object
     *
     * @return {@link BasketService} object
     */
    public BasketService getBasketService() {
        return basketService;
    }

    /**
     * Returns field of {@link BasketDessertService} object
     *
     * @return {@link BasketDessertService} object
     */
    public BasketDessertService getBasketDessertService() {
        return basketDessertService;
    }

    /**
     * Returns field of {@link OrderService} object
     *
     * @return {@link OrderService} object
     */
    public OrderService getOrderService() {
        return orderService;
    }
}
