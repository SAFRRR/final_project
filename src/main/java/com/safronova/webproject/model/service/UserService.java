package com.safronova.webproject.model.service;

import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.dao.ResultCode;
import com.safronova.webproject.model.entity.SignInData;
import com.safronova.webproject.model.entity.SignUpData;
import com.safronova.webproject.model.entity.User;

import java.util.Optional;

/**
 * {@code UserService} is an interface with a bunch of methods that allow to manipulate {@link User}
 *
 * @author Anna Safronova
 */
public interface UserService {
    /**
     * Sign In
     *
     * @param signInData the data for signIn
     * @return {@link User} entity wrapped with {@link Optional}. If credentials are invalid, {@code Optional}
     * @throws ServiceException if an error occurred executing the method.
     */
    Optional<User> signIn(SignInData signInData) throws ServiceException;

    /**
     * Sign up
     *
     * @param signUpData the data for signUp
     * @return {@link ResultCode} enum, that shows the result of the method execution.
     * @throws ServiceException if an error occurred executing the method.
     */
    ResultCode signUp(SignUpData signUpData) throws ServiceException;

    /**
     * Update user
     *
     * @param signInData the data for Sign In
     * @param user is Object of {@link User}, which contains full information about user.
     * @param newPassword value of the new password
     * @param confirmPassword value of the confirm password
     * @return {@link ResultCode} enum, that shows the result of the method execution.
     * @throws ServiceException if an error occurred executing the method.
     */
    ResultCode updateUser(SignInData signInData, User user, String newPassword, String confirmPassword) throws ServiceException;
}





