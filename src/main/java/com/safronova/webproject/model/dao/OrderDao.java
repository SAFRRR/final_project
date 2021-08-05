package com.safronova.webproject.model.dao;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.model.entity.Order;

import java.util.List;

public interface OrderDao {
    Order saveOrder(Order order) throws DaoException;

    List<Order> findByUserId(Integer id) throws DaoException;

    List<Order> findAll() throws DaoException;

    Order findById(Integer id) throws DaoException;

    void updateStatusById(String orderStatus, Integer orderId) throws DaoException;
}
