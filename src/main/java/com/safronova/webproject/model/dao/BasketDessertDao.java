package com.safronova.webproject.model.dao;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.model.entity.BasketDessert;

import java.math.BigDecimal;
import java.util.List;

/**
 * Interface provides methods to interact with BasketDessert data from database.
 * Methods should connect to database and manipulate with data(save, edit, etc.).
 */
public interface BasketDessertDao {

    /**
     * Connects to database and add new dessert to basket.
     *
     * @param id is basket ID value.
     * @param dessertId is dessert ID value
     * @param count is amount of the desserts in basket
     * @param subTotal is sub total of user's basket, calculated as count of the desserts multiplied by its price
     * @throws DaoException when problems with database connection occurs.
     */
    void addItemToBasket(Integer id, Integer dessertId, Integer count, BigDecimal subTotal) throws DaoException;

    /**
     *  Connects to database and returns all info about basket by its ID.
     *
     * @param id is basket ID value.
     * @return List of {@link BasketDessert} with all matching data.
     * @throws DaoException when problems with database connection occurs.
     */
    List<BasketDessert> findByBasketId(Integer id) throws DaoException;

    /**
     * Connects to database and returns all info about basket by dessert ID.
     *
     * @param id is dessert ID value.
     * @return List of {@link BasketDessert} with all matching data.
     * @throws DaoException when problems with database connection occurs.
     */
    List<BasketDessert> findByDessertId(Integer id) throws DaoException;

    /**
     * Connects to database and updates sub total of basket.
     *
     * @param basketDessert is {@link BasketDessert} object that contains all info about basket dessert for update.
     * @throws DaoException when problems with database connection occurs.
     */
    void updateSubTotal(BasketDessert basketDessert) throws DaoException;

    /**
     * Connects to database and updates count of desserts in basket.
     *
     * @param basketDessert is {@link BasketDessert} object that contains all info about basket dessert for update.
     * @throws DaoException when problems with database connection occurs.
     */
    void updateCount(BasketDessert basketDessert) throws DaoException;

    /**
     * Connects to database and returns all info about basket dessert by ID.
     *
     * @param id is basket dessert ID value.
     * @return {@link BasketDessert} if basket dessert data found, null if not.
     * @throws DaoException when problems with database connection occurs.
     */
    BasketDessert findById(Integer id) throws DaoException;

    /**
     * Connects to database and delete basket dessert by ID.
     *
     * @param id is basket dessert ID value
     * @throws DaoException when problems with database connection occurs.
     */
    void deleteBasketDessert(Integer id) throws DaoException;
}

