package com.safronova.webproject.model.service;

import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.entity.*;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<Order> createOrder(String address, String cash, String date, String time, User user, Basket basket) throws ServiceException;

    List<OrderDessert> createOrderDessertByOrder(Order order, List<BasketDessert> basketDessertList) throws ServiceException;

    Order saveOrder(Order order) throws ServiceException;

    List<Order> findByUser(Integer id) throws ServiceException;

    List<Order> findAll() throws ServiceException;

    Order findById(String orderId) throws ServiceException;

    List<OrderDessert> findByOrder(int id) throws ServiceException;

    void changeStatus(String orderStatus, String orderId) throws ServiceException;
}
