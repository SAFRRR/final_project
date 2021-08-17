package com.safronova.webproject.model.dao.impl;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.model.dao.DessertTypeDao;
import com.safronova.webproject.model.entity.DessertCategory;
import com.safronova.webproject.model.entity.DessertType;
import com.safronova.webproject.model.pool.ConnectionPool;
import static com.safronova.webproject.model.dao.ColumnName.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link DessertTypeDao}. Provides methods to interact with DessertType data from database.
 * Methods connect to database using {@link Connection} from {@link ConnectionPool} and manipulate with data(save, edit, etc.).
 */
public class DessertTypeDaoImpl implements DessertTypeDao {
    /**
     * A single instance of the class (pattern Singleton)
     */
    private static final DessertTypeDaoImpl instance = new DessertTypeDaoImpl();

    /** Query for database to get all record in dessert_type table */
    private static final String SELECT_ALL_DESSERT_TYPE_SQL =
            "SELECT dt_id, dt_category, dt_description "+
                    "FROM dessert_types";

    /**Query for database to get all record in dessert_type table by ID*/
    private static final String SELECT_DESSERT_TYPE_BY_ID =
            "SELECT dt_id, dt_category, dt_description " +
                    "FROM dessert_types " +
                    "WHERE (dt_id = ?)";

    /**
     * Returns the instance of the class
     * @return Object of {@link DessertTypeDaoImpl}
     */
    public static DessertTypeDaoImpl getInstance() {
        return instance;
    }

    /**
     * Private constructor without parameters
     */
    private DessertTypeDaoImpl() {}

    /**
     *  Connects to database and returns list of all dessert types.
     *
     * @return List of {@link DessertType} with all type of desserts.
     * @throws DaoException  when problems with database connection occurs.
     */
    @Override
    public List<DessertType> findAll() throws DaoException {
        List<DessertType> dessertTypeList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_DESSERT_TYPE_SQL);
            while (resultSet.next()) {
                DessertType dessertType = new DessertType();
                dessertType.setId(resultSet.getInt(DESSERT_TYPE_ID));
                DessertCategory category = DessertCategory.valueOf(resultSet.getString(DESSERT_TYPE_CATEGORY));
                dessertType.setCategory(category);
                dessertType.setDescription(resultSet.getString(DESSERT_TYPE_DESCRIPTION));
                dessertTypeList.add(dessertType);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't handle DessertTypeDao.findAll request", e);
        }
        return dessertTypeList;
    }

    /**
     * Connects to database and returns all info about dessert type by ID.
     *
     * @param id is type of dessert ID value.
     * @return {@link DessertType} if type of dessert data found, null if not.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public DessertType findById(Integer id) throws DaoException {
        DessertType dessertType = new DessertType();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_DESSERT_TYPE_BY_ID)) {
            statement.setInt(DessertTypeDaoImpl.FindDessertTypeIndexID.ID, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                dessertType.setId(resultSet.getInt(DESSERT_TYPE_ID));
                DessertCategory dessertCategory = DessertCategory.valueOf(resultSet.getString(DESSERT_TYPE_CATEGORY));
                dessertType.setCategory(dessertCategory);
                dessertType.setDescription(resultSet.getString(DESSERT_TYPE_DESCRIPTION));
            }
        } catch (SQLException e) {
            throw new DaoException("Can't handle DessertTypeDao.findById request", e);
        }
        return dessertType;
    }

    /**
     * Static class that contains parameter indexes for getting dessert type data by dessertType ID
     */
    private static class FindDessertTypeIndexID {
        private static final int ID = 1;
    }
}
