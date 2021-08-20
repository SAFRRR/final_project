package com.safronova.webproject.controller.command.impl.admin.impl;

import com.safronova.webproject.controller.command.PagePath;
import com.safronova.webproject.controller.command.RequestAttribute;
import com.safronova.webproject.controller.command.RequestParameter;
import com.safronova.webproject.controller.command.Router;
import com.safronova.webproject.controller.command.Router.RouterType;
import com.safronova.webproject.controller.command.impl.admin.AdminCommand;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.entity.DessertType;
import com.safronova.webproject.model.service.DessertService;
import com.safronova.webproject.model.service.DessertTypeService;
import com.safronova.webproject.model.service.ServiceProvider;
import com.safronova.webproject.model.service.StorageService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateDessertCommand extends AdminCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected Router handle(HttpServletRequest request) {
        Router router;

        final String dessertId = request.getParameter(RequestParameter.DESSERT_ID);
        final String nameDessert = request.getParameter(RequestParameter.NAME);
        final String description = request.getParameter(RequestParameter.DESCRIPTION);
        final String category = request.getParameter(RequestParameter.CATEGORY);
        final String weight = request.getParameter(RequestParameter.WEIGHT);
        final String price = request.getParameter(RequestParameter.PRICE);
        final String count = request.getParameter(RequestParameter.COUNT);

        final ServiceProvider serviceProvider = ServiceProvider.getInstance();
        final DessertService dessertService = serviceProvider.getDessertService();
        final DessertTypeService dessertTypeService = serviceProvider.getDessertTypeService();
        final StorageService storageService = serviceProvider.getStorageService();

        try {
            DessertType dessertType = dessertTypeService.findById(category);
            dessertService.updateDessert(dessertId, nameDessert, description, price, weight, dessertType, count);
            storageService.updateStorage(dessertId, count);
            router = new Router(PagePath.GO_TO_DESSERT_LIST,RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Error at UpdateDessertCommand", e);
            request.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, RouterType.REDIRECT);
        }
        return router;
    }
}
