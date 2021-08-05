package com.safronova.webproject.model.dao22;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.model.entity2.CustomUser;

import java.util.Map;
import java.util.Optional;

public interface CustomUserDao {

    Optional<CustomUser> findById(int userId) throws DaoException;

    Optional<CustomUser> findByLogin(String userLogin) throws DaoException;

    Optional<CustomUser> findByEmail(String email) throws DaoException;

    boolean signUpUser(String email, String login, String passwordHashHex, String saltHex, String defaultImage, CustomUser.CustomRole defaultRole) throws DaoException;

    boolean addNewToken(String email, String token, String expirationDate, String newEmail) throws DaoException;

    boolean addNewToken(String email, String token, String expirationDate) throws DaoException;

    Map<DatabaseFeedback.Key, Optional<String>> findUserLoginData(String userEmail) throws DaoException;
//    Map<DatabaseFeedback.Key, Optional<String>> findUserLoginData(String userEmail) throws DaoException;
//    Map<DatabaseFeedback.Key, Optional<String>> findUserLoginData(int userId) throws DaoException;


//    boolean registerUser(String email, String login, String passwordHashHex, String saltHex, String defaultImage,
//                         Role defaultRole) throws DaoException;


//    boolean updateUserEmail(String email, String newEmail) throws DaoException;
//    boolean updateUserLogin(String login, int userId) throws DaoException;
//    boolean updateUserPassword(String hashedPassword, int userId) throws DaoException;
//    boolean updateProfileImage(String imageUrl, int userId) throws DaoException;


//    boolean banUser(String userLogin, String banDate) throws DaoException;
//    boolean unbanUser(String userLogin, String unbanDate) throws DaoException;
//
//
//    boolean promoteUser(String userLogin) throws DaoException;
//
//
//    boolean demoteAdmin(String adminLogin) throws DaoException;
//
//
//    boolean addNewComment(int userId, int alienId, String newComment) throws DaoException;
//
//
//    boolean deleteComment(int commentId) throws DaoException;
//
//
//    boolean deleteComment(int commentId, int userId) throws DaoException;


//
//
//

//
//
//    boolean updateUserStatusToNormal(String email) throws DaoException;
//
//
//    Optional<Token> findToken(String tokenRequestedContent, Token.Status status) throws DaoException;
//
//
//    boolean setTokenStatusExpired(String tokenRequestedContent) throws DaoException;

}
