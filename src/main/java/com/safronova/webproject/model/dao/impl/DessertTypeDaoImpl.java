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

public class DessertTypeDaoImpl implements DessertTypeDao {

    private static final DessertTypeDaoImpl instance = new DessertTypeDaoImpl();

    public static DessertTypeDaoImpl getInstance() {
        return instance;
    }

    private DessertTypeDaoImpl() {}

    private static final String SELECT_ALL_DESSERT_TYPE_SQL =
            "SELECT dt_id, dt_category, dt_description "+
                    "FROM dessert_types";

    private static final String SELECT_DESSERT_TYPE_BY_ID =
            "SELECT dt_id, dt_category, dt_description " +
                    "FROM dessert_types " +
                    "WHERE (dt_id = ?)";


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

    private static class FindDessertTypeIndexID {
        private static final int ID = 1;
    }
}
