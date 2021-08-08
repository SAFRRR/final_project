package com.safronova.webproject.model.dao.impl;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.model.dao.StorageDao;
import com.safronova.webproject.model.entity.Storage;
import com.safronova.webproject.model.pool.ConnectionPool;
import static com.safronova.webproject.model.dao.ColumnName.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StorageDaoImpl implements StorageDao {
    private static final StorageDaoImpl instance = new StorageDaoImpl();

    public static StorageDaoImpl getInstance() {
        return instance;
    }

    private StorageDaoImpl() {}

    private static final String SELECT_STORAGE_BY_DESSERT =
            "SELECT st_id, st_count " +
                    "FROM storages " +
                    "JOIN desserts ON desserts.d_id = storages.st_dessert_id " +
                    "WHERE (st_dessert_id = ?)";

    private static final String INSERT_STORAGE_SQL =
            "INSERT INTO storages (st_count, st_dessert_id)" +
                    "VALUES (?,?)";

    private static final String SET_STORAGE_COUNT =
            "UPDATE storages " +
                    "SET st_count = ? " +
                    "WHERE st_id = ?";

    private static final String SET_STORAGE_COUNT_BY_DESSERT =
            "UPDATE storages " +
                    "SET st_count = ? " +
                    "WHERE st_dessert_id = ?";


    @Override
    public Storage findByDessertId(Integer dessertId) throws DaoException {
        Storage storage = new Storage();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_STORAGE_BY_DESSERT)) {
            statement.setInt(FindStorageIndex.ID, dessertId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                storage.setId(resultSet.getInt(STORAGE_ID));
                storage.setCount(resultSet.getInt(STORAGE_COUNT));
            }
        } catch (SQLException e) {
            throw new DaoException("Can't handle StorageDao.findByDessertId request", e);
        }
        return storage;
    }

    @Override
    public void updateStorage(Storage storage) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SET_STORAGE_COUNT)) {
            statement.setInt(SetStorageIndex.COUNT, storage.getCount());
            statement.setInt(SetStorageIndex.ID, storage.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't handle StorageDao.updateStorage request", e);
        }
    }

    @Override
    public void insertStorage(Storage storage) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_STORAGE_SQL)) {
            statement.setInt(InserStorage.COUNT, storage.getCount());
            statement.setInt(InserStorage.DESSERT_ID, storage.getDessert().getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't handle StorageDao.insertStorage request", e);
        }
    }

    @Override
    public void updateStorageByDessert(Integer dessertId, Integer count) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SET_STORAGE_COUNT_BY_DESSERT)) {
            statement.setInt(SetStorageIndex.COUNT, count);
            statement.setInt(SetStorageIndex.ID, dessertId);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't handle StorageDao.updateStorageByDessert request", e);
        }
    }

    private static class FindStorageIndex {
        private static final int ID = 1;
    }

    private static class SetStorageIndex {
        private static final int COUNT = 1;
        private static final int ID = 2;
    }

    private static class InserStorage {
        private static final int COUNT = 1;
        private static final int DESSERT_ID = 2;
    }

}
