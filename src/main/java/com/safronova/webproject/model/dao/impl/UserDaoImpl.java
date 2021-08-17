package com.safronova.webproject.model.dao.impl;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.model.dao.ResultCode;
import com.safronova.webproject.model.dao.UserDao;
import com.safronova.webproject.model.entity.Role;
import com.safronova.webproject.model.entity.SignInData;
import com.safronova.webproject.model.entity.SignUpData;
import com.safronova.webproject.model.entity.User;
import com.safronova.webproject.model.pool.ConnectionPool;
import com.safronova.webproject.model.util.PasswordEncryptor;
import static com.safronova.webproject.model.dao.ColumnName.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Implementation of {@link UserDao}. Provides methods to interact with Users data from database.
 * Methods connect to database using {@link Connection} from {@link ConnectionPool} and manipulate with data(save, edit, etc.).
 */
public class UserDaoImpl implements UserDao {

    /**
     * A single instance of the class (pattern Singleton)
     */
    private static final UserDaoImpl instance = new UserDaoImpl();

    /** Query for database to sign up new user */
    private static final String SIGNUP_SQL =
            "INSERT INTO users (u_login, u_password, u_email, u_role, u_name, u_surname, u_address, u_phone)"+
                    " VALUES (?,?,?,?,?,?,?,?)";

    /** Query for database to get user by email */
    private static final String GET_USER_BY_EMAIL_SQL =
            "SELECT u_id, u_email, u_login, u_password, u_role, u_name, u_surname, u_address, u_phone "+
                    "FROM users " +
                    "WHERE u_email = ?";

    /** Query for database to set password by user ID */
    private static final String SET_PASSWORD_BY_ID_SQL =
            "UPDATE users " +
                    "SET u_password = ?" +
                    " WHERE u_id = ?";

    /** Query for database to update user data by user ID */
    private static final String UPDATE_USER_BY_ID_SQL =
            "UPDATE users " +
                    "SET u_login = ?, u_password = ?, u_name = ?, u_surname = ?, u_address = ?, u_phone = ? " +
                    "WHERE u_id = ?";

    /**
     * Returns the instance of the class
     * @return Object of {@link UserDaoImpl}
     */
    public static UserDaoImpl getInstance() {
        return instance;
    }

    /** Private constructor without parameters */
    private UserDaoImpl() {
    }

    /**
     * Connects to database, checks the credentials and returns an User object if success.
     *
     * @param signInData is Object of {@link SignInData}, which contains information about user's email and password.
     * @return {@link Optional<User>} if user's data exists and password matches, empty optional if user's username and password are not correct.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public Optional<User> signIn(SignInData signInData) throws DaoException {
        final PasswordEncryptor passwordEncryptor = PasswordEncryptor.getInstance();
        Optional<User> optionalUser;
        User user = new User();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_USER_BY_EMAIL_SQL)) {
            statement.setString(UserDaoImpl.FindUserIndex.EMAIL, signInData.getEmail());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String hashedPassword = resultSet.getString(USERS_PASSWORD);
                if (!passwordEncryptor.checkHash(signInData.getPassword(), hashedPassword)) {
                    return Optional.empty();
                }
                user.setId(resultSet.getInt(USERS_ID));
                user.setUsername(resultSet.getString(USERS_USERNAME));
                user.setPassword(resultSet.getString(USERS_PASSWORD));
                user.setEmail(resultSet.getString(USERS_EMAIL));
                user.setRole(Role.valueOf(resultSet.getString(USERS_ROLE)));
                user.setFirstName(resultSet.getString(USERS_FIRST_NAME));
                user.setLastName(resultSet.getString(USERS_LAST_NAME));
                user.setAddress(resultSet.getString(USERS_ADDRESS));
                user.setPhone(resultSet.getString(USERS_PHONE));
                optionalUser = Optional.of(user);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("Can't handle UserDao.signIn request", e);
        }
        return optionalUser;
    }

    /**
     *  Connects to database, creates new user by data provided and returns {@link ResultCode} object as result.
     *
     * @param signUpData Object of {@link SignUpData}, which contains user information.
     * @return {@link ResultCode} enum, that shows the result of the method execution.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public ResultCode signUp(SignUpData signUpData) throws DaoException {
        final String passwordEncryptor =  PasswordEncryptor.getInstance().getHash(signUpData.getPassword());
        final String ROLE_USER = String.valueOf(Role.USER);
        final int DUBLICATE_EMAIL_ERROR_CODE = 1062;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SIGNUP_SQL)) {
            statement.setString(UserDaoImpl.SignUpIndex.USERNAME, signUpData.getUsername());
            statement.setString(UserDaoImpl.SignUpIndex.EMAIL, signUpData.getEmail());
            statement.setString(UserDaoImpl.SignUpIndex.PASSWORD, passwordEncryptor);
            statement.setString(UserDaoImpl.SignUpIndex.ROLE, ROLE_USER);
            statement.setString(UserDaoImpl.SignUpIndex.FIRST_NAME, signUpData.getFirstName());
            statement.setString(UserDaoImpl.SignUpIndex.LAST_NAME, signUpData.getLastName());
            statement.setString(UserDaoImpl.SignUpIndex.ADDRESS, signUpData.getAddress());
            statement.setString(UserDaoImpl.SignUpIndex.PHONE, signUpData.getPhoneNumber());
            statement.execute();
            return ResultCode.SUCCESS;
        } catch (SQLException e) {
            if (e.getErrorCode() == DUBLICATE_EMAIL_ERROR_CODE) {
                return ResultCode.ERROR_DUPLICATE_EMAIL;
            } else {
                throw new DaoException("Can't handle UserDao.signUp request", e);
            }
        }
    }

    /**
     *  Connects to database and sets new password to user by ID.
     *
     * @param id is user's ID value.
     * @param password is text that contains new password.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public void setPasswordById(Integer id, String password) throws DaoException {
        final PasswordEncryptor passwordEncryptor = PasswordEncryptor.getInstance();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SET_PASSWORD_BY_ID_SQL)) {
            statement.setString(UserDaoImpl.SetPasswordIndex.PASSWORD, passwordEncryptor.getHash(password));
            statement.setInt(UserDaoImpl.SetPasswordIndex.ID, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't handle UserDao.setPasswordById request", e);
        }
    }

    /**
     * Connects to database, update user's data and returns {@link ResultCode} object
     *
     * @param user is Object of {@link User}, which contains full information about user.
     * @return {@link ResultCode} enum, that shows the result of the method execution.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public ResultCode updateUser(User user) throws DaoException {
        final PasswordEncryptor passwordEncryptor = PasswordEncryptor.getInstance();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_BY_ID_SQL)) {
            statement.setString(UserDaoImpl.UpdateIndex.USERNAME, user.getUsername());
            statement.setString(UserDaoImpl.UpdateIndex.PASSWORD, passwordEncryptor.getHash(user.getPassword()));
            statement.setString(UserDaoImpl.UpdateIndex.FIRST_NAME, user.getFirstName());
            statement.setString(UserDaoImpl.UpdateIndex.LAST_NAME, user.getLastName());
            statement.setString(UserDaoImpl.UpdateIndex.ADDRESS, user.getAddress());
            statement.setString(UserDaoImpl.UpdateIndex.PHONE, user.getPhone());
            statement.setInt(UserDaoImpl.UpdateIndex.ID, user.getId());
            statement.execute();
            return ResultCode.SUCCESS;
        } catch (SQLException e) {
            throw new DaoException("Can't handle UserDao.updateUser request", e);
        }
    }

    /**
     * Static class that contains parameter indexes for sign up
     */
    private static class SignUpIndex {
        private static final int USERNAME = 1;
        private static final int PASSWORD = 2;
        private static final int EMAIL = 3;
        private static final int ROLE = 4;
        private static final int FIRST_NAME = 5;
        private static final int LAST_NAME = 6;
        private static final int ADDRESS = 7;
        private static final int PHONE = 8;
    }

    /**
     * Static class that contains parameter indexes for setting password by user ID
     */
    private static class SetPasswordIndex {
        private static final int PASSWORD = 1;
        private static final int ID = 2;
    }

    /**
     * Static class that contains parameter indexes for updating user data by user ID
     */
    private static class UpdateIndex {
        private static final int USERNAME = 1;
        private static final int PASSWORD = 2;
        private static final int FIRST_NAME = 3;
        private static final int LAST_NAME = 4;
        private static final int ADDRESS = 5;
        private static final int PHONE = 6;
        private static final int ID = 7;
    }

    /**
     * Static class that contains parameter indexes for getting user data by user ID
     */
    private static class FindUserIndex {
        private static final int EMAIL = 1;
    }
}
