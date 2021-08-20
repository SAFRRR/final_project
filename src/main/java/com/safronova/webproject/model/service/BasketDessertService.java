package com.safronova.webproject.model.service;

import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.entity.Basket;
import com.safronova.webproject.model.entity.BasketDessert;

import java.util.List;

/**
 * {@code BasketDessertService} is an interface with a bunch of methods that allow to manipulate {@link BasketDessert}
 *
 * @author Anna Safronova
 */
public interface BasketDessertService {
    /**
     * Add item to basket.
     *
     * @param id value of basketDessert id
     * @param dessertId value of  dessert id
     * @param count value of dessert count
     * @param price value of dessert price
     * @throws ServiceException if an error occurred executing the method.
     */
    void addToBasket(int id, String dessertId, String count, String price) throws ServiceException;

    /**
     * Find {@link BasketDessert} entities by basket id.
     *
     * @param id value of basket id
     * @return the list of {@link BasketDessert} entities
     * @throws ServiceException if an error occurred executing the method.
     */
    List<BasketDessert> findByBasketId(int id) throws ServiceException;

    /**
     * Find {@link BasketDessert} entities by dessert id
     *
     * @param id value of dessert id
     * @return the list of {@link BasketDessert} entities
     * @throws ServiceException if an error occurred executing the method.
     */
    List<BasketDessert> findByDessertId(int id) throws ServiceException;

    /**
     * Update {@link BasketDessert} entity
     *
     * @param basketDessertId value of basket dessert id
     * @param count value of dessert count
     * @throws ServiceException if an error occurred executing the method.
     */
    void updateBasketDessert(String basketDessertId, String count) throws ServiceException;

    /**
     * Delete {@link BasketDessert} entity
     *
     * @param basketDessertId value of basket dessert id
     * @throws ServiceException if an error occurred executing the method.
     */
    void deleteBasketDessert(String basketDessertId) throws ServiceException;

    /**
     * Clear {@link BasketDessert} entity
     *
     * @param basket {@link Basket} entity
     * @return {@link BasketDessert} entity
     * @throws ServiceException if an error occurred executing the method.
     */
    Basket clearBasket(Basket basket) throws ServiceException;
}
