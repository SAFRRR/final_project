package com.safronova.webproject.model.dao.impl;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.model.dao.DessertDao;
import com.safronova.webproject.model.dao.ResultCode;
import com.safronova.webproject.model.entity.*;
import com.safronova.webproject.model.pool.ConnectionPool;
import static com.safronova.webproject.model.dao.ColumnName.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DessertDaoImpl implements DessertDao {
    private static final DessertDaoImpl instance = new DessertDaoImpl();

    public static DessertDaoImpl getInstance() {
        return instance;
    }

    private DessertDaoImpl() {
    }

    private static final String SELECT_ALL_DESSERT_SQL =
            "SELECT d_id, d_name, d_description, d_price, d_weight,  d_image " +
                    "FROM desserts";

    private static final String SELECT_ALL_DESSERT_LIST_SQL =
            "SELECT d_id, d_name, d_description, d_price, d_weight, d_image, dt_id, dt_category, dt_description, st_count "+
                    "FROM desserts "+
                    "JOIN dessert_types ON desserts.d_dessert_type_id = dessert_types.dt_id " +
                    "JOIN storages ON desserts.d_id = storages.st_dessert_id";

    private static final String FIND_DESSERT_BY_CATEGORY =
            "SELECT d_id, d_name, d_description,  d_price, d_weight, d_image "+
                    "FROM desserts " +
                    "JOIN dessert_types ON desserts.d_dessert_type_id = dessert_types.dt_id "+
                    "WHERE (d_dessert_type_id = ?)";

    private static final String FIND_DESSERT_BY_ID =
            "SELECT d_id, d_name, d_description, d_price, d_weight, d_image, dt_id, dt_category, dt_description, st_count "+
                    "FROM desserts "+
                    "JOIN dessert_types ON desserts.d_dessert_type_id = dessert_types.dt_id "+
                    "JOIN storages ON desserts.d_id = storages.st_dessert_id "+
                    "WHERE (d_id = ?)";

    private static final String INSERT_DESSERT_SQL =
            "INSERT INTO desserts (d_name, d_description, d_price, d_weight, d_dessert_type_id, d_quantity) "+
                    "VALUES (?,?,?,?,?,?)";

    private static final String SET_IMAGE_SQL =
            "UPDATE desserts "+
                    "SET d_image = ? "+
                    "WHERE d_id = ?";

    private static final String UPDATE_DESSERT_SQL =
            "UPDATE desserts "+
                    "SET d_name = ?, d_description = ?, d_price = ?, d_weight = ?, d_dessert_type_id = ?, d_quantity = ?  "+
                    "WHERE d_id =?";

    private static final String DELETE_DESSERT_SQL =
            "DELETE FROM desserts "+
                    "WHERE d_id = ?";

    private static final String SELECT_NAME_SQL =
            "SELECT d_name " +
                    "FROM desserts " +
                    "WHERE (d_name=?)";

    @Override
    public ResultCode findDessertByName(String name) throws DaoException {
       try (Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_NAME_SQL)) {
           statement.setString(1, name);
           ResultSet resultSet = statement.executeQuery();
           if (resultSet.next()) {
               return ResultCode.ERROR_DUPLICATE_NAME;
           } else {
               return ResultCode.SUCCESS;
           }
       } catch (SQLException e) {
           throw new DaoException("Can't handle DessertDao.findDessertByName request", e);
       }
    }

    @Override
    public Dessert createDessert(Dessert dessert) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_DESSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(DessertDaoImpl.DessertIndex.NAME, dessert.getName());
            statement.setString(DessertDaoImpl.DessertIndex.DESCRIPTION,dessert.getDescription());
            statement.setBigDecimal(DessertDaoImpl.DessertIndex.PRICE, dessert.getPrice());
            statement.setInt(DessertDaoImpl.DessertIndex.WEIGHT, dessert.getWeight());
            statement.setInt(DessertDaoImpl.DessertIndex.TYPE_ID, dessert.getDessertType().getId());
            statement.setInt(DessertDaoImpl.DessertIndex.QUANTITY, dessert.getQuantity());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("Creating dessert failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    dessert.setId(generatedKeys.getInt(1));
                } else {
                    throw new DaoException("Creating dessert failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can't handle DessertDao.createDessert request", e);
        }
        return dessert;
    }

    @Override
    public List<Dessert> findAll() throws DaoException {
        List<Dessert> dessertList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_DESSERT_SQL);
            while (resultSet.next()) {
                Dessert dessert = new Dessert();
                dessert.setId(resultSet.getInt(DESSERT_ID));
                dessert.setName(resultSet.getString(DESSERT_NAME));
                dessert.setDescription(resultSet.getString(DESSERT_DESCRIPTION));
                dessert.setPrice(resultSet.getBigDecimal(DESSERT_PRICE));
                dessert.setDessertImage(resultSet.getString(DESSERT_IMAGE));
                dessertList.add(dessert);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't handle DessertDao.findAll request", e);
        }
        return dessertList;
    }

    @Override
    public List<Dessert> findByCategory(String category) throws DaoException {
        List<Dessert> dessertList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_DESSERT_BY_CATEGORY)) {
            statement.setString(FindDessertIndex.INDEX, category);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Dessert dessert = new Dessert();
                dessert.setId(resultSet.getInt(DESSERT_ID));
                dessert.setName(resultSet.getString(DESSERT_NAME));
                dessert.setDescription(resultSet.getString(DESSERT_DESCRIPTION));
                dessert.setWeight(resultSet.getInt(DESSERT_WEIGHT));
                dessert.setPrice(resultSet.getBigDecimal(DESSERT_PRICE));
                dessert.setDessertImage(resultSet.getString(DESSERT_IMAGE));
                dessertList.add(dessert);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't handle DessertDao.findByCategory request", e);
        }
        return dessertList;
    }

    @Override
    public Dessert findById(Integer dessertId) throws DaoException {
        Dessert dessert = new Dessert();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_DESSERT_BY_ID)) {
            statement.setInt(FindDessertIndex.INDEX, dessertId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                dessert.setId(resultSet.getInt(DESSERT_ID));
                dessert.setName(resultSet.getString(DESSERT_NAME));
                dessert.setDescription(resultSet.getString(DESSERT_DESCRIPTION));
                dessert.setPrice(resultSet.getBigDecimal(DESSERT_PRICE));
                dessert.setDessertImage(resultSet.getString(DESSERT_IMAGE));
                dessert.setWeight(resultSet.getInt(DESSERT_WEIGHT));
                DessertType dessertType = new DessertType();
                dessertType.setId(resultSet.getInt(DESSERT_TYPE_ID));
                DessertCategory dessertCategory = DessertCategory.valueOf(resultSet.getString(DESSERT_TYPE_CATEGORY));
                dessertType.setCategory(dessertCategory);
                dessertType.setDescription(resultSet.getString(DESSERT_TYPE_DESCRIPTION));
                dessert.setDessertType(dessertType);
                Storage storage = new Storage();
                storage.setCount(resultSet.getInt(STORAGE_COUNT));
                dessert.setStorage(storage);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't handle DessertDao.findById request", e);
        }
        return dessert;
    }

    @Override
    public void updateDessertImage(Dessert dbDessert) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SET_IMAGE_SQL)) {
            statement.setString(DessertDaoImpl.UpdateImageIndex.IMAGE, dbDessert.getDessertImage());
            statement.setInt(DessertDaoImpl.UpdateImageIndex.ID, dbDessert.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't handle DessertDao.updateDessertImage request", e);
        }
    }

    @Override
    public List<Dessert> findAllDessertList() throws DaoException {
        List<Dessert> dessertList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_DESSERT_LIST_SQL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Dessert dessert = new Dessert();
                dessert.setId(resultSet.getInt(DESSERT_ID));
                dessert.setName(resultSet.getString(DESSERT_NAME));
                dessert.setDescription(resultSet.getString(DESSERT_DESCRIPTION));
                dessert.setPrice(resultSet.getBigDecimal(DESSERT_PRICE));
                dessert.setDessertImage(resultSet.getString(DESSERT_IMAGE));
                dessert.setWeight(resultSet.getInt(DESSERT_WEIGHT));
                DessertType dessertType = new DessertType();
                dessertType.setId(resultSet.getInt(DESSERT_TYPE_ID));
                DessertCategory dessertCategory = DessertCategory.valueOf(resultSet.getString(DESSERT_TYPE_CATEGORY));
                dessertType.setCategory(dessertCategory);
                dessertType.setDescription(resultSet.getString(DESSERT_TYPE_DESCRIPTION));
                dessert.setDessertType(dessertType);
                Storage storage = new Storage();
                storage.setCount(resultSet.getInt(STORAGE_COUNT));
                dessert.setStorage(storage);
                dessertList.add(dessert);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't handle DessertDao.findAllDessertList request", e);
        }
        return dessertList;
    }

    @Override
    public void updateDessert(Integer id, Dessert  dessert) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_DESSERT_SQL)) {
            statement.setString(DessertDaoImpl.DessertIndex.NAME, dessert.getName());
            statement.setString(DessertDaoImpl.DessertIndex.DESCRIPTION, dessert.getDescription());
            statement.setBigDecimal(DessertDaoImpl.DessertIndex.PRICE, dessert.getPrice());
            statement.setInt(DessertDaoImpl.DessertIndex.WEIGHT, dessert.getWeight());
            statement.setInt(DessertDaoImpl.DessertIndex.TYPE_ID, dessert.getDessertType().getId());
            statement.setInt(DessertDaoImpl.DessertIndex.QUANTITY, dessert.getQuantity());
            statement.setInt(DessertDaoImpl.DessertIndex.ID, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't handle DessertDao.updateDessert request", e);
        }
    }

    @Override
    public void deleteById(Integer id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_DESSERT_SQL)) {
            statement.setInt(DessertDaoImpl.FindDessertIndex.INDEX, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't handle DessertDao.deleteById request", e);
        }
    }

    private static class FindDessertIndex {
        private static final int INDEX = 1;
    }

    private static class UpdateImageIndex {
        private static final int IMAGE = 1;
        private static final int ID = 2;
    }

    private static class DessertIndex {
        private static final int NAME = 1;
        private static final int DESCRIPTION = 2;
        private static final int PRICE = 3;
        private static final int WEIGHT = 4;
        private static final int TYPE_ID = 5;
        private static final int QUANTITY = 6;
        private static final int ID = 7;
    }
}
