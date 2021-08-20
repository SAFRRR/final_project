package com.safronova.webproject.model.service.impl;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.dao.BasketDessertDao;
import com.safronova.webproject.model.dao.DaoProvider;
import com.safronova.webproject.model.entity.Basket;
import com.safronova.webproject.model.entity.BasketDessert;
import com.safronova.webproject.model.service.BasketDessertService;
import com.safronova.webproject.model.service.BasketService;
import com.safronova.webproject.model.validator.DessertValidator;

import java.math.BigDecimal;
import java.util.List;

/**
 * {@code BasketDessertServiceImpl  class is an implementation of {@link BasketDessertService} interface.
 *
 * @author Anna Safronova
 */
public class BasketDessertServiceImpl implements BasketDessertService {
    private static final DaoProvider daoProvider = DaoProvider.getInstance();
    private static final BasketDessertDao basketDessertDao = daoProvider.getBasketDessertDao();

    @Override
    public void addToBasket(int basketId, String dessertId, String count, String price) throws ServiceException {
        if (!DessertValidator.validateId(dessertId) || !DessertValidator.validateQuantity(count)) {
            throw new ServiceException("Dessert data didn't passed validation");
        }
        try {
            BigDecimal subTotal = new BigDecimal(price).multiply(new BigDecimal(count));
            int dessertCount = Integer.parseInt(count);
            int id = Integer.parseInt(dessertId);
            basketDessertDao.addItemToBasket(basketId, id, dessertCount, subTotal);
        } catch (DaoException e) {
            throw new ServiceException("Can't handle addToBasket request at BasketDessertService", e);
        }
    }

    @Override
    public List<BasketDessert> findByBasketId(int id) throws ServiceException {
        List<BasketDessert> basketDessertList;
        try {
            basketDessertList = basketDessertDao.findByBasketId(id);
        } catch (DaoException e) {
            throw new ServiceException("Can't handle findByBasketId request at BasketDessertService", e);
        }
        return basketDessertList;
    }

    @Override
    public List<BasketDessert> findByDessertId(int id) throws ServiceException {
        List<BasketDessert> basketDessertList;
        try {
            basketDessertList = basketDessertDao.findByDessertId(id);
        } catch (DaoException e) {
            throw new ServiceException("Can't handle findByDessertId request at BasketDessertService", e);
        }
        return basketDessertList;
    }

    @Override
    public void updateBasketDessert(String basketDessertId, String count) throws ServiceException {
        if (!DessertValidator.validateQuantity(count)) {
            throw new ServiceException("Data didn't passed validation");
        }
        try {
            BasketDessert basketDessert = basketDessertDao.findById(Integer.parseInt(basketDessertId));
            basketDessert.setCount(Integer.parseInt(count));
            basketDessertDao.updateCount(basketDessert);
        } catch (DaoException e) {
            throw new ServiceException("Can't handle updateBasketDessert request at BasketDessertService", e);
        }
    }

    @Override
    public void deleteBasketDessert(String basketDessertId) throws ServiceException {
        if (!DessertValidator.validateId(basketDessertId)) {
            throw new ServiceException("Data didn't passed validation");
        }
        try {
            basketDessertDao.deleteBasketDessert(Integer.parseInt(basketDessertId));
        } catch (DaoException e) {
            throw new ServiceException("Can't handle deleteBasketDessert request at BasketDessertService", e);
        }
    }

    @Override
    public Basket clearBasket(Basket basket) throws ServiceException {
        List<BasketDessert> basketDessertList = findByBasketId(basket.getId());
        for (BasketDessert basketDessert : basketDessertList) {
            deleteBasketDessert(String.valueOf(basketDessert.getId()));
        }
        basket.setTotalCost(new BigDecimal(0));
        return basket;
    }
}
