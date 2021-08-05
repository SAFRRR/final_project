package com.safronova.webproject.model.dao;


import com.safronova.webproject.model.dao.impl.BasketDaoImpl;
import com.safronova.webproject.model.dao.impl.OrderDaoImpl;
import com.safronova.webproject.model.dao.impl.StorageDaoImpl;
import com.safronova.webproject.model.dao.impl.UserDaoImpl;
import com.safronova.webproject.model.entity.BasketDessert;
import com.safronova.webproject.model.entity.Dessert;
import com.safronova.webproject.model.entity.OrderDessert;

public class DaoProvider {
    private static final DaoProvider instance = new DaoProvider();

    private static final UserDao userDao = UserDaoImpl.getInstance();

    private static final DessertDao dessertDao = DessertDaoImpl.getInstance();

    private static final DessertTypeDao dessertTypeDao = DessertTypeDaoImpl.getInstance();

    private static final StorageDao storageDao = StorageDaoImpl.getInstance();

    private static final BasketDao basketDao = BasketDaoImpl.getInstance();

    private static final OrderDao orderDao = OrderDaoImpl.getInstance();

    private static final BasketDessertDao basketDessertDao = BasketFlowerDaoImpl.getInstance();

    private static final OrderDessertDao orderDessertDao = OrderFlowerDaoImpl.getInstance();

    private DaoProvider() {}

    public static DaoProvider getInstance() {
        return instance;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public static DessertDao getDessertDao() {
        return dessertDao;
    }

    public static DessertTypeDao getDessertTypeDao() {
        return dessertTypeDao;
    }

    public static StorageDao getStorageDao() {
        return storageDao;
    }

    public static BasketDao getBasketDao() {
        return basketDao;
    }

    public static BasketDessertDao getBasketDessertDao() { return basketDessertDao; }

    public static OrderDao getOrderDao() {
        return orderDao;
    }

    public static OrderDessertDao getOrderDessertDao() { return orderDessertDao; }
}
