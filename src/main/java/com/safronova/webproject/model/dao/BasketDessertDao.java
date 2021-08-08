package com.safronova.webproject.model.dao;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.model.entity.BasketDessert;

import java.math.BigDecimal;
import java.util.List;

public interface BasketDessertDao {
    void addItemToBasket(Integer id, Integer dessertId, Integer count, BigDecimal subTotal) throws DaoException;

    List<BasketDessert> findByBasketId(Integer id) throws DaoException;

    void updateSubTotal(BasketDessert basketDessert) throws DaoException;

    void updateCount(BasketDessert basketDessert) throws DaoException;

    BasketDessert findById(Integer id) throws DaoException;

    void deleteBasketDessert(Integer id) throws DaoException;


}

