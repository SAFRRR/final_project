package com.safronova.webproject.model.service;

import com.safronova.webproject.model.service.impl.*;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();

    private final UserService userService = new UserServiceImpl();
    private final DessertService dessertService = new DessertServiceImpl();
    private final DessertTypeService dessertTypeService = new DessertTypeServiceImpl();
    private final StorageService storageService = new StorageServiceImpl();
    private final BasketService basketService = new BasketServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
    private final BasketDessertService basketDessertService = new BasketDessertServiceImpl();

    private ServiceProvider() {}

    public static ServiceProvider getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public DessertService getDessertService() {
        return dessertService;
    }

    public DessertTypeService getDessertTypeService() {
        return dessertTypeService;
    }

    public StorageService getStorageService() {
        return storageService;
    }

    public BasketService getBasketService() {
        return basketService;
    }

    public BasketDessertService getBasketDessertService() {
        return basketDessertService;
    }

    public OrderService getOrderService() {
        return orderService;
    }
}
