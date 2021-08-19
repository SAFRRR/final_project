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

/**
 * Implementation of {@link BasketDessertDao}. Provides methods to interact with BasketDessert data from database.
 * Methods connect to database using {@link Connection} from {@link ConnectionPool} and manipulate with data(save, edit, etc.).
 */
public class BasketDessertDaoImpl implements BasketDessertDao {
    /**
     * A single instance of the class (pattern Singleton)
     */
    private static final BasketDessertDaoImpl instance = new BasketDessertDaoImpl();

    /** Query for database to select basketDessert by id */
    private static final String SELECT_BY_ID_SQL =
            "SELECT bd_id, bd_count, bd_sub_total " +
                    "FROM basket_desserts " +
                    "WHERE (bd_id = ?)";

    /** Query for database to select items by basket id */
    private static final String SELECT_ITEMS_BY_BASKET_ID_SQL =
            "SELECT bd_id, bd_basket_id, bd_dessert_id, bd_count, bd_sub_total, d_image, d_name, d_price, d_quantity, st_count "+
                    "FROM basket_desserts "+
                    "INNER JOIN desserts ON bd_dessert_id = desserts.d_id "+
                    "INNER JOIN storages ON desserts.d_id = storages.st_dessert_id "+
                    "WHERE (bd_basket_id = ?)";

    /** Query for database to select items by dessert id */
    private static final String SELECT_ITEMS_BY_DESSERT_ID_SQL =
            "SELECT bd_id, bd_basket_id, bd_dessert_id, bd_count, bd_sub_total, d_image, d_name, d_price, st_count "+
                    "FROM basket_desserts "+
                    "INNER JOIN desserts ON bd_dessert_id = desserts.d_id "+
                    "INNER JOIN storages ON desserts.d_id = storages.st_dessert_id "+
                    "WHERE (bd_dessert_id = ?)";

    /** Query for database to add item to basket */
    private static final String ADD_ITEM_SQL =
            "INSERT INTO basket_desserts (bd_basket_id, bd_dessert_id, bd_count, bd_sub_total)"+
                    "VALUES(?,?,?,?)";

    /** Query for database to set sub total cost */
    private static final String SET_SUB_TOTAL_SQL =
            "UPDATE basket_desserts " +
                    "SET bd_sub_total = ?" +
                    "WHERE bd_id = ?";

    /** Query for database to set count */
    private static final String SET_COUNT_SQL =
            "UPDATE basket_desserts " +
                    "SET bd_count =? " +
                    "WHERE bd_id = ?";

    /** Query for database to delete basketFlower by id */
    private static final String DELETE_ITEM_SQL =
            "DELETE FROM basket_desserts " +
                    "WHERE bd_id = ?";

    /**
     * Returns the instance of the class
     *
     * @return Object of {@link BasketDessertDaoImpl}
     */
    public static BasketDessertDaoImpl getInstance() {
        return instance;
    }

    /**
     * Private constructor without parameters
     */
    private BasketDessertDaoImpl() {}

    /**
     * Connects to database and returns all info about basket dessert by ID.
     *
     * @param id is basket dessert ID value.
     * @return  {@link BasketDessert} if basket dessert data found, null if not.
     * @throws DaoException when problems with database connection occurs.
     */
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

    /**
     * Connects to database and delete basket dessert by ID.
     *
     * @param id is basket dessert ID value
     * @throws DaoException when problems with database connection occurs.
     */
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

    /**
     * Connects to database and add new dessert to basket.
     *
     * @param id is basket ID value.
     * @param dessertId is dessert ID value
     * @param count is amount of the desserts in basket
     * @param subTotal is sub total of user's basket, calculated as count of the desserts multiplied by its price
     * @throws DaoException when problems with database connection occurs.
     */
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

    /**
     *  Connects to database and returns all info about basket by its ID.
     *
     * @param id is basket ID value.
     * @return List of {@link BasketDessert} with all matching data.
     * @throws DaoException when problems with database connection occurs.
     */
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
                dessert.setQuantity(resultSet.getInt(DESSERT_QUANTITY));
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

    /**
     * Connects to database and returns all info about basket by dessert ID.
     *
     * @param id is dessert ID value.
     * @return List of {@link BasketDessert} with all matching data.
     * @throws DaoException when problems with database connection occurs.
     */
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
            throw new DaoException("Can't handle BasketDessertDao.findByDessertId request", e);
        }
        return basketDessertList;
    }

    /**
     * Connects to database and updates sub total of basket.
     *
     * @param basketDessert is {@link BasketDessert} object that contains all info about basket dessert for update.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public void updateSubTotal(BasketDessert basketDessert) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SET_SUB_TOTAL_SQL)) {
            statement.setBigDecimal(BasketDessertDaoImpl.SetSubTotalIndex.SUB_TOTAL, basketDessert.getSubTotal());
            statement.setInt(BasketDessertDaoImpl.SetSubTotalIndex.ID, basketDessert.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't handle BasketDessertDao.updateSubTotal request", e);
        }
    }

    /**
     * Connects to database and updates count of dessert in basket.
     *
     * @param basketDessert is {@link BasketDessert} object that contains all info about basket dessert for update.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public void updateCount(BasketDessert basketDessert) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SET_COUNT_SQL)) {
            statement.setInt(BasketDessertDaoImpl.SetSubTotalIndex.SUB_TOTAL, basketDessert.getCount());
            statement.setInt(BasketDessertDaoImpl.SetSubTotalIndex.ID, basketDessert.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't handle BasketDessertDao.updateCount request", e);
        }
    }

    /**
     * Static class that contains parameter indexes for adding item to basket
     */
    private static class AddItemIndex {
        private static final int BASKET_ID = 1;
        private static final int DESSERT_ID = 2;
        private static final int COUNT = 3;
        private static final int SUB_TOTAL = 4;
    }


    /**
     * Static class that contains parameter indexes for finding items from basket
     */
    private static class ItemIndex {
        private static final int BASKET_ID = 1;
    }

    /**
     * Static class that contains parameter indexes for setting sub_total
     */
    private static class SetSubTotalIndex {
        private static final int SUB_TOTAL = 1;
        private static final int ID = 2;
    }
}
