package com.safronova.webproject.model.dao;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.model.entity.OrderDessert;

import java.util.List;

/**
 * Interface provides methods to interact with OrderDessert data from database.
 * Methods should connect to database and manipulate with data(save, edit, etc.).
 */
public interface OrderDessertDao {

    /**
     * Connects to database and add new orderDessert.
     *
     * @param orderDessert is {@link OrderDessert} object that contains all info about orderDessert.
     * @throws DaoException when problems with database connection occurs.
     */
    void saveOrderDessert(OrderDessert orderDessert) throws DaoException;

    /**
     * Connects to database and return list of orderDessert that linked to the dessert by ID.
     *
     * @param id is dessert ID
     * @return List of {@link OrderDessert} with all matching data.
     * @throws DaoException when problems with database connection occurs.
     */
    List<OrderDessert> findByDessertId(Integer id) throws DaoException;

    /**
     * Connects to database and return list of orderDessert that linked to the order by ID.
     *
     * @param id is order ID
     * @return List of {@link OrderDessert} with all matching data.
     * @throws DaoException when problems with database connection occurs.
     */
    List<OrderDessert> findByOrder(Integer id) throws DaoException;
}


