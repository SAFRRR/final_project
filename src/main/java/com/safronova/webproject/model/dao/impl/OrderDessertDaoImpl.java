package com.safronova.webproject.model.dao.impl;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.model.dao.OrderDessertDao;
import com.safronova.webproject.model.entity.Dessert;
import com.safronova.webproject.model.entity.OrderDessert;
import com.safronova.webproject.model.pool.ConnectionPool;
import static com.safronova.webproject.model.dao.ColumnName.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDessertDaoImpl implements OrderDessertDao {
    private static final OrderDessertDaoImpl instance = new OrderDessertDaoImpl();

    public static OrderDessertDaoImpl getInstance() {
        return instance;
    }

    private OrderDessertDaoImpl() {}

    private static final String INSERT_ORDER_DESSERT_SQL =
            "INSERT INTO ordered_desserts (od_dessert_id, od_count, od_order_id, od_sub_total) "+
                    "VALUES (?,?,?,?)";

    private static final String SELECT_ORDER_DESSERT_SQL =
            "SELECT d_name, d_price, od_count, od_sub_total "+
                    "FROM ordered_desserts "+
                    "JOIN desserts ON ordered_desserts.od_dessert_id = desserts.d_id "+
                    "WHERE od_order_id = ?";

    private static final String SELECT_ORDER_DESSERT_BY_DESSERT_SQL =
            "SELECT d_name, d_price, od_count, od_sub_total "+
                    "FROM ordered_desserts "+
                    "JOIN desserts ON ordered_desserts.od_dessert_id = desserts.d_id "+
                    "WHERE od_dessert_id = ?";

    @Override
    public void saveOrderDessert(OrderDessert orderDessert) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_ORDER_DESSERT_SQL)) {
            statement.setInt(OrderDessertDaoImpl.InsertOrderDessertIndex.DESSERT_ID, orderDessert.getDessert().getId());
            statement.setInt(OrderDessertDaoImpl.InsertOrderDessertIndex.COUNT, orderDessert.getCount());
            statement.setInt(OrderDessertDaoImpl.InsertOrderDessertIndex.ORDER_ID, orderDessert.getOrder().getId());
            statement.setBigDecimal(OrderDessertDaoImpl.InsertOrderDessertIndex.SUB_TOTAL, orderDessert.getSubTotal());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't handle OrderDessertDao.saveOrderDessert request", e);
        }
    }

    @Override
    public List<OrderDessert> findByDessertId(Integer id) throws DaoException {
        List<OrderDessert> orderDessertList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ORDER_DESSERT_BY_DESSERT_SQL)) {
            statement.setInt(OrderDessertDaoImpl.SelectOrderDessertByDesseertIndex.DESSERT_ID, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                OrderDessert orderDessert = new OrderDessert();
                Dessert dessert = new Dessert();
                dessert.setName(resultSet.getString(DESSERT_NAME));
                dessert.setPrice(resultSet.getBigDecimal(DESSERT_PRICE));
                orderDessert.setCount(resultSet.getInt(ORDER_DESSERT_COUNT));
                orderDessert.setSubTotal(resultSet.getBigDecimal(ORDER_DESSERT_SUB_TOTAL));
                orderDessert.setDessert(dessert);
                orderDessertList.add(orderDessert);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't handle OrderDessertDao.findByOrder request", e);
        }
        return orderDessertList;
    }

    @Override
    public List<OrderDessert> findByOrder(Integer id) throws DaoException {
        List<OrderDessert> orderDessertList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ORDER_DESSERT_SQL)) {
            statement.setInt(OrderDessertDaoImpl.SelectOrderDessertIndex.ORDER_ID, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                OrderDessert orderDessert = new OrderDessert();
                Dessert dessert = new Dessert();
                dessert.setName(resultSet.getString(DESSERT_NAME));
                dessert.setPrice(resultSet.getBigDecimal(DESSERT_PRICE));
                orderDessert.setCount(resultSet.getInt(ORDER_DESSERT_COUNT));
                orderDessert.setSubTotal(resultSet.getBigDecimal(ORDER_DESSERT_SUB_TOTAL));
                orderDessert.setDessert(dessert);
                orderDessertList.add(orderDessert);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't handle OrderDessertDao.findByOrder request", e);
        }
        return orderDessertList;
    }

    private static class InsertOrderDessertIndex {
        private static final int DESSERT_ID = 1;
        private static final int COUNT = 2;
        private static final int ORDER_ID = 3;
        private static final int SUB_TOTAL = 4;
    }

    private static class SelectOrderDessertIndex {
        private static final int ORDER_ID = 1;
    }

    private static class SelectOrderDessertByDesseertIndex {
        private static final int DESSERT_ID = 1;
    }
}
