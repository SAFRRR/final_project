package com.safronova.webproject.model.dao.impl;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.model.dao.DessertDao;
import com.safronova.webproject.model.entity.Dessert;
import com.safronova.webproject.model.entity.DessertCategory;
import com.safronova.webproject.model.entity.DessertType;
import com.safronova.webproject.model.entity.Storage;
import com.safronova.webproject.model.pool.ConnectionPool;

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

    private static final String SELECT_ALL_FLOWER_SQL =
            "SELECT id, name, description, price, flower_image " +
                    "FROM flower";

    private static final String SELECT_ALL_FLOWER_LIST_SQL =
            "SELECT id, name, description, price, flower_image, soil, origin, light, watering, type_id, category, type_description, storage_count " +
                    "FROM flower flowers " +
                    "JOIN flower_type type ON flowers.flower_type_id = type.type_id " +
                    "JOIN storage s ON flowers.id = s.flowers_id ";

    private static final String FIND_FLOWER_BY_CATEGORY =
            "SELECT id, name, description, price, flower_image " +
                    "FROM flower flowers " +
                    "JOIN flower_type type ON flowers.flower_type_id = type.type_id " +
                    "WHERE (flower_type_id = ?)";

    private static final String FIND_FLOWER_BY_ID =
            "SELECT id, name, description, price, flower_image, soil, origin, light, watering, type_id, category, type_description, storage_count " +
                    "FROM flower flowers " +
                    "JOIN flower_type type ON flowers.flower_type_id = type.type_id " +
                    "JOIN storage s ON flowers.id = s.flowers_id " +
                    "WHERE (id = ?)";

    private static final String INSERT_FLOWER_SQL =
            "INSERT INTO flower (name, description, price, soil, origin, light, flower_type_id, watering) " +
                    "VALUES (?,?,?,?,?,?,?,?)";

    private static final String SET_IMAGE_SQL =
            "UPDATE flower " +
                    "SET flower_image = ? " +
                    "WHERE id = ?";

    private static final String UPDATE_FLOWER_SQL =
            "UPDATE flower " +
                    "SET name = ?, description = ?, price = ?, soil = ?, origin = ?, light = ?, flower_type_id = ?, watering = ? " +
                    "WHERE id = ?";

    private static final String DELETE_FLOWER_SQL =
            "DELETE FROM flower " +
                    "WHERE id = ?";


    @Override
    public Dessert createFlower(Dessert dessert) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_FLOWER_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(FlowerDaoImpl.FlowerIndex.NAME, flower.getName());
            statement.setString(FlowerDaoImpl.FlowerIndex.DESCRIPTION, flower.getDescription());
            statement.setDouble(FlowerDaoImpl.FlowerIndex.PRICE, flower.getPrice());
            statement.setString(FlowerDaoImpl.FlowerIndex.SOIL, String.valueOf(flower.getSoil()));
            statement.setString(FlowerDaoImpl.FlowerIndex.ORIGIN, flower.getOriginCountry());
            statement.setBoolean(FlowerDaoImpl.FlowerIndex.LIGHT, flower.isLight());
            statement.setInt(FlowerDaoImpl.FlowerIndex.TYPE_ID, flower.getFlowerType().getId());
            statement.setInt(FlowerDaoImpl.FlowerIndex.WATERING, flower.getWatering());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("Creating flower failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    dessert.setId(generatedKeys.getInt(1));
                } else {
                    throw new DaoException("Creating flower failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can't handle FlowerDao.createFlower request", e);
        }
        return dessert;
    }

    @Override
    public List<Dessert> findAll() throws DaoException {
        List<Dessert> dessertList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_FLOWER_SQL);
            while (resultSet.next()) {
                Dessert dessert = new Dessert();
                dessert.setId(resultSet.getInt(FLOWER_ID));
                dessert.setName(resultSet.getString(FLOWER_NAME));
                dessert.setDescription(resultSet.getString(FLOWER_DESCRIPTION));
                dessert.setPrice(resultSet.getDouble(FLOWER_PRICE));
                dessert.setDessertImage(resultSet.getString(FLOWER_IMAGE));
                dessertList.add(dessert);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't handle FlowerDao.findAll request", e);
        }
        return dessertList;
    }

    @Override
    public List<Dessert> findByCategory(String category) throws DaoException {
        List<Dessert> dessertList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_FLOWER_BY_CATEGORY)) {
            statement.setString(FindFlowerIndex.INDEX, category);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Dessert dessert = new Dessert();
                dessert.setId(resultSet.getInt(FLOWER_ID));
                dessert.setName(resultSet.getString(FLOWER_NAME));
                dessert.setDescription(resultSet.getString(FLOWER_DESCRIPTION));
                dessert.setPrice(resultSet.getDouble(FLOWER_PRICE));
                dessert.setFlowerImage(resultSet.getString(FLOWER_IMAGE));
                dessertList.add(dessertr);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't handle FlowerDao.findByCategory request", e);
        }
        return dessertList;
    }

    @Override
    public Dessert findById(Integer dessertId) throws DaoException {
        Dessert dessert = new Dessert();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_FLOWER_BY_ID)) {
            statement.setInt(FindFlowerIndex.INDEX, flowerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                dessert.setId(resultSet.getInt(FLOWER_ID));
                dessert.setName(resultSet.getString(FLOWER_NAME));
                dessert.setDescription(resultSet.getString(FLOWER_DESCRIPTION));
                dessert.setPrice(resultSet.getDouble(FLOWER_PRICE));
                dessert.setFlowerImage(resultSet.getString(FLOWER_IMAGE));
                dessert.setOriginCountry(resultSet.getString(FLOWER_COUNTRY));
                dessert.setLight(resultSet.getBoolean(FLOWER_LIGHT));
                dessert.setWatering(resultSet.getInt(FLOWER_WATERING));
                DessertType dessertType = new DessertType();
                dessertType.setId(resultSet.getInt(FLOWER_TYPE_ID));
                DessertCategory dessertCategory = DessertCategory.valueOf(resultSet.getString(FLOWER_TYPE_CATEGORY));
                dessertType.setCategory(dessertCategory);
                dessertType.setDescription(resultSet.getString(FLOWER_TYPE_DESCRIPTION));
                dessert.setFlowerType(flowerType);
                Soil soil = Soil.valueOf(resultSet.getString(SOIL));
                dessert.setSoil(soil);
                Storage storage = new Storage();
                storage.setCount(resultSet.getInt(STORAGE_COUNT));
                dessert.setStorage(storage);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't handle FlowerDao.findById request", e);
        }
        return dessert;
    }

    @Override
    public void updateDessertImage(Dessert dbDessert) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SET_IMAGE_SQL)) {
            statement.setString(DessertDaoImpl.UpdateImageIndex.IMAGE, dbFlower.getFlowerImage());
            statement.setInt(DessertDaoImpl.UpdateImageIndex.ID, dbFlower.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't handle FlowerDao.updateFlowerImage request", e);
        }
    }

    @Override
    public List<Dessert> findAllDessertList() throws DaoException {
        List<Dessert> dessertList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_FLOWER_LIST_SQL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Dessert dessert = new Dessert();
                dessert.setId(resultSet.getInt(FLOWER_ID));
                dessert.setName(resultSet.getString(FLOWER_NAME));
                dessert.setDescription(resultSet.getString(FLOWER_DESCRIPTION));
                dessert.setPrice(resultSet.getDouble(FLOWER_PRICE));
                dessert.setDessertImage(resultSet.getString(FLOWER_IMAGE));
                dessert.setOriginCountry(resultSet.getString(FLOWER_COUNTRY));
                dessert.setLight(resultSet.getBoolean(FLOWER_LIGHT));
                dessert.setWatering(resultSet.getInt(FLOWER_WATERING));
                DessertType dessertType = new DessertType();
                dessertType.setId(resultSet.getInt(FLOWER_TYPE_ID));
                DessertCategory dessertCategory = DessertCategory.valueOf(resultSet.getString(FLOWER_TYPE_CATEGORY));
                dessertType.setCategory(dessertCategory);
                dessertType.setDescription(resultSet.getString(FLOWER_TYPE_DESCRIPTION));
                dessert.setDessertType(dessertType);
                Soil soil = Soil.valueOf(resultSet.getString(SOIL));
                dessert.setSoil(soil);
                Storage storage = new Storage();
                storage.setCount(resultSet.getInt(STORAGE_COUNT));
                dessert.setStorage(storage);
                dessertList.add(dessert);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't handle FlowerDao.findAllFlowerList request", e);
        }
        return dessertList;
    }

    @Override
    public void updateDessert(Integer id, Dessert  dessert) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_FLOWER_SQL)) {
            statement.setString(DessertDaoImpl.FlowerIndex.NAME, dessert.getName());
            statement.setString(DessertDaoImpl.FlowerIndex.DESCRIPTION, dessert.getDescription());
            statement.setDouble(DessertDaoImpl.FlowerIndex.PRICE, dessert.getPrice());
            statement.setString(DessertDaoImpl.FlowerIndex.SOIL, String.valueOf(dessert.getSoil()));
            statement.setString(DessertDaoImpl.FlowerIndex.ORIGIN, dessert.getOriginCountry());
            statement.setBoolean(DessertDaoImpl.FlowerIndex.LIGHT, dessert.isLight());
            statement.setInt(DessertDaoImpl.FlowerIndex.TYPE_ID, dessert.getFlowerType().getId());
            statement.setInt(DessertDaoImpl.FlowerIndex.WATERING, dessert.getWatering());
            statement.setInt(DessertDaoImpl.FlowerIndex.ID, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't handle FlowerDao.updateFlower request", e);
        }
    }

    @Override
    public void deleteById(Integer id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_FLOWER_SQL)) {
            statement.setInt(DessertDaoImpl.FindFlowerIndex.INDEX, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't handle FlowerDao.deleteById request", e);
        }
    }

    private static class FindDessertIndex {
        private static final int INDEX = 1;
    }

    /**
     * Static class that contains parameter indexes for updating flower image
     */
    private static class UpdateImageIndex {
        private static final int IMAGE = 1;
        private static final int ID = 2;
    }

    /**
     * Static class that contains parameter indexes for inserting flower data
     */
    private static class DessertIndex {
        private static final int NAME = 1;
        private static final int DESCRIPTION = 2;
        private static final int PRICE = 3;
        private static final int SOIL = 4;
        private static final int ORIGIN = 5;
        private static final int LIGHT = 6;
        private static final int TYPE_ID = 7;
        private static final int WATERING = 8;
        private static final int ID = 9;
    }
}
