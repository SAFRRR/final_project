package com.safronova.webproject.model.service;

import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.entity.Basket;
import com.safronova.webproject.model.entity.BasketDessert;

import java.util.List;

public interface BasketDessertService {
    void addToBasket(int id, String dessertId, String count, String price) throws ServiceException;

    List<BasketDessert> findByBasketId(int id) throws ServiceException;

    List<BasketDessert> findByDessertId(int id) throws ServiceException;

    void updateBasketDessert(String basketDessertId, String count) throws ServiceException;

    void deleteBasketDessert(String basketDessertId) throws ServiceException;

    Basket clearBasket(Basket basket) throws ServiceException;
}
