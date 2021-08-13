package com.safronova.webproject.model.dao;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.model.entity.OrderDessert;

import java.util.List;

public interface OrderDessertDao {
    void saveOrderDessert(OrderDessert orderDessert) throws DaoException;
    List<OrderDessert> findByDessertId(Integer id) throws DaoException;
    List<OrderDessert> findByOrder(Integer id) throws DaoException;
}


