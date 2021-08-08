package com.safronova.webproject.model.service;

import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.entity.Dessert;
import com.safronova.webproject.model.entity.Storage;

public interface StorageService {
    Storage findByDessertId(String dessertId) throws ServiceException;

    void createStorage(Dessert dessert, String count) throws ServiceException;

    void updateStorage(String dessertId, String count) throws ServiceException;
}
