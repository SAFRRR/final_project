package com.safronova.webproject.model.service;

import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.dao.ResultCode;
import com.safronova.webproject.model.entity.Dessert;
import com.safronova.webproject.model.entity.DessertType;

import java.util.List;

/**
 * {@code DessertService} is an interface with a bunch of methods that allow to manipulate {@link Dessert}
 *
 * @author Anna Safronova
 */
public interface DessertService {
    /**
     * Find all {@link Dessert} entities
     *
     * @return the list of {@link Dessert} entity
     * @throws ServiceException if an error occurred executing the method.
     */
    List<Dessert> findAll() throws ServiceException;

    /**
     * Find {@link Dessert} entity by category list.
     *
     * @param category the category of dessert
     * @return the list of {@link Dessert} entity
     * @throws ServiceException if an error occurred executing the method.
     */
    List<Dessert> findByCategory(String category) throws ServiceException;

    /**
     * Find {@link Dessert} entity by id dessert.
     *
     * @param dessertId the dessert id
     * @return {@link Dessert} entity
     * @throws ServiceException if an error occurred executing the method.
     */
    Dessert findById(String dessertId) throws ServiceException;

    /**
     * Find {@link Dessert} entity by dessert name.
     *
     * @param name the name of tje dessert
     * @return {@link ResultCode} enum, that shows the result of the method execution.
     * @throws ServiceException if an error occurred executing the method.
     */
    ResultCode findDessertByName(String name) throws ServiceException;

    /**
     * Find all {@link Dessert} entities
     *
     * @return the list of {@link Dessert} entity
     * @throws ServiceException if an error occurred executing the method.
     */
    List<Dessert> findAllDessertList() throws ServiceException;

    /**
     * Create {@link Dessert} entity
     *
     * @param nameDessert value of dessert name
     * @param description value of dessert description
     * @param price value of dessert price
     * @param weight value of weight
     * @param dessertType value of dessert type
     * @param quantity value of quantity
     * @return {@link Dessert} entity
     * @throws ServiceException if an error occurred executing the method.
     */
    Dessert createDessert(String nameDessert, String description, String price,  String weight,  DessertType dessertType, String quantity) throws ServiceException;

    /**
     * Check {@link Dessert} quantity
     *
     * @param quantity value of dessert quantity
     * @return the dessert quantity
     * @throws ServiceException if an error occurred executing the method.
     */
    int checkQuantity(String quantity) throws ServiceException;

    /**
     * Update {@link Dessert} entity
     *
     * @param dessertId value of dessert id
     * @param nameDessert value of name dessert
     * @param description value of description
     * @param price value of price
     * @param weight value of weight
     * @param dessertType value of dessert type
     * @param quantity value of quantity
     * @throws ServiceException if an error occurred executing the method.
     */
    void updateDessert(String dessertId, String nameDessert, String description, String price, String weight, DessertType dessertType, String quantity) throws ServiceException;

    /**
     * Update dessert quantity.
     *
     * @param dessertId the dessert id value
     * @param quantity  the quantity value
     * @throws ServiceException if an error occurred executing the method.
     */
    void updateDessertQuantity(String dessertId, String quantity) throws ServiceException;

    /**
     * Delete {@link Dessert} entity by id.
     *
     * @param dessertId value of dessert id
     * @throws ServiceException if an error occurred executing the method.
     */
    void deleteDessertById(String dessertId) throws ServiceException;
}
