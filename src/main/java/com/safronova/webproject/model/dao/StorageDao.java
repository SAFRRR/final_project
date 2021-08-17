package com.safronova.webproject.model.dao;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.model.entity.Storage;

/**
 * Interface provides methods to interact with Storage data from database.
 * Methods should connect to database and manipulate with data(save, edit, etc.).
 */
public interface StorageDao {

    /**
     * Connects to database and returns {@link Storage} object by id of dessert.
     *
     * @param dessertId is dessert's id value.
     * @return {@link Storage} if storage data found, null if not.
     * @throws DaoException when problems with database connection occurs.
     */
    Storage findByDessertId(Integer dessertId) throws DaoException;

    /**
     * Connects to database and update storage data.
     *
     * @param storage is {@link Storage} object that contains all info about storage for update.
     * @throws DaoException when problems with database connection occurs.
     */
    void updateStorage(Storage storage) throws DaoException;

    /**
     * Connects to database and add new storage.
     *
     * @param storage is {@link Storage} object that contains all info about storage.
     * @throws DaoException when problems with database connection occurs.
     */
    void insertStorage(Storage storage) throws DaoException;

    /**
     * Connects to database and set count to the storage by dessert ID.
     *
     * @param dessertId is dessert ID value.
     * @param count is count of dessert in storage
     * @throws DaoException when problems with database connection occurs.
     */
    void updateStorageByDessert(Integer dessertId, Integer count) throws DaoException;
}
