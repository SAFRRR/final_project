package com.safronova.webproject.model.dao22;

import com.safronova.webproject.model.dao22.impl.CustomUserDaoImpl;
import com.safronova.webproject.model.dao22.impl.UserDaoImpl;

public class DaoProvider {
    private static final DaoProvider instance = new DaoProvider();
    private static final UserDao userDao = UserDaoImpl.getInstance();
    private static final CustomUserDao customUserDao = CustomUserDaoImpl.getInstance();
//    private static final AlienDao alienDao = AlienDaoImpl.getInstance();
//    private static final RatingDao ratingDao = RatingDaoImpl.getInstance();
    private DaoProvider() {}

    public static DaoProvider getInstance() {
        return instance;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public CustomUserDao getCustomUserDao() {
        return customUserDao;
    }

//    public AlienDao getAlienDao() {
//        return alienDao;
//    }
//
//    public RatingDao getRatingDao() {
//        return ratingDao;
//    }
}
