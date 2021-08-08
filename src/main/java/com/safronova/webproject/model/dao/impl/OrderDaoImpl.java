package com.safronova.webproject.model.dao.impl;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.model.dao.OrderDao;
import com.safronova.webproject.model.entity.Order;
import com.safronova.webproject.model.entity.Status;
import com.safronova.webproject.model.pool.ConnectionPool;
import static com.safronova.webproject.model.dao.ColumnName.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private static final OrderDaoImpl instance = new OrderDaoImpl();

    public static OrderDaoImpl getInstance() {
        return instance;
    }

    private OrderDaoImpl() {}

    private static final String INSERT_ORDER_SQL =
            "INSERT INTO orders (o_status, o_date_delivery, o_user_id, o_total_cost, o_time, o_address, o_date_order, o_cash)" +
                    "VALUES (?,?,?,?,?,?,?,?)";

    private static final String SELECT_ORDER_BY_USER_SQL =
            "SELECT o_id, o_status, o_date_order, o_total_cost " +
                    "FROM orders " +
                    "WHERE orders.o_user_id = ?";


    private static final String SELECT_ORDERS_SQL =
            "SELECT o_id, o_address, o_cash, o_date_delivery, o_date_order, o_status "+
                    "FROM orders";

    private static final String SELECT_ORDER_BY_ID_SQL =
            "SELECT o_id, o_status, o_total_cost " +
                    "FROM orders " +
                    "WHERE o_id = ?";

    private static final String UPDATE_STATUS_SQL =
            "UPDATE orders " +
                    "SET o_status = ? " +
                    "WHERE o_id = ?";


    @Override
    public Order saveOrder(Order order) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_ORDER_SQL, Statement.RETURN_GENERATED_KEYS)) {
            String status = String.valueOf(order.getStatusOrder());
            statement.setString(InsertOrderIndex.STATUS_ORDER, status);
            statement.setDate(InsertOrderIndex.DATE_DELIVERY, new java.sql.Date(order.getDateDelivery().getTime()));
            statement.setInt(InsertOrderIndex.USER_ID, order.getUser().getId());
            statement.setBigDecimal(InsertOrderIndex.TOTAL_COST, order.getTotalCost());
            statement.setString(InsertOrderIndex.TIME_ORDER, order.getTimeOrder());
            statement.setString(InsertOrderIndex.ADDRESS, order.getAddress());
            statement.setDate(InsertOrderIndex.DATE_ORDER, new java.sql.Date(order.getDateOrder().getTime()));
            statement.setBoolean(InsertOrderIndex.CASH, order.isCash());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("Creating order failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.setId(generatedKeys.getInt(1));
                } else {
                    throw new DaoException("Creating order failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can't handle OrderDao.saveOrder request", e);
        }
        return order;
    }

    @Override
    public List<Order> findByUserId(Integer id) throws DaoException {
        List<Order> orderList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ORDER_BY_USER_SQL)) {
            statement.setInt(FindOrder.ID, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt(ORDER_ID));
                Status status = Status.valueOf(resultSet.getString(ORDER_STATUS));
                order.setStatusOrder(status);
                order.setDateOrder(resultSet.getDate(ORDER_DATE));
                order.setTotalCost(resultSet.getBigDecimal(ORDER_TOTAL_COST));
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't handle OrderDao.findByUserId request", e);
        }
        return orderList;
    }

    @Override
    public List<Order> findAll() throws DaoException {
        List<Order> orderList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ORDERS_SQL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt(ORDER_ID));
                order.setAddress(resultSet.getString(ORDER_ADDRESS));
                order.setCash(resultSet.getBoolean(ORDER_CASH));
                order.setDateDelivery(resultSet.getDate(ORDER_DATE_DELIVERY));
                order.setDateOrder(resultSet.getDate(ORDER_DATE));
                Status status = Status.valueOf(resultSet.getString(ORDER_STATUS));
                order.setStatusOrder(status);
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't handle OrderDao.findAll request", e);
        }
        return orderList;
    }

    @Override
    public Order findById(Integer id) throws DaoException {
        Order order = new Order();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ORDER_BY_ID_SQL)) {
            statement.setInt(FindOrder.ID, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                order.setId(resultSet.getInt(ORDER_ID));
                Status status = Status.valueOf(resultSet.getString(ORDER_STATUS));
                order.setStatusOrder(status);
                order.setTotalCost(resultSet.getBigDecimal(ORDER_TOTAL_COST));
            }
        } catch (SQLException e) {
            throw new DaoException("Can't handle OrderDao.findById request", e);
        }
        return order;
    }

    @Override
    public void updateStatusById(String orderStatus, Integer orderId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS_SQL)) {
            statement.setString(UpdateStatusIndex.STATUS, orderStatus);
            statement.setInt(UpdateStatusIndex.ID, orderId);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't handle OrderDao.updateStatusById request", e);
        }
    }

    private static class InsertOrderIndex {
        private static final int STATUS_ORDER = 1;
        private static final int DATE_DELIVERY = 2;
        private static final int USER_ID = 3;
        private static final int TOTAL_COST = 4;
        private static final int TIME_ORDER = 5;
        private static final int ADDRESS = 6;
        private static final int DATE_ORDER = 7;
        private static final int CASH = 8;
    }

    private static class FindOrder {
        private static final int ID = 1;
    }

    private static class UpdateStatusIndex {
        private static final int STATUS = 1;
        private static final int ID = 2;
    }

}
