package com.safronova.webproject.model.dao.impl;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.model.dao.BasketDao;
import com.safronova.webproject.model.entity.Basket;
import com.safronova.webproject.model.pool.ConnectionPool;
import static com.safronova.webproject.model.dao.ColumnName.BASKET_ID;
import static com.safronova.webproject.model.dao.ColumnName.BASKET_TOTAL_COST;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BasketDaoImpl implements BasketDao {
    private static final BasketDaoImpl instance = new BasketDaoImpl();

    public static BasketDaoImpl getInstance() {
        return instance;
    }

    private BasketDaoImpl() {}

    private static final String FIND_BASKET_BY_USER =
            "SELECT b_id, b_total_cost, b_user_id " +
                    "FROM baskets " +
                    "JOIN users ON baskets.b_user_id = users.u_id " +
                    "WHERE (b_user_id = ?)";

    private static final String FIND_BASKET_BY_ID =
            "SELECT b_id, b_total_cost " +
                    "FROM baskets " +
                    "WHERE (b_id = ?)";

    private static final String INSERT_BASKET_SQL =
            "INSERT INTO baskets (b_user_id) " +
                    "VALUE (?)";

    private static final String SET_TOTAL_COST =
            "UPDATE baskets " +
                    "SET b_total_cost = ? " +
                    "WHERE b_id = ?";


    @Override
    public Basket findById(Integer id) throws DaoException {
        Basket basket = new Basket();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BASKET_BY_ID)) {
            statement.setInt(FindBasketIndex.ID, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                basket.setId(resultSet.getInt(BASKET_ID));
                basket.setTotalCost(resultSet.getBigDecimal(BASKET_TOTAL_COST));
            }
        } catch (SQLException e) {
            throw new DaoException("Can't handle BasketDao.findById request", e);
        }
        return basket;
    }

    @Override
    public Basket findByUserId(Integer userId) throws DaoException {
        Basket basket = new Basket();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BASKET_BY_USER)) {
            statement.setInt(FindBasketIndex.ID, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                basket.setId(resultSet.getInt(BASKET_ID));
                basket.setTotalCost(resultSet.getBigDecimal(BASKET_TOTAL_COST));
            }
        } catch (SQLException e) {
            throw new DaoException("Can't handle BasketDao.findByUserId request", e);
        }
        return basket;
    }

    @Override
    public Basket createBasket(Integer userId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_BASKET_SQL)) {
            statement.setInt(FindBasketIndex.ID, userId);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't handle BasketDao.createBasket request", e);
        }
        return findByUserId(userId);
    }

    @Override
    public void updateBasket(Basket basket) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SET_TOTAL_COST)) {
            statement.setBigDecimal(SetCostIndex.TOTAL_COST, basket.getTotalCost());
            statement.setInt(SetCostIndex.ID, basket.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't handle BasketDao.updateBasket request", e);
        }
    }

    private static class FindBasketIndex {
        private static final int ID = 1;
    }

    private static class SetCostIndex {
        private static final int TOTAL_COST = 1;
        private static final int ID = 2;
    }
}
