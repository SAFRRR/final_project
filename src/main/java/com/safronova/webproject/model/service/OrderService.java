package com.safronova.webproject.model.service;

import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.entity.*;

import java.util.List;
import java.util.Optional;

/**
 * {@code OrderService} is an interface with a bunch of methods that allow to manipulate {@link Order}
 *
 * @author Anna Safronova
 */
public interface OrderService {

    /**
     * Create a {@code Order} entity in the data store.
     *
     * @param address value pf order address
     * @param cash value of payment type
     * @param date value of date order
     * @param time value of time order
     * @param user {@link User} entity
     * @param basket {@link Basket} entity
     * @return {@link Order} entity wrapped with {@link Optional}. If credentials are invalid, {@code Optional}
     * @throws ServiceException if an error occurred executing the method.
     */
    Optional<Order> createOrder(String address, String cash, String date, String time, User user, Basket basket) throws ServiceException;

    /**
     * Create a {@code OrderDessert} entity by order.
     *
     * @param order {@link Order} entity
     * @param basketDessertList {@link BasketDessert} list
     * @return the list of {@link BasketDessert} entity
     * @throws ServiceException if an error occurred executing the method.
     */
    List<OrderDessert> createOrderDessertByOrder(Order order, List<BasketDessert> basketDessertList) throws ServiceException;

    /**
     *  Find {@link OrderDessert} entity by dessert id.
     *
     * @param id unique id of the assignment.
     * @return the list of {@link OrderDessert} entity
     * @throws ServiceException if an error occurred executing the method.
     */
    List<OrderDessert> findByDessert(int id) throws ServiceException;

    /**
     * Save a {@code Order} entity in the data store..
     *
     * @param order {@link Order} entity
     * @return {@link Order} entity
     * @throws ServiceException if an error occurred executing the method.
     */
    Order saveOrder(Order order) throws ServiceException;

    /**
     * Find {@link Order} entity by user id
     *
     * @param id user id
     * @return the list of {@link Order} entity
     * @throws ServiceException if an error occurred executing the method.
     */
    List<Order> findByUser(Integer id) throws ServiceException;

    /**
     * Find all {@link Order} entities
     *
     * @return the list of {@link Order} entity
     * @throws ServiceException if an error occurred executing the method.
     */
    List<Order> findAll() throws ServiceException;

    /**
     *  Find {@link Order} entity by order id
     *
     * @param orderId the order id
     * @return {@link Order} entity
     * @throws ServiceException if an error occurred executing the method.
     */
    Order findById(String orderId) throws ServiceException;

    /**
     * Find {@link Order} entity by orderDessert id
     *
     * @param id the orderDessert id
     * @return {@link OrderDessert} entity
     * @throws ServiceException if an error occurred executing the method.
     */
    List<OrderDessert> findByOrder(int id) throws ServiceException;

    /**
     * Change status of {@link Order}
     *
     * @param orderStatus status of the order
     * @param orderId the order id
     * @throws ServiceException if an error occurred executing the method.
     */
    void changeStatus(String orderStatus, String orderId) throws ServiceException;
}
