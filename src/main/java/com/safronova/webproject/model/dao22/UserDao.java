package com.safronova.webproject.model.dao22;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.model.entity2.*;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> findAll() throws DaoException;
    Optional<User> findById(int userId) throws DaoException;
    Optional<User> findByLogin(String userLogin) throws DaoException;
    Optional<User> findByEmail(String userEmail) throws DaoException;
    //
    boolean signIn(String email, String password) throws DaoException; //passwordHash
    //FIX
//    boolean registerUser(String email, String login, String passwordHashHex, String saltHex, String defaultImage, Role defaultRole) throws DaoException;
    boolean signUp(String email, String login, String passwordHashHex, String saltHex, String defaultImag, Role defaultRole) throws DaoException;
    //update
//    boolean updateUserEmail(String email, int userId) throws DaoException;
//    boolean updateUserLogin(String login, int userId) throws DaoException;
//    boolean updateUserPassword(String hashedPassword, int userId) throws DaoException;
//    boolean updateProfileImage(String newFileName, int userId) throws DaoException;
    ResultCode updateUser(User user) throws DaoException;//boolean
    boolean changeUserStatus(String email) throws DaoException;
}
