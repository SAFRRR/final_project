package com.safronova.webproject.model.service.impl;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.dao.DaoProvider;
import com.safronova.webproject.model.dao.DessertDao;
import com.safronova.webproject.model.dao.ResultCode;
import com.safronova.webproject.model.entity.Dessert;
import com.safronova.webproject.model.entity.DessertType;
import com.safronova.webproject.model.service.DessertService;
import com.safronova.webproject.model.service.DessertTypeService;
import com.safronova.webproject.model.validator.DessertValidator;

import java.math.BigDecimal;
import java.util.List;

/**
 * {@code DessertServiceImpl class is an implementation of {@link DessertService} interface.
 *
 * @author Anna Safronova
 */
public class DessertServiceImpl implements DessertService {
    private static final DaoProvider daoProvider = DaoProvider.getInstance();
    private static final DessertDao dessertDao = daoProvider.getDessertDao();

    @Override
    public List<Dessert> findAll() throws ServiceException {
        List<Dessert> dessertList;
        try {
            dessertList = dessertDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Can't handle findAll request at DessertService", e);
        }
        return dessertList;
    }

    @Override
    public List<Dessert> findByCategory(String category) throws ServiceException {
        if (!DessertValidator.validateDessertTypeId(category)) {
            throw new ServiceException("Dessert Type data didn't passed validation");
        }
        List<Dessert> dessertList;
        try {
            dessertList = dessertDao.findByCategory(category);
        } catch (DaoException e) {
            throw new ServiceException("Can't handle findByCategory request at DessertService", e);
        }
        return dessertList;
    }

    @Override
    public Dessert findById(String dessertId) throws ServiceException {
        if (!DessertValidator.validateId(dessertId)) {
            throw new ServiceException("Dessert data didn't passed validation");
        }
        Dessert dessert;
        try {
            int id = Integer.parseInt(dessertId);
            dessert = dessertDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Can't handle findById request at DessertService", e);
        }
        return dessert;
    }


    @Override
    public ResultCode findDessertByName(String name) throws ServiceException {
        if(!DessertValidator.validateName(name)){
            throw new ServiceException("Dessert name didn't passed validation");
        }else{
            try {
                return dessertDao.findDessertByName(name);
            } catch (DaoException e) {
                throw new ServiceException("Can't handle findDessertByName request at DessertService", e);
            }
        }
    }

    @Override
    public int checkQuantity(String quantity) throws ServiceException {
        if(!DessertValidator.validateQuantity(quantity)){
            throw new ServiceException("Dessert quantity didn't passed validation");
        }else{
            return Integer.parseInt(quantity);
        }
    }


    @Override
    public Dessert createDessert(String nameDessert, String description, String price, String weight,  DessertType category, String quantity) throws ServiceException {
        final String FORMAT_FILE_NAME = ".png";
        if (!DessertValidator.validateData(nameDessert, description, price, weight)) {
            throw new ServiceException("Dessert data didn't passed validation");
        }
        BigDecimal dessertPrice = new BigDecimal(price);
        int dessertWeight = Integer.parseInt(weight);
        int dessertQuantity = Integer.parseInt(quantity);
        Dessert dessert = new Dessert(nameDessert, description, dessertPrice, dessertWeight, category, dessertQuantity);
        Dessert dbDessert;
        try {
            dbDessert = dessertDao.createDessert(dessert);
            String dessertImage = dessert.getId() + FORMAT_FILE_NAME;
            dbDessert.setDessertImage(dessertImage);
            dessertDao.updateDessertImage(dbDessert);
        } catch (DaoException e) {
            throw new ServiceException("Can't handle createDessert request at DessertService", e);
        }
        return dbDessert;
    }

    @Override
    public void updateDessert(String dessertId, String nameDessert, String description, String price, String weight,  DessertType dessertType, String quantity) throws ServiceException {
        if (!DessertValidator.validateData(nameDessert, description, price, weight) || !DessertValidator.validateId(dessertId)) {
            throw new ServiceException("Dessert data didn't passed validation");
        }
        int id = Integer.parseInt(dessertId);
        BigDecimal dessertPrice = new BigDecimal(price);
        int dessertWeight = Integer.parseInt(weight);
        int dessertQuantity = Integer.parseInt(quantity);
        Dessert dessert = new Dessert(nameDessert, description, dessertPrice,  dessertWeight, dessertType, dessertQuantity);
        try {
            dessertDao.updateDessert(id, dessert);
        } catch (DaoException e) {
            throw new ServiceException("Can't handle updateDessert request at DessertService", e);
        }
    }

    @Override
    public void deleteDessertById(String dessertId) throws ServiceException {
        if (!DessertValidator.validateId(dessertId)) {
            throw new ServiceException("Dessert data didn't passed validation");
        }
        int id = Integer.parseInt(dessertId);
        try {
            dessertDao.deleteById(id);
        } catch (DaoException e) {
            throw new ServiceException("Can't handle deleteDessertById request at DessertService", e);
        }
    }

    @Override
    public List<Dessert> findAllDessertList() throws ServiceException {
        List<Dessert> dessertList;
        try {
            dessertList = dessertDao.findAllDessertList();
        } catch (DaoException e) {
            throw new ServiceException("Can't handle findAllDessertList request at DessertService", e);
        }
        return dessertList;
    }
}

