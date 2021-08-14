package com.safronova.webproject.model.service.impl;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.dao.DaoProvider;
import com.safronova.webproject.model.dao.OrderDao;
import com.safronova.webproject.model.dao.OrderDessertDao;
import com.safronova.webproject.model.dao.StorageDao;
import com.safronova.webproject.model.entity.*;
import com.safronova.webproject.model.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LogManager.getLogger();
    private static final DaoProvider daoProvider = DaoProvider.getInstance();
    private static final OrderDao orderDao = daoProvider.getOrderDao();
    private static final OrderDessertDao orderDessertDao = daoProvider.getOrderDessertDao();
    private static final StorageDao storageDao = daoProvider.getStorageDao();

    @Override
    public Order saveOrder(Order order) throws ServiceException {
        Order savedOrder;
        try {
            savedOrder = orderDao.saveOrder(order);
        } catch (DaoException e) {
            throw new ServiceException("Can't handle saveOrder request at OrderService", e);
        }
        return savedOrder;
    }

    @Override
    public List<Order> findByUser(Integer id) throws ServiceException {
        List<Order> orderList;
        try {
            orderList = orderDao.findByUserId(id);
        } catch (DaoException e) {
            throw new ServiceException("Can't handle findByUser request at OrderService", e);
        }
        return orderList;
    }



    @Override
    public List<Order> findAll() throws ServiceException {
        List<Order> orderList;
        try {
            orderList = orderDao.findAll();
            Comparator<Order> comparator = Comparator.comparing(Order::getDateOrder).reversed();
            orderList = orderList.stream().sorted(comparator).collect(Collectors.toList());
        } catch (DaoException e) {
            throw new ServiceException("Can't handle findAll request at OrderService", e);
        }
        return orderList;
    }

    @Override
    public Order findById(String orderId) throws ServiceException {
        Order order;
        int id = Integer.parseInt(orderId);
        try {
            order = orderDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Can't handle findById request at OrderService", e);
        }
        return order;
    }

    @Override
    public List<OrderDessert> findByOrder(int id) throws ServiceException {
        List<OrderDessert> orderDessertList;
        try {
            orderDessertList = orderDessertDao.findByOrder(id);
        } catch (DaoException e) {
            throw new ServiceException("Can't handle findByOrder request at OrderService", e);
        }
        return orderDessertList;
    }

    @Override
    public List<OrderDessert> findByDessert(int id) throws ServiceException {
        List<OrderDessert> orderDessertList;
        try {
            orderDessertList = orderDessertDao.findByDessertId(id);
        } catch (DaoException e) {
            throw new ServiceException("Can't handle findByDessertId request at OrderService", e);
        }
        return orderDessertList;
    }

    @Override
    public void changeStatus(String orderStatus, String orderId) throws ServiceException {
        try {
            int id = Integer.parseInt(orderId);
            orderDao.updateStatusById(orderStatus, id);
        } catch (DaoException e) {
            throw new ServiceException("Can't handle changeStatus request at OrderService", e);
        }
    }

    @Override
    public Optional<Order> createOrder(String address, String cash, String date, String time, User user, Basket basket) {
        if (address.isEmpty() || cash.isEmpty() || date.isEmpty() || time.isEmpty()) {
            return Optional.empty();
        }
        boolean orderCash = Boolean.parseBoolean(cash);
        Date orderDate = parseOrderDate(date);
        BigDecimal orderTotalCost = basket.getTotalCost();
        Order order = new Order(Status.INPROCESS, address, Calendar.getInstance().getTime(), orderDate, time, orderTotalCost, user, orderCash);
        return Optional.of(order);
    }

    private Date parseOrderDate(String date) {
        SimpleDateFormat formatTemplate = new SimpleDateFormat("yyyy-MM-dd");
        Date parsingDate = null;
        try {
            parsingDate = formatTemplate.parse(date);
        } catch (ParseException e) {
            logger.error("Can't parse order date", e);
        }
        return parsingDate;
    }

    @Override
    public List<OrderDessert> createOrderDessertByOrder(Order order, List<BasketDessert> basketDessertList) throws ServiceException {
        List<OrderDessert> orderDessertList = new ArrayList<>();
        for (BasketDessert basketDessert : basketDessertList) {
            Dessert dessert = basketDessert.getDessert();
            OrderDessert orderDessert = new OrderDessert();
            orderDessert.setOrder(order);
            orderDessert.setDessert(dessert);
            orderDessert.setCount(basketDessert.getCount());
            orderDessert.setSubTotal(basketDessert.getSubTotal());
            orderDessertList.add(orderDessert);
            Storage storage = dessert.getStorage();
            int count = storage.getCount() - basketDessert.getCount();
            updateStorage(dessert.getId(), count);
            try {
                orderDessertDao.saveOrderDessert(orderDessert);
            } catch (DaoException e) {
                throw new ServiceException("Can't handle createOrderDessertByOrder request at OrderService", e);
            }
        }
        return orderDessertList;
    }

    private void updateStorage(Integer dessertId, Integer count) throws ServiceException {
        try {
            Storage dbStorage = storageDao.findByDessertId(dessertId);
            dbStorage.setCount(count);
            storageDao.updateStorage(dbStorage);
        } catch (DaoException e) {
            throw new ServiceException("Can't handle saveOrder request at OrderService", e);
        }
    }
}
