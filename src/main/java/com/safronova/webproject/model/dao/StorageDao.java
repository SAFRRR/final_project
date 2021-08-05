package com.safronova.webproject.model.dao;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.model.entity.Storage;

public interface StorageDao {
    Storage findByDessertId(Integer dessertId) throws DaoException;

    void updateStorage(Storage storage) throws DaoException;

    void insertStorage(Storage storage) throws DaoException;

    void updateStorageByDessert(Integer dessertId, Integer count) throws DaoException;
}
