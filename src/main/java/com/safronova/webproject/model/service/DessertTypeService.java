package com.safronova.webproject.model.service;

import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.entity.DessertType;
import com.safronova.webproject.model.entity.Order;

import java.util.List;

/**
 * {@code DessertTypeService} is an interface with a bunch of methods that allow to manipulate {@link DessertType}
 *
 * @author Anna Safronova
 */
public interface DessertTypeService {
    /**
     * Find all {@link DessertType} entities
     *
     * @return the list of {@link DessertType} entity
     * @throws ServiceException if an error occurred executing the method.
     */
    List<DessertType> findAll() throws ServiceException;

    /**
     * Find {@link DessertType} entity  by id
     *
     * @param id the unique id
     * @return {@link DessertType} entity
     * @throws ServiceException if an error occurred executing the method.
     */
    DessertType findById(String id) throws ServiceException;
}

