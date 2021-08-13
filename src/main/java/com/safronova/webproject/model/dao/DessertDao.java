package com.safronova.webproject.model.dao;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.model.entity.Dessert;

import java.util.List;

public interface DessertDao {
    List<Dessert> findAll() throws DaoException;

    List<Dessert> findByCategory(String category) throws DaoException;

    ResultCode findDessertByName(String name) throws DaoException;

    Dessert findById(Integer dessertId) throws DaoException;

    Dessert createDessert(Dessert dessert) throws DaoException;

    void updateDessertImage(Dessert dbDessert) throws DaoException;

    List<Dessert> findAllDessertList() throws DaoException;

    void updateDessert(Integer id, Dessert dessert) throws DaoException;

    void deleteById(Integer id) throws DaoException;

}
