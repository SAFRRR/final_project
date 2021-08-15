package com.safronova.webproject.model.service.impl;

import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.dao.BasketDao;
import com.safronova.webproject.model.dao.BasketDessertDao;
import com.safronova.webproject.model.dao.DaoProvider;
import com.safronova.webproject.model.entity.Basket;
import com.safronova.webproject.model.entity.BasketDessert;
import com.safronova.webproject.model.service.BasketService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class BasketServiceImpl implements BasketService {
    private static final DaoProvider daoProvider = DaoProvider.getInstance();
    private static final BasketDao basketDao = daoProvider.getBasketDao();
    private static final BasketDessertDao basketFlowerDao = daoProvider.getBasketDessertDao();

    @Override
    public Basket findUserBasket(Integer id) throws ServiceException {
        Basket basket;
        try {
            basket = basketDao.findByUserId(id);
            if (basket.getId() == 0) {
                Basket userBasket = basketDao.createBasket(id);
                return userBasket;
            }
        } catch (DaoException e) {
            throw new ServiceException("Can't handle findUserBasket at BasketService", e);
        }
        return basket;
    }

    @Override
    public Basket findById(String basketId) throws ServiceException {
        Basket basket;
        try {
            basket = basketDao.findById(Integer.parseInt(basketId));
        } catch (DaoException e) {
            throw new ServiceException("Can't handle findUserBasket at BasketService", e);
        }
        return basket;
    }

    @Override
    public void updateBasket(Basket basket, List<BasketDessert> basketDessertList) throws ServiceException {
        BigDecimal basketTotal = new BigDecimal(0);
        for (BasketDessert basketDessert : basketDessertList) {
            if (basketDessert.getDessert().getStorage().getCount() > 0) {
                updateBasketDessert(basketDessert);
                basketTotal = basketTotal.add(basketDessert.getSubTotal());
            }
        }
        basket.setTotalCost(basketTotal);
        try {
            basketDao.updateBasket(basket);
        } catch (DaoException e) {
            throw new ServiceException("Can't handle updateBasket at BasketService", e);
        }
    }
    private void updateBasketDessert(BasketDessert basketDessert) throws ServiceException {
        BigDecimal temp = basketDessert.getDessert().getPrice();
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(temp)).multiply(new BigDecimal(basketDessert.getCount()));
        bigDecimal.setScale(2, RoundingMode.HALF_EVEN);
        basketDessert.setSubTotal(bigDecimal);
        try {
            basketFlowerDao.updateSubTotal(basketDessert);
        } catch (DaoException e) {
            throw new ServiceException("Can't handle updateBasketDessert at BasketService", e);
        }
    }

    @Override
    public void updateTotalCost(Basket basket) throws ServiceException {
        try {
            basketDao.updateBasket(basket);
        } catch (DaoException e) {
            throw new ServiceException("Can't handle updateTotalCost at BasketService", e);
        }
    }
}
