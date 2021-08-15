package com.safronova.webproject.model.service.impl;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.dao.DaoProvider;
import com.safronova.webproject.model.dao.ResultCode;
import com.safronova.webproject.model.dao.UserDao;
import com.safronova.webproject.model.entity.SignInData;
import com.safronova.webproject.model.entity.SignUpData;
import com.safronova.webproject.model.entity.User;
import com.safronova.webproject.model.service.UserService;
import com.safronova.webproject.model.validator.UserValidator;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final DaoProvider daoProvider = DaoProvider.getInstance();
    private static final UserDao userDao = daoProvider.getUserDao();

    @Override
    public ResultCode signUp(SignUpData signUpData) throws ServiceException {
        if (!UserValidator.validate(signUpData)) {
            throw new ServiceException("User data didn't passed validation");
        } else {
            try {
                return userDao.signUp(signUpData);
            } catch (DaoException e) {
                throw new ServiceException("Can't handle signUp request at UserService", e);
            }
        }
    }

    @Override
    public Optional<User> signIn(SignInData signInData) throws ServiceException {
        if (!UserValidator.validateEmail(signInData.getEmail()) || !UserValidator.validatePassword(signInData.getPassword())) {
            throw new ServiceException("User data didn't passed validation");
        } else {
            try {
                return userDao.signIn(signInData);
            } catch (DaoException e) {
                throw new ServiceException("Can't handle signIn request at UserService", e);
            }
        }
    }

    @Override
    public ResultCode updateUser(SignInData signInData, User user, String newPassword, String confirmPassword) throws ServiceException {
        if (!signIn(signInData).isPresent()) {
            return ResultCode.WRONG_PASSWORD;
        }
        if (!UserValidator.validate(user)) {
            throw new ServiceException("User data didn't passed validation");
        }
        try {
            User userForUpdate;
            if (!newPassword.equals(confirmPassword)) {
                return ResultCode.WRONG_CONFIRMATION;
            } else {
                userForUpdate = createUser(user, newPassword, confirmPassword);
            }
            return userDao.updateUser(userForUpdate);
        } catch (DaoException e) {
            throw new ServiceException("Can't handle updateUser request at UserService", e);
        }
    }

    private User createUser(User user, String newPassword, String confirmPassword) {
        if (newPassword.equals(confirmPassword) && !newPassword.isEmpty()) {
            user.setPassword(newPassword);
        }
        return user;
    }
}
