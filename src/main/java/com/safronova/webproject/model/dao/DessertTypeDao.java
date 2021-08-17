package com.safronova.webproject.model.dao;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.model.entity.DessertType;

import java.util.List;

/**
 * Interface provides methods to interact with DessertType data from database.
 * Methods should connect to database and manipulate with data(save, edit, etc.).
 */
public interface DessertTypeDao {

    /**
     * Connects to database and returns list of all dessert types.
     *
     * @return List of {@link DessertType} with all type of desserts.
     * @throws DaoException when problems with database connection occurs.
     */
    List<DessertType> findAll() throws DaoException;

    /**
     * Connects to database and returns all info about dessert type by ID.
     *
     * @param id is type of dessert ID value.
     * @return {@link DessertType} if type of dessert data found, null if not.
     * @throws DaoException when problems with database connection occurs.
     */
    DessertType findById(Integer id) throws DaoException;
}

