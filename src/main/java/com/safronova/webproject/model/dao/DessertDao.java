package com.safronova.webproject.model.dao;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.model.entity.Dessert;

import java.util.List;

/**
 * Interface provides methods to interact with Dessert data from database.
 * Methods should connect to database and manipulate with data(save, edit, etc.).
 */
public interface DessertDao {

    /**
     * Connects to database and returns list of all desserts.
     *
     * @return List of {@link Dessert} with all desserts.
     * @throws DaoException when problems with database connection occurs.
     */
    List<Dessert> findAll() throws DaoException;

    /**
     * Connects to database and returns all info about dessert by its category.
     *
     * @param category is text that contains category of dessert.
     * @return List of {@link Dessert} with all matching desserts.
     * @throws DaoException when problems with database connection occurs.
     */
    List<Dessert> findByCategory(String category) throws DaoException;

    /**
     * Connects to database, find dessert by ID and returns {@link ResultCode} object as result.
     *
     * @param name is dessert NAME value.
     * @return {@link ResultCode} enum, that shows the result of the method execution.
     * @throws DaoException when problems with database connection occurs.
     */
    ResultCode findDessertByName(String name) throws DaoException;

    /**
     * Connects to database and returns all info about dessert by ID.
     *
     * @param dessertId is dessert ID value.
     * @return {@link Dessert} if dessert data found, null if not.
     * @throws DaoException when problems with database connection occurs.
     */
    Dessert findById(Integer dessertId) throws DaoException;

    /**
     * Connects to database and add new dessert.
     *
     * @param dessert is {@link Dessert} object that contains all info about dessert.
     * @return {@link Dessert} object that was saved in database
     * @throws DaoException when problems with database connection occurs.
     */
    Dessert createDessert(Dessert dessert) throws DaoException;

    /**
     * Connects to database and update dessert image.
     *
     * @param dbDessert is {@link Dessert} object that contains all info about dessert for update.
     * @throws DaoException when problems with database connection occurs.
     */
    void updateDessertImage(Dessert dbDessert) throws DaoException;

    /**
     * Connects to database and returns list of all desserts.
     *
     * @return List of {@link Dessert} with all dessert's detailed data.
     * @throws DaoException when problems with database connection occurs.
     */
    List<Dessert> findAllDessertList() throws DaoException;

    /**
     * Connects to database and updates dessert's data by ID.
     *
     * @param id is dessert ID value
     * @param dessert is {@link Dessert} object that contains all info about dessert for update.
     * @throws DaoException when problems with database connection occurs.
     */
    void updateDessert(Integer id, Dessert dessert) throws DaoException;


    /**
     * Update dessert quantity.
     *
     * @param id the id value
     * @param quantity the quantity value
     * @throws DaoException when problems with database connection occurs.
     */
    void updateDessertQuantity(Integer id, Integer quantity) throws DaoException;

    /**
     * Connects to database and delete dessert by ID.
     *
     * @param id is dessert ID value
     * @throws DaoException when problems with database connection occurs.
     */
    void deleteById(Integer id) throws DaoException;
}
