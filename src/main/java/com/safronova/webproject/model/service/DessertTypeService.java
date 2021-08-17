package com.safronova.webproject.model.service;

import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.entity.DessertType;

import java.util.List;

public interface DessertTypeService {
    List<DessertType> findAll() throws ServiceException;

    DessertType findById(String id) throws ServiceException;
}

