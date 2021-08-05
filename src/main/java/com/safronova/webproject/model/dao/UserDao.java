package com.safronova.webproject.model.dao;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.model.entity.SignInData;
import com.safronova.webproject.model.entity.SignUpData;
import com.safronova.webproject.model.entity.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> signIn(SignInData signInData) throws DaoException;

    ResultCode signUp(SignUpData signUpData) throws DaoException;

    User findUserByEmail(String email) throws DaoException;

    void setPasswordById(Integer id, String password) throws DaoException;

    ResultCode updateUser(User user) throws DaoException;
}
