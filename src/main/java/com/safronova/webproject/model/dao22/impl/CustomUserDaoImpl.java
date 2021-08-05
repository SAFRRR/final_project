package com.safronova.webproject.model.dao22.impl;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.model.dao22.CustomUserDao;
import com.safronova.webproject.model.dao22.DatabaseFeedback;
import com.safronova.webproject.model.entity2.CustomUser;
import com.safronova.webproject.model.entity2.Token;
import com.safronova.webproject.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import static com.safronova.webproject.model.dao22.ColumnName.*;

public class CustomUserDaoImpl implements CustomUserDao {
    private static final Logger logger = LogManager.getLogger();
    private static final CustomUserDao instance = new CustomUserDaoImpl();

    private static final String FIND_BY_ID =
			"SELECT `user_id`, `email`, `login_name`, `image_url`, `role_type`, `status`, `banned_to_datetime`" +
			"FROM `custom_users`" +
			"WHERE `custom_users`.`user_id` = ?";

    private static final String FIND_BY_LOGIN =
			"SELECT `user_id`, `email`, `login_name`, `image_url`, `role_type`, `status`, `banned_to_datetime`" +
			"FROM `custom_users`" +
			"WHERE `custom_users`.`login_name` = ?";

    private static final String FIND_BY_EMAIL =
			"SELECT `user_id`, `email`, `login_name`, `image_url`, `role_type`, `status`"+
			"FROM `custom_users`" +
            "WHERE `custom_users`.`email` = ?";

    private static final String SIGNUP =
            "INSERT INTO `custom_users`" +
			"(`email`, `login_name`, `password_hash`, `salt`, `image_url`, `role_type`, `status`)" +
			"VALUES (?, ?, ?, ?, ?, ?, ?)";


    private static final String ADD_NEW_TOKEN =
            "INSERT INTO `tokens`" +
			"(`email`, `token`, `expiration_date`, `status`)" +
			"VALUES (?, ?, ?, ?)";

    private static final String ADD_NEW_UPDATE_EMAIL_TOKEN =
            "INSERT INTO `tokens`" +
			"(`email`, `token`, `expiration_date`, `status`, `new_email`)" +
			"VALUES (?, ?, ?, ?, ?)";

    private static final String FIND_USER_LOGIN_DATA =
            "SELECT `password_hash`, `salt`" +
			"FROM `custom_users`" +
			"WHERE `email` = ? AND `status` = ? OR `status` = ?";

    public static CustomUserDao getInstance() {
        return instance;
    }

    @Override
    public Optional<CustomUser> findById(int userId) throws DaoException {
        Optional<CustomUser> user = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String email = resultSet.getString(USER_EMAIL);
                String loginName = resultSet.getString(USER_LOGIN_NAME);
                String imageUrl = resultSet.getString(USER_IMAGE_URL);
                CustomUser.CustomRole role = CustomUser.CustomRole.valueOf(resultSet.getString(USER_ROLE_TYPE));
                CustomUser.CustomStatus status = CustomUser.CustomStatus.valueOf(resultSet.getString(USER_STATUS));
//                Date bannedToDate = resultSet.getDate(USER_BANNED_TO_DATE);
                user = Optional.of(new CustomUser(userId, email, loginName, imageUrl, role, status));
            }
        } catch (SQLException e) {
            logger.error("Can not proceed `{}` request: {} {}", FIND_BY_ID, e.getMessage(), e.getStackTrace());
            throw new DaoException("Can not proceed request: " + FIND_BY_ID, e);
        }
        return user;
    }

    @Override
    public Optional<CustomUser> findByLogin(String userLogin) throws DaoException {
        Optional<CustomUser> user = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN)) {
            statement.setString(1, userLogin);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt(USER_ID);
                String email = resultSet.getString(USER_EMAIL);
                String loginName = resultSet.getString(USER_LOGIN_NAME);
                String imageUrl = resultSet.getString(USER_IMAGE_URL);
                CustomUser.CustomRole role = CustomUser.CustomRole.valueOf(resultSet.getString(USER_ROLE_TYPE));
                CustomUser.CustomStatus status = CustomUser.CustomStatus.valueOf(resultSet.getString(USER_STATUS));
//                Date bannedToDate = resultSet.getDate(USER_BANNED_TO_DATE);
                user = Optional.of(new CustomUser(userId, email, userLogin, imageUrl, role, status));
            }
        } catch (SQLException e) {
            logger.error("Can not proceed `{}` request: {} {}", FIND_BY_LOGIN, e.getMessage(), e.getStackTrace());
            throw new DaoException("Can not proceed request: " + FIND_BY_LOGIN, e);
        }
        return user;
    }

    @Override
    public Optional<CustomUser> findByEmail(String email) throws DaoException {
        Optional<CustomUser> user = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt(USER_ID);
                String loginName = resultSet.getString(USER_LOGIN_NAME);
                String imageUrl = resultSet.getString(USER_IMAGE_URL);
                CustomUser.CustomRole role = CustomUser.CustomRole.valueOf(resultSet.getString(USER_ROLE_TYPE));
                CustomUser.CustomStatus status = CustomUser.CustomStatus.valueOf(resultSet.getString(USER_STATUS));
//                Date bannedToDate = resultSet.getDate(USER_BANNED_TO_DATE);
                user = Optional.of(new CustomUser(userId, email, loginName, imageUrl, role, status));
            }
        } catch (SQLException e) {
            logger.error("Can not proceed `{}` request: {} {}", FIND_BY_EMAIL, e.getMessage(), e.getStackTrace());
            throw new DaoException("Can not proceed request: " + FIND_BY_EMAIL, e);
        }
        return user;
    }


    @Override
    public Map<DatabaseFeedback.Key, Optional<String>> findUserLoginData(String userEmail) throws DaoException {
        Map<DatabaseFeedback.Key, Optional<String>> result = new EnumMap<>(DatabaseFeedback.Key.class);
        result.put(DatabaseFeedback.Key.PASSWORD, Optional.empty());
        result.put(DatabaseFeedback.Key.SALT, Optional.empty());
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_LOGIN_DATA)) {
            statement.setString(1, userEmail);
            statement.setString(2, CustomUser.CustomStatus.NORMAL.name());
            statement.setString(3, CustomUser.CustomStatus.BANNED.name());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result.put(DatabaseFeedback.Key.PASSWORD, Optional.of(resultSet.getString(USER_PASSWORD_HASH)));
                result.put(DatabaseFeedback.Key.SALT, Optional.of(resultSet.getString(USER_SALT)));
            }
        } catch (SQLException e) {
            logger.error("Can not proceed `{}` request: {} {}", FIND_USER_LOGIN_DATA, e.getMessage(), e.getStackTrace());
            throw new DaoException("Can not proceed request: " + FIND_USER_LOGIN_DATA, e);
        }
        return result;
    }


    @Override
    public boolean signUpUser(String email, String login, String passwordHashHex, String saltHex, String defaultImage, CustomUser.CustomRole defaultRole) throws DaoException {
        int rowsAdded = 0;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SIGNUP)) {
            statement.setString(1, email);
            statement.setString(2, login);
            statement.setString(3, passwordHashHex);
            statement.setString(4, saltHex);
            statement.setString(5, defaultImage);
            statement.setString(6, defaultRole.getValue());
            statement.setString(7, CustomUser.CustomStatus.CONFIRMATION_AWAITING.name());
            rowsAdded = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not proceed `{}` request: {} {}", SIGNUP, e.getMessage(), e.getStackTrace());
            throw new DaoException("Can not proceed request: " + SIGNUP, e);
        }
        return rowsAdded == 1;
    }

    @Override
    public boolean addNewToken(String email, String token, String expirationDate) throws DaoException {
        int rowsAdded = 0;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_NEW_TOKEN)) {
            statement.setString(1, email);
            statement.setString(2, token);
            statement.setString(3, expirationDate);
            statement.setString(4, Token.Status.NORMAL.name());
            rowsAdded = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not proceed `{}` request: {} {}", ADD_NEW_TOKEN, e.getMessage(), e.getStackTrace());
            throw new DaoException("Can not proceed request: " + ADD_NEW_TOKEN, e);
        }
        return rowsAdded == 1;
    }

    @Override
    public boolean addNewToken(String email, String token, String expirationDate, String newEmail) throws DaoException {
        int rowsAdded = 0;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_NEW_UPDATE_EMAIL_TOKEN)) {
            statement.setString(1, email);
            statement.setString(2, token);
            statement.setString(3, expirationDate);
            statement.setString(4, Token.Status.NORMAL.name());
            statement.setString(5, newEmail);
            rowsAdded = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not proceed `{}` request: {} {}", ADD_NEW_UPDATE_EMAIL_TOKEN, e.getMessage(), e.getStackTrace());
            throw new DaoException("Can not proceed request: " + ADD_NEW_UPDATE_EMAIL_TOKEN, e);
        }
        return rowsAdded == 1;
    }


}
