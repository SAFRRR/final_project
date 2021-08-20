package com.safronova.webproject.model.service;

import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.entity.Basket;
import com.safronova.webproject.model.entity.BasketDessert;
import com.safronova.webproject.model.entity.Dessert;

import java.util.List;

/**
 * {@code BasketService} is an interface with a bunch of methods that allow to manipulate {@link Basket}
 *
 * @author Anna Safronova
 */
public interface BasketService {
    /**
     * Find {@link Basket} entity
     *
     * @param id value of basket id
     * @return  {@link Basket} entity
     * @throws ServiceException if an error occurred executing the method.
     */
    Basket findUserBasket(Integer id) throws ServiceException;

    /**
     * Update {@link Basket} entity
     *
     * @param basket {@link Basket} entity
     * @param basketDessertList  {@link BasketDessert} list
     * @throws ServiceException if an error occurred executing the method.
     */
    void updateBasket(Basket basket, List<BasketDessert> basketDessertList) throws ServiceException;

    /**
     * Update total cost.
     *
     * @param basket {@link Basket} entity
     * @throws ServiceException if an error occurred executing the method.
     */
    void updateTotalCost(Basket basket) throws ServiceException;

    /**
     * Find {@link Basket} entity by basket id
     *
     * @param basketId the basket id
     * @return {@link Basket} entity
     * @throws ServiceException if an error occurred executing the method.
     */
    Basket findById(String basketId) throws ServiceException;
}
