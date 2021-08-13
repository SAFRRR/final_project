package com.safronova.webproject.model.dao.impl;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.model.dao.BasketDessertDao;
import com.safronova.webproject.model.entity.Basket;
import com.safronova.webproject.model.entity.BasketDessert;
import com.safronova.webproject.model.entity.Dessert;
import com.safronova.webproject.model.entity.Storage;
import com.safronova.webproject.model.pool.ConnectionPool;
import static com.safronova.webproject.model.dao.ColumnName.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasketDessertDaoImpl implements BasketDessertDao {
    private static final BasketDessertDaoImpl instance = new BasketDessertDaoImpl();

    public static BasketDessertDaoImpl getInstance() {
        return instance;
    }

    private BasketDessertDaoImpl() {}

    private static final String SELECT_BY_ID_SQL =
            "SELECT bd_id, bd_count, bd_sub_total " +
                    "FROM basket_desserts " +
                    "WHERE (bd_id = ?)";



    private static final String SELECT_ITEMS_BY_BASKET_ID_SQL =
            "SELECT bd_id, bd_basket_id, bd_dessert_id, bd_count, bd_sub_total, d_image, d_name, d_price, st_count "+
                    "FROM basket_desserts "+
                    "INNER JOIN desserts ON bd_dessert_id = desserts.d_id "+
                    "INNER JOIN storages ON desserts.d_id = storages.st_dessert_id "+
                    "WHERE (bd_basket_id = ?)";

    private static final String SELECT_ITEMS_BY_DESSERT_ID_SQL =
            "SELECT bd_id, bd_basket_id, bd_dessert_id, bd_count, bd_sub_total, d_image, d_name, d_price, st_count "+
                    "FROM basket_desserts "+
                    "INNER JOIN desserts ON bd_dessert_id = desserts.d_id "+
                    "INNER JOIN storages ON desserts.d_id = storages.st_dessert_id "+
                    "WHERE (bd_dessert_id = ?)";

    private static final String ADD_ITEM_SQL =
            "INSERT INTO basket_desserts (bd_basket_id, bd_dessert_id, bd_count, bd_sub_total)"+
                    "VALUES(?,?,?,?)";

    private static final String SET_SUB_TOTAL_SQL =
            "UPDATE basket_desserts " +
                    "SET bd_sub_total = ?" +
                    "WHERE bd_id = ?";

    private static final String SET_COUNT_SQL =
            "UPDATE basket_desserts " +
                    "SET bd_count =? " +
                    "WHERE bd_id = ?";

    private static final String DELETE_ITEM_SQL =
            "DELETE FROM basket_desserts " +
                    "WHERE bd_id = ?";

    @Override
    public BasketDessert findById(Integer id) throws DaoException {
        BasketDessert basketDessert = new BasketDessert();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            statement.setInt(BasketDessertDaoImpl.ItemIndex.BASKET_ID, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                basketDessert.setId(resultSet.getInt(BASKET_DESSERT_ID));
                basketDessert.setCount(resultSet.getInt(BASKET_DESSERT_COUNT));
                basketDessert.setSubTotal(resultSet.getBigDecimal(BASKET_DESSERT_SUB_TOTAL));
            }
        } catch (SQLException e) {
            throw new DaoException("Can't handle BasketDessertDao.findById request", e);
        }
        return basketDessert;
    }

    @Override
    public void deleteBasketDessert(Integer id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ITEM_SQL)) {
            statement.setInt(BasketDessertDaoImpl.ItemIndex.BASKET_ID, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't handle BasketDessertDao.deleteBasketDessert request", e);
        }
    }

    @Override
    public void addItemToBasket(Integer id, Integer dessertId, Integer count, BigDecimal subTotal) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_ITEM_SQL)) {
            statement.setInt(BasketDessertDaoImpl.AddItemIndex.BASKET_ID, id);
            statement.setInt(BasketDessertDaoImpl.AddItemIndex.DESSERT_ID, dessertId);
            statement.setInt(BasketDessertDaoImpl.AddItemIndex.COUNT, count);
            statement.setBigDecimal(BasketDessertDaoImpl.AddItemIndex.SUB_TOTAL, subTotal);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't handle BasketDessertDao.addItemToBasket request", e);
        }
    }

    @Override
    public List<BasketDessert> findByBasketId(Integer id) throws DaoException {
        List<BasketDessert> basketDessertList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ITEMS_BY_BASKET_ID_SQL)) {
            statement.setInt(BasketDessertDaoImpl.ItemIndex.BASKET_ID, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                BasketDessert basketDessert = new BasketDessert();
                Dessert dessert = new Dessert();
                Basket basket = new Basket();
                Storage storage = new Storage();
                basketDessert.setId(resultSet.getInt(BASKET_DESSERT_ID));
                basketDessert.setBasket(basket);
                basketDessert.setDessert(dessert);
                basketDessert.setSubTotal(resultSet.getBigDecimal(BASKET_DESSERT_SUB_TOTAL));
                basketDessert.setCount(resultSet.getInt(BASKET_DESSERT_COUNT));
                dessert.setId(resultSet.getInt(BASKET_DESSERT_DESSERT_ID));
                dessert.setDessertImage(resultSet.getString(DESSERT_IMAGE));
                dessert.setName(resultSet.getString(DESSERT_NAME));
                dessert.setStorage(storage);
                dessert.setPrice(resultSet.getBigDecimal(DESSERT_PRICE));
                basket.setId(resultSet.getInt(BASKET_DESSERT_BASKET_ID));
                storage.setCount(resultSet.getInt(STORAGE_COUNT));
                basketDessertList.add(basketDessert);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't handle BasketDessertDao.findByBasketId request", e);
        }
        return basketDessertList;
    }


    @Override
    public List<BasketDessert> findByDessertId(Integer id) throws DaoException {
        List<BasketDessert> basketDessertList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ITEMS_BY_DESSERT_ID_SQL)) {
            statement.setInt(BasketDessertDaoImpl.ItemIndex.BASKET_ID, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                BasketDessert basketDessert = new BasketDessert();
                Dessert dessert = new Dessert();
                Basket basket = new Basket();
                Storage storage = new Storage();
                basketDessert.setId(resultSet.getInt(BASKET_DESSERT_ID));
                basketDessert.setBasket(basket);
                basketDessert.setDessert(dessert);
                basketDessert.setSubTotal(resultSet.getBigDecimal(BASKET_DESSERT_SUB_TOTAL));
                basketDessert.setCount(resultSet.getInt(BASKET_DESSERT_COUNT));
                dessert.setId(resultSet.getInt(BASKET_DESSERT_DESSERT_ID));
                dessert.setDessertImage(resultSet.getString(DESSERT_IMAGE));
                dessert.setName(resultSet.getString(DESSERT_NAME));
                dessert.setStorage(storage);
                dessert.setPrice(resultSet.getBigDecimal(DESSERT_PRICE));
                basket.setId(resultSet.getInt(BASKET_DESSERT_BASKET_ID));
                storage.setCount(resultSet.getInt(STORAGE_COUNT));
                basketDessertList.add(basketDessert);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't handle BasketDessertDao.findByBasketId request", e);
        }
        return basketDessertList;
    }


    @Override
    public void updateSubTotal(BasketDessert basketDessert) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SET_SUB_TOTAL_SQL)) {
            statement.setBigDecimal(BasketDessertDaoImpl.SetSubTotalIndex.SUB_TOTAL, basketDessert.getSubTotal());
            statement.setInt(BasketDessertDaoImpl.SetSubTotalIndex.ID, basketDessert.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't handle BasketDessertDao.updateBasketDessert request", e);
        }
    }

    @Override
    public void updateCount(BasketDessert basketDessert) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SET_COUNT_SQL)) {
            statement.setInt(BasketDessertDaoImpl.SetSubTotalIndex.SUB_TOTAL, basketDessert.getCount());
            statement.setInt(BasketDessertDaoImpl.SetSubTotalIndex.ID, basketDessert.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't handle BasketDessertDao.setCountBasketDessert request", e);
        }
    }

    private static class AddItemIndex {
        private static final int BASKET_ID = 1;
        private static final int DESSERT_ID = 2;
        private static final int COUNT = 3;
        private static final int SUB_TOTAL = 4;
    }

    private static class ItemIndex {
        private static final int BASKET_ID = 1;
    }

    private static class SetSubTotalIndex {
        private static final int SUB_TOTAL = 1;
        private static final int ID = 2;
    }
}

