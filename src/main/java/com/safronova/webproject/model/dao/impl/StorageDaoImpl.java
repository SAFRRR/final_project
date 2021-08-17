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

/**
 * Implementation of {@link StorageDao}. Provides methods to interact with Storage data from database.
 * Methods connect to database using {@link Connection} from {@link ConnectionPool} and manipulate with data(save, edit, etc.).
 */
public class StorageDaoImpl implements StorageDao {
    /**
     * A single instance of the class (pattern Singleton)
     */
    private static final StorageDaoImpl instance = new StorageDaoImpl();

    /** Query for database to get storage record by dessert table */
    private static final String SELECT_STORAGE_BY_DESSERT =
            "SELECT st_id, st_count " +
                    "FROM storages " +
                    "JOIN desserts ON desserts.d_id = storages.st_dessert_id " +
                    "WHERE (st_dessert_id = ?)";

    /** Query for database to add storage */
    private static final String INSERT_STORAGE_SQL =
            "INSERT INTO storages (st_count, st_dessert_id) " +
                    "VALUES (?,?)";

    /** Query for database to set storage count */
    private static final String SET_STORAGE_COUNT =
            "UPDATE storages " +
                    "SET st_count = ? " +
                    "WHERE st_id = ?";

    /** Query for database to set storage count by dessert */
    private static final String SET_STORAGE_COUNT_BY_DESSERT =
            "UPDATE storages " +
                    "SET st_count = ? " +
                    "WHERE st_dessert_id = ?";

    /**
     * Returns the instance of the class
     * @return Object of {@link StorageDaoImpl}
     */
    public static StorageDaoImpl getInstance() {
        return instance;
    }

    /**
     * Private constructor without parameters
     */
    private StorageDaoImpl() {}

    /**
     *  Connects to database and returns {@link Storage} object by id of dessert.
     *
     * @param dessertId is dessert's id value.
     * @return {@link Storage} if storage data found, null if not.
     * @throws DaoException when problems with database connection occurs.
     */
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

    /**
     * Connects to database and update storage data.
     *
     * @param storage is {@link Storage} object that contains all info about storage for update.
     * @throws DaoException when problems with database connection occurs.
     */
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

    /**
     * Connects to database and add new storage.
     *
     * @param storage is {@link Storage} object that contains all info about storage.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public void insertStorage(Storage storage) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_STORAGE_SQL)) {
            statement.setInt(InsertStorage.COUNT, storage.getCount());
            statement.setInt(InsertStorage.DESSERT_ID, storage.getDessert().getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't handle StorageDao.insertStorage request", e);
        }
    }

    /**
     * Connects to database and set count to the storage by dessert ID.
     *
     * @param dessertId is dessert ID value.
     * @param count is count of dessert in storage
     * @throws DaoException when problems with database connection occurs.
     */
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

    /**
     * Static class that contains parameter indexes for getting storage data by dessert ID
     */
    private static class FindStorageIndex {
        private static final int ID = 1;
    }

    /**
     * Static class that contains parameter indexes for setting storage count
     */
    private static class SetStorageIndex {
        private static final int COUNT = 1;
        private static final int ID = 2;
    }

    /**
     * Static class that contains parameter indexes for setting storage count
     */
    private static class InsertStorage {
        private static final int COUNT = 1;
        private static final int DESSERT_ID = 2;
    }
}
