package com.safronova.webproject.model.service.impl;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.dao.DaoProvider;
import com.safronova.webproject.model.dao.StorageDao;
import com.safronova.webproject.model.entity.Dessert;
import com.safronova.webproject.model.entity.Storage;
import com.safronova.webproject.model.service.StorageService;
import com.safronova.webproject.model.validator.DessertValidator;

public class StorageServiceImpl implements StorageService {
    private static final DaoProvider daoProvider = DaoProvider.getInstance();
    private static final StorageDao storageDao = daoProvider.getStorageDao();

    @Override
    public Storage findByDessertId(String dessertId) throws ServiceException {
        Storage storage;
        try {
            storage = storageDao.findByDessertId(Integer.parseInt(dessertId));
        } catch (DaoException e) {
            throw new ServiceException("Can't handle findByDessertId request at StorageService", e);
        }
        return storage;
    }

    @Override
    public void createStorage(Dessert dessert, String count) throws ServiceException {
        if (!DessertValidator.validateQuantity(count)) {
            throw new ServiceException("Storage data didn't passed validation");
        }
        Storage storage = new Storage(dessert, Integer.parseInt(count));
        try {
            storageDao.insertStorage(storage);
        } catch (DaoException e) {
            throw new ServiceException("Can't handle createStorage request at StorageService", e);
        }
    }

    @Override
    public void updateStorage(String dessertId, String dessertCount) throws ServiceException {
        if (!DessertValidator.validateQuantity(dessertCount)) {
            throw new ServiceException("Storage data didn't passed validation");
        }
        try {
            int id = Integer.parseInt(dessertId);
            int count = Integer.parseInt(dessertCount);
            storageDao.updateStorageByDessert(id, count);
        } catch (DaoException e) {
            throw new ServiceException("Can't handle updateStorage request at StorageService", e);
        }
    }
}
