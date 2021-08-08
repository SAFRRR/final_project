package com.safronova.webproject.model.service;

import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.entity.Basket;
import com.safronova.webproject.model.entity.BasketDessert;

import java.util.List;

public interface BasketService {
    Basket findUserBasket(Integer id) throws ServiceException;

    void updateBasket(Basket basket, List<BasketDessert> basketDessertList) throws ServiceException;

    void updateTotalCost(Basket basket) throws ServiceException;

    Basket findById(String basketId) throws ServiceException;
}
