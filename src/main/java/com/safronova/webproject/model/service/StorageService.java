package com.safronova.webproject.model.service;

import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.entity.Dessert;
import com.safronova.webproject.model.entity.Storage;

/**
 * {@code StorageService} is an interface with a bunch of methods that allow to manipulate {@link Storage}
 *
 *  @author Anna Safronova
 */
public interface StorageService {
    /**
     * Find {@link Storage} entity by dessert id.
     *
     * @param dessertId the dessert id
     * @return {@link Storage} entity
     * @throws ServiceException if an error occurred executing the method.
     */
    Storage findByDessertId(String dessertId) throws ServiceException;

    /**
     * Create a {@code Storage} entity in the data store.
     *
     * @param dessert {@link Dessert} entity
     * @param count value of available items
     * @throws ServiceException if an error occurred executing the method.
     */
    void createStorage(Dessert dessert, String count) throws ServiceException;

    /**
     * Update a {@code Storage} entity in the data store.
     *
     * @param dessertId the dessert id
     * @param count value of available items
     * @throws ServiceException if an error occurred executing the method.
     */
    void updateStorage(String dessertId, String count) throws ServiceException;
}
