package com.safronova.webproject.model.service.impl;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.dao.DaoProvider;
import com.safronova.webproject.model.dao.DessertTypeDao;
import com.safronova.webproject.model.entity.DessertType;
import com.safronova.webproject.model.service.DessertTypeService;
import com.safronova.webproject.model.validator.DessertValidator;

import java.util.List;

public class DessertTypeServiceImpl implements DessertTypeService {
    private static final DaoProvider daoProvider = DaoProvider.getInstance();
    private static final DessertTypeDao dessertTypeDao = daoProvider.getDessertTypeDao();

    @Override
    public DessertType findById(String id) throws ServiceException {
        if (!DessertValidator.validateDessertTypeId(id)) {
            throw new ServiceException("Dessert type data didn't passed validation");
        }
        DessertType dessertType;
        try {
            int typeId = Integer.parseInt(id);
            dessertType = dessertTypeDao.findById(typeId);
        } catch (DaoException e) {
            throw new ServiceException("Can't handle findById request at DessertTypeService", e);
        }
        return dessertType;
    }

    @Override
    public List<DessertType> findAll() throws ServiceException {
        List<DessertType> dessertTypeList;
        try {
            dessertTypeList = dessertTypeDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Can't handle findAll request at DessertTypeService", e);
        }
        return dessertTypeList;
    }
}
