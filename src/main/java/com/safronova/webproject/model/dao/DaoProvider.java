package com.safronova.webproject.model.dao;


import com.safronova.webproject.model.dao.impl.*;

/**
 * Factory class that provides implementations of DAO interfaces.
 */
public class DaoProvider {

    /**
     * Instance of the class
     */
    private static final DaoProvider instance = new DaoProvider();

    /**
     * An object of {@link UserDaoImpl}
     */
    private static final UserDao userDao = UserDaoImpl.getInstance();

    /**
     * An object of {@link DessertDaoImpl}
     */
    private static final DessertDao dessertDao = DessertDaoImpl.getInstance();

    /**
     * An object of {@link DessertTypeDaoImpl}
     */
    private static final DessertTypeDao dessertTypeDao = DessertTypeDaoImpl.getInstance();

    /**
     * An object of {@link StorageDaoImpl}
     */
    private static final StorageDao storageDao = StorageDaoImpl.getInstance();

    /**
     * An object of {@link BasketDaoImpl}
     */
    private static final BasketDao basketDao = BasketDaoImpl.getInstance();

    /**
     * An object of {@link OrderDaoImpl}
     */
    private static final OrderDao orderDao = OrderDaoImpl.getInstance();

    /**
     * An object of {@link BasketDessertDaoImpl}
     */
    private static final BasketDessertDao basketDessertDao = BasketDessertDaoImpl.getInstance();

    /**
     * An object of {@link OrderDessertDaoImpl}
     */
    private static final OrderDessertDao orderDessertDao = OrderDessertDaoImpl.getInstance();

    /**
     * Private constructor without parameters
     */
    private DaoProvider() {}

    /**
     * Returns the instance of the class
     *
     * @return Object of {@link DaoProvider}
     */
    public static DaoProvider getInstance() {
        return instance;
    }

    /**
     * Returns field of {@link UserDao} object
     *
     * @return {@link UserDao} object
     */
    public UserDao getUserDao() {
        return userDao;
    }

    /**
     * Returns field of {@link DessertDao} object
     *
     * @return {@link DessertDao} object
     */
    public static DessertDao getDessertDao() {
        return dessertDao;
    }

    /**
     * Returns field of {@link DessertTypeDao} object
     *
     * @return {@link DessertTypeDao} object
     */
    public static DessertTypeDao getDessertTypeDao() {
        return dessertTypeDao;
    }

    /**
     * Returns field of {@link StorageDao} object
     *
     * @return {@link StorageDao} object
     */
    public static StorageDao getStorageDao() {
        return storageDao;
    }

    /**
     * Returns field of {@link BasketDao} object
     *
     * @return {@link BasketDao} object
     */
    public static BasketDao getBasketDao() {
        return basketDao;
    }

    /**
     * Returns field of {@link BasketDessertDao} object
     *
     * @return {@link BasketDessertDao} object
     */
    public static BasketDessertDao getBasketDessertDao() {
        return basketDessertDao;
    }

    /**
     * Returns field of {@link OrderDao} object
     *
     * @return {@link OrderDao} object
     */
    public static OrderDao getOrderDao() {
        return orderDao;
    }

    /**
     * Method returns field of {@link OrderDessertDao} object
     *
     * @return {@link OrderDessertDao} object
     */
    public static OrderDessertDao getOrderDessertDao() {
        return orderDessertDao;
    }
}
