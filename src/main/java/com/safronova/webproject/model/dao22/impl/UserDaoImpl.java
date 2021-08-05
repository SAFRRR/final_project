package com.safronova.webproject.model.dao22.impl;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.model.dao22.ResultCode;
import com.safronova.webproject.model.dao22.UserDao;
import com.safronova.webproject.model.entity2.*;
import com.safronova.webproject.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.safronova.webproject.model.dao22.ColumnName.*;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger();
    private static final UserDaoImpl instance = new UserDaoImpl();
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String FIND_ALL_USERS =
            "SELECT u_id, u,name, u_login, u_password, u_email, u_avatar, u_lastName, " +
            "FROM users" +
            "INNER JOIN roles" +
            "ON users.u_role = roles.r_id" +
            "INNER JOIN statuses" +
            "ON users.u_status = statuses.s_id" +
                    "INNER JOIN addresses" +
                    "ON users.u_address = addresses.a_id" +
                    "INNER JOIN orders" +
                    "ON users.u_order = orders.o_id" +
            "WHERE statuses.s_name <> 'DELETED';";

    private static final String FIND_USER_BY_ID =
            "SELECT u_id, u_name, u_login, u_password, u_email, u_avatar, u_lastName, " +
                    "roles.name as role, statuses.name as status " +
                    "FROM users " +
                    "INNER JOIN roles " +
                    "ON users.u_role = roles.r_id" +
                    "INNER JOIN statuses " +
                    "ON users.u_status = statuses.s_id" +
                    "INNER JOIN addresses" +
                    "ON users.u_address = addresses.a_id" +
                    "INNER JOIN orders" +
                    "ON users.u_order = orders.o_id" +
                    "WHERE users.u_id = ?;";

    private static final String FIND_USER_BY_LOGIN =
            "SELECT u_id, u_name, u_login, u_password, u_email, u_avatar, u_lastName, " +
                    "roles.name as role, statuses.name as status " +
                    "FROM users " +
                    "INNER JOIN roles " +
                    "ON users.u_role = roles.r_id" +
                    "INNER JOIN statuses " +
                    "ON users.u_status = statuses.s_id" +
                    "INNER JOIN addresses" +
                    "ON users.u_address = addresses.a_id" +
                    "INNER JOIN orders" +
                    "ON users.u_order = orders.o_id" +
                    "WHERE users.u_login = ?;";


    private static final String FIND_USER_BY_EMAIL = " SELECT `u_id`, `u_name`, `u_login`, `u_password`, `u_email`, `u_role`, `u_address`,`u_avatar`, `u_surname`, `u_order`, `u_status` "+
                    "FROM `users` " +  "WHERE `users`.`u_email` = ?;";
//                    "INNER JOIN roles " +
//                    "ON users.u_role = roles.r_id" +
//                    "INNER JOIN statuses " +
//                    "ON users.u_status = statuses.s_id" +
//                    "INNER JOIN addresses" +
//                    "ON users.u_address = addresses.a_id" +
//                    "INNER JOIN orders" +
//                    "ON users.u_order = orders.o_id" +




    private static final String SIGNUP = "INSERT INTO `users`" +
            "(`u_email`, `u_login`, `u_password_hash`, `u_salt`, `u_image_url`, `u_role`, `u_status`)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

//    private static final String SIGNUP_= "INSERT INTO users" +
//            "(u_name, u_login, u_password, u_email, u_role, u_address, u_avatar, u_lastName, u_order, u_status)" +
//            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String UPDATE_USER= "UPDATE users" +
            "SET u_name = ?, u_login = ?, u_password= ?, " +
            "u_email = ?, u_role = ?, u_address = ?, u_avatar = ?, u_lastName = ?, u_order = ?, u_status = ? " +
            "WHERE u_id = ?;";//u_role = (SELECT r_id FROM roles WHERE r_name = ?)

    private static final String CHANGE_USER_STATUS = "UPDATE users SET u_status = (SELECT s_id FROM statuses WHERE s_name = ?) WHERE u_email = ?;";


    public static UserDaoImpl getInstance(){
        return instance;
    }

    private UserDaoImpl() {
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new LinkedList<>();
        try(Connection connection = ConnectionPool.getInstance().getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS);
            while(resultSet.next()){
                //fix make builder
                int id = resultSet.getInt(USER_ID);
                String login = resultSet.getString(USER_LOGIN);
                String password = resultSet.getString(USER_PASSWORD);
                String name = resultSet.getString(USER_NAME);
                String surName = resultSet.getString(USER_SURNAME);
                Role role = Role.valueOf(resultSet.getString(USER_ROLE));
                Status status = Status.valueOf(resultSet.getString(USER_STATUS));
                int addressId = resultSet.getInt(USER_ADDRESS);//parse
                int orderId = resultSet.getInt(USER_ORDER);
                String profilePicturePath = resultSet.getString(USER_AVATAR);
                String email = resultSet.getString(USER_EMAIL);
                User user = new User(id, login, password, name, surName, role, status, addressId, orderId, profilePicturePath, email);
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error("Can not proceed `{}` request: {}", FIND_ALL_USERS, e);
            throw new DaoException("Can not proceed request: " + FIND_ALL_USERS, e);
        }
        return users;
    }

    @Override
    public Optional<User> findById(int userId) throws DaoException {
       Optional<User> user = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                //fix make builder
                String login = resultSet.getString(USER_LOGIN);
                String password = resultSet.getString(USER_PASSWORD);
                String name = resultSet.getString(USER_NAME);
                String surName = resultSet.getString(USER_SURNAME);
                Role role = Role.valueOf(resultSet.getString(USER_ROLE));
                Status status = Status.valueOf(resultSet.getString(USER_STATUS));
                int addressId = resultSet.getInt(USER_ADDRESS);//parse
                int orderId = resultSet.getInt(USER_ORDER);
                String profilePicturePath = resultSet.getString(USER_AVATAR);
                String email = resultSet.getString(USER_EMAIL);
                user = Optional.of(new User(userId, login, password, name, surName, role, status, addressId, orderId, profilePicturePath, email));
            }
        } catch (SQLException e) {
            logger.error("Can not proceed `{}` request: {}", FIND_USER_BY_ID, e);
            throw new DaoException("Can not proceed request: " + FIND_USER_BY_ID, e);
        }
        return user;
    }

    @Override
    public Optional<User> findByLogin(String userLogin) throws DaoException {
        Optional<User> user = Optional.empty();
        try(Connection connection = ConnectionPool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN);
            preparedStatement.setString(1, userLogin);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int id = resultSet.getInt(USER_ID);
                String password = resultSet.getString(USER_PASSWORD);
                String name = resultSet.getString(USER_NAME);
                String surName = resultSet.getString(USER_SURNAME);
                Role role = Role.valueOf(resultSet.getString(USER_ROLE));
                Status status = Status.valueOf(resultSet.getString(USER_STATUS));
                int addressId = resultSet.getInt(USER_ADDRESS);//parse
                int orderId = resultSet.getInt(USER_ORDER);
                String profilePicturePath = resultSet.getString(USER_AVATAR);
                String email = resultSet.getString(USER_EMAIL);
                user = Optional.of(new User(id, userLogin, password, name, surName, role, status, addressId, orderId, profilePicturePath, email));
            }
        } catch (SQLException e) {
            logger.error("Can not proceed `{}` request: {}", FIND_USER_BY_LOGIN, e);
            throw new DaoException("Can not proceed request: " + FIND_USER_BY_LOGIN, e);
        }
        return user;
    }

//    @Override
//    public Optional<User> findByEmail(String userEmail) throws DaoException {
//        Optional<User> user = Optional.empty();
//        try(Connection connection = ConnectionPool.getInstance().getConnection()){
//            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL);
//            preparedStatement.setString(1, userEmail);// statement.setString(FindUserIndex.EMAIL, email);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if(resultSet.next()){
//                int id = resultSet.getInt(USER_ID);
//                String login = resultSet.getString(USER_LOGIN);
//                String password = resultSet.getString(USER_PASSWORD);
//                String name = resultSet.getString(USER_NAME);
//                String surName = resultSet.getString(USER_SURNAME);
//                Role role = Role.valueOf(resultSet.getString(USER_ROLE));
//                Status status = Status.valueOf(resultSet.getString(USER_STATUS));
//                int addressId = resultSet.getInt(USER_ADDRESS);//parse
//                int orderId = resultSet.getInt(USER_ORDER);
//                String profilePicturePath = resultSet.getString(USER_AVATAR);
//                user = Optional.of(new User(id, login, password, name, surName, role, status, addressId, orderId, profilePicturePath, userEmail));
//            }
//        } catch (SQLException e) {
//            logger.error("Can not proceed `{}` request: {}", FIND_USER_BY_EMAIL, e);
//            throw new DaoException("Can not proceed request: " + FIND_USER_BY_EMAIL, e);//throw new DaoException("Can't handle UserDao.findUserByEmail request", e);
//        }
//        return user;
//    }

    @Override
    public Optional<User> findByEmail(String email) throws DaoException {
        Optional<User> user = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt(USER_ID);
                String loginName = resultSet.getString(USER_LOGIN);
                String imageUrl = resultSet.getString(USER_AVATAR);
                Role role = Role.valueOf(resultSet.getString(USER_ROLE));
                Status status = Status.valueOf(resultSet.getString(USER_STATUS));
                user = Optional.of(new User(userId, email, loginName, imageUrl, role, status));
            }
        } catch (SQLException e) {
            logger.error("Can not proceed `{}` request: {} {}", FIND_USER_BY_EMAIL, e.getMessage(), e.getStackTrace());
            throw new DaoException("Can not proceed request: " + FIND_USER_BY_EMAIL, e);
        }
        return user;
    }


    @Override
    public boolean signIn(String email, String password) throws DaoException {
        return false;
    }





    @Override
    public boolean signUp(String email, String login, String passwordHashHex, String saltHex, String defaultImage, Role defaultRole)  throws DaoException {
        int rowsAdded = 0;
        try (Connection connection = ConnectionPool.getInstance().getConnection(); PreparedStatement statement = connection.prepareStatement(SIGNUP)) {
            statement.setString(1, email);
            statement.setString(2, login);
            statement.setString(3, passwordHashHex);
            statement.setString(4, saltHex);
            statement.setString(5, defaultImage);
            statement.setString(6, defaultRole.getValue());
            statement.setString(7, "CONFIRMATION_AWAITING");
//            statement.setString(7, User.Status.CONFIRMATION_AWAITING.name());
            rowsAdded = statement.executeUpdate();

        } catch (SQLException e) {
            logger.error("Can not proceed `{}` request: {} {}", SIGNUP, e.getMessage(), e.getStackTrace());
            throw new DaoException("Can not proceed request: " + SIGNUP, e);
        }
        return rowsAdded == 1;

    }



//
//    @Override
//    public boolean registerUser(String email, String login, String passwordHashHex, String saltHex, String defaultImage, Role defaultRole)
//            throws DaoException {
//        int rowsAdded = 0;
//        try (Connection connection = ConnectionPool.getInstance().getConnection();
//             PreparedStatement statement = connection.prepareStatement(REGISTER)) {
//            statement.setString(1, email);
//            statement.setString(2, login);
//            statement.setString(3, passwordHashHex);
//            statement.setString(4, saltHex);
//            statement.setString(5, defaultImage);
//            statement.setString(6, defaultRole.getValue());
//            statement.setString(7, User.UserStatus.CONFIRMATION_AWAITING.name());
//            rowsAdded = statement.executeUpdate();
//        } catch (SQLException e) {
//            logger.log(Level.ERROR, "Can not proceed `{}` request: {}", REGISTER, e.getMessage());
//            throw new DaoException("Can not proceed request: " + REGISTER, e);
//        }
//        return rowsAdded == 1;
//    }
//
//    @Override
//    public ResultCode signUp(SignUpData signUpData) throws DaoException {
//        final PasswordEncryptor passwordEncryptor = PasswordEncryptor.getInstance();
//        final String ROLE_USER = String.valueOf(Role.USER);
//        final int DUBLICATE_EMAIL_ERROR_CODE = 1062;
//
//        try (Connection connection = connectionPool.getConnection();
//             PreparedStatement statement = connection.prepareStatement(SIGNUP_SQL)) {
//            statement.setString(SignUpIndex.USERNAME, signUpData.getUsername());
//            statement.setString(SignUpIndex.EMAIL, signUpData.getEmail());
//            statement.setString(SignUpIndex.PASSWORD, passwordEncryptor.getHash(signUpData.getPassword()));
//            statement.setString(SignUpIndex.ROLE, ROLE_USER);
//            statement.setString(SignUpIndex.FIRST_NAME, signUpData.getFirstName());
//            statement.setString(SignUpIndex.LAST_NAME, signUpData.getLastName());
//            statement.setString(SignUpIndex.ADDRESS, signUpData.getAddress());
//            statement.setString(SignUpIndex.PHONE, signUpData.getPhoneNumber());
//            statement.execute();
//            return ResultCode.SUCCESS;
//
//        } catch (SQLException e) {
//            if (e.getErrorCode() == DUBLICATE_EMAIL_ERROR_CODE) {
//                return ResultCode.ERROR_DUPLICATE_EMAIL;
//            } else {
//                throw new DaoException("Can't handle UserDao.signUp request", e);
//            }
//        }
//    }

    @Override
    public ResultCode updateUser(User user) throws DaoException {
        try(Connection connection = ConnectionPool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
//            statement.setString(UpdateIndex.USERNAME, user.getUsername());
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmail());
//            preparedStatement.setInt(6, user.getRole());
            preparedStatement.setInt(6, user.getAddressId());
            preparedStatement.setString(7, user.getImageUrl());
            preparedStatement.setString(8, user.getSurname());
//            preparedStatement.setString(9, user.getStatus());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Can not proceed `{}` request: {}", UPDATE_USER, e);
            throw new DaoException("Can not proceed request: " + UPDATE_USER, e);//throw new DaoException("Can't handle UserDao.findUserByEmail request", e);
        }
        return null;
    }

    @Override
    public boolean changeUserStatus(String email) throws DaoException {
        int rowsAdded = 0;
        try(Connection connection = ConnectionPool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_USER_STATUS);
            preparedStatement.setString(1, "normal_status");
//            preparedStatement.setString(1, Status.CONFIRMED.name());
            preparedStatement.setString(2, email);
            rowsAdded = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error( "Can not proceed `{}` request: {}", CHANGE_USER_STATUS, e);
            throw new DaoException("Can not proceed request: " + CHANGE_USER_STATUS, e);
        }
        return rowsAdded == 1;
    }
}
