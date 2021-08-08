package com.safronova.webproject.model.service;

import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.dao.ResultCode;
import com.safronova.webproject.model.entity.SignInData;
import com.safronova.webproject.model.entity.SignUpData;
import com.safronova.webproject.model.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> signIn(SignInData signInData) throws ServiceException;

    ResultCode signUp(SignUpData signUpData) throws ServiceException;

    ResultCode updateUser(SignInData signInData, User user, String newPassword, String confirmPassword) throws ServiceException;
}





