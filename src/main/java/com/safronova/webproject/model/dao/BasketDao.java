package com.safronova.webproject.model.dao;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.model.entity.Basket;

public interface BasketDao {
    Basket findByUserId(Integer id) throws DaoException;

    Basket createBasket(Integer id) throws DaoException;

    void updateBasket(Basket basket) throws DaoException;

    Basket findById(Integer id) throws DaoException;
}

