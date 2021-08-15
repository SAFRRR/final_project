package com.safronova.webproject.model.service;

import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.dao.ResultCode;
import com.safronova.webproject.model.entity.Dessert;
import com.safronova.webproject.model.entity.DessertType;

import java.util.List;

public interface DessertService {
    List<Dessert> findAll() throws ServiceException;

    List<Dessert> findByCategory(String category) throws ServiceException;

    Dessert findById(String dessertId) throws ServiceException;

    ResultCode findDessertByName(String name) throws ServiceException;

    List<Dessert> findAllDessertList() throws ServiceException;

    Dessert createDessert(String nameDessert, String description, String price,  String weight,  DessertType dessertType, String quantity) throws ServiceException;

    void updateDessert(String dessertId, String nameDessert, String description, String price, String weight, DessertType dessertType, String quantity) throws ServiceException;

    void deleteDessertById(String dessertId) throws ServiceException;
}
