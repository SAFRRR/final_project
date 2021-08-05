package com.safronova.webproject.model.dao;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.model.entity.DessertType;

import java.util.List;

public interface DessertTypeDao {
    List<DessertType> findAll() throws DaoException;

    DessertType findById(Integer id) throws DaoException;
}

