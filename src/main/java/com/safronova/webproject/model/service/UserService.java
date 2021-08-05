package com.safronova.webproject.model.service;

import com.safronova.webproject.controller22.command2222.Feedback;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.entity2.CustomUser;
import com.safronova.webproject.model.util.localization.LocaleAttribute;

import java.util.Map;
import java.util.Optional;

public interface UserService {

    Map<Feedback.Key, Object> signInUser(String email, String password) throws ServiceException;
    Map<Feedback.Key, Object> signUpUser(String email, String login, String password, String passwordRepeat, LocaleAttribute locale) throws ServiceException;

    Optional<CustomUser> findUserByEmail(String email) throws ServiceException;
}





//    Map<Feedback.Key, Object> authorizeUser(String email, String password) throws ServiceException;
//
//    Optional<User> findUserByEmail(String email) throws ServiceException;


//    Optional<User> findByLogin(String login) throws ServiceException;
//
//    Map<Feedback.Key, Object> makeRequestForNewEmail(String email, String newEmail, int userId, LocaleAttribute locale)
//            throws ServiceException;
//
//
//    Map<Feedback.Key, Object> changeLogin(String login, String newLogin, int userId) throws ServiceException;
//
//    Map<Feedback.Key, Object> changePassword(String password, String passwordConfirm, int userId)
//            throws ServiceException;
//
//
//    Map<Feedback.Key, Object> updateImage(String serverDeploymentPath, Part userImage, int userId)
//            throws ServiceException;
//
//
//    Map<Feedback.Key, Object> addNewComment(int currentUserId, String alienIdString, String newComment)
//            throws ServiceException;
//
//
//    boolean deleteComment(String commentIdString, User currentUser) throws ServiceException;
//
//
//    Map<Feedback.Key, Object> suggestNewAlien(String alienName, String alienSmallDescription,
//                                              String alienFullDescription, Part alienImage, String serverDeploymentPath) throws ServiceException;
//
//
//    Map<Feedback.Key, Object> suggestNewAlienImage(String alienName, Part alienImage, String serverDeploymentPath)
//            throws ServiceException;
//
//
//    boolean setNewEmail(String tokenRequested) throws ServiceException;
//
//
//    boolean activateAccount(String tokenRequested) throws ServiceException;


