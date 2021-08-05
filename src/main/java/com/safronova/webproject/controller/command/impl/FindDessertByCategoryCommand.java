package com.safronova.webproject.controller.command.impl;

import com.safronova.webproject.controller.command.*;
import com.safronova.webproject.controller.command.Router.RouterType;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.entity.Dessert;
import com.safronova.webproject.model.entity.DessertType;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindDessertByCategoryCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        final String category = request.getParameter(RequestParameter.CATEGORY);

        final ServiceProvider serviceProvider = ServiceProvider.getInstance();
        final FlowerService flowerService = serviceProvider.getFlowerService();
        final FlowerTypeService flowerTypeService = serviceProvider.getFlowerTypeService();

        try {
            List<Dessert> dessertList = flowerService.findByCategory(category);
            DessertType dessertType = flowerTypeService.findById(category);
            request.setAttribute(RequestAttribute.FLOWER_TYPE, dessertType);
            if (dessertList.isEmpty()) {
                request.setAttribute(RequestAttribute.EMPTY_LIST, true);
            } else {
                request.setAttribute(RequestAttribute.FLOWER_LIST, dessertList);
            }
            router = new Router(PagePath.ITEM_PAGE, RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at FindProductByCategoryCommand", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, RouterType.REDIRECT);
        }
        return router;

    }
}
