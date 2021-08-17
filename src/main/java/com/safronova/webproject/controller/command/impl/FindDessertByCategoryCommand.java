package com.safronova.webproject.controller.command.impl;

import com.safronova.webproject.controller.command.*;
import com.safronova.webproject.controller.command.Router.RouterType;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.entity.Dessert;
import com.safronova.webproject.model.entity.DessertType;
import com.safronova.webproject.model.service.DessertService;
import com.safronova.webproject.model.service.DessertTypeService;
import com.safronova.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class FindDessertByCategoryCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession(true);
        final String category = request.getParameter(RequestParameter.CATEGORY);
        final ServiceProvider serviceProvider = ServiceProvider.getInstance();
        final DessertService dessertService = serviceProvider.getDessertService();
        final DessertTypeService dessertTypeService = serviceProvider.getDessertTypeService();

        try {
            List<Dessert> dessertList = dessertService.findByCategory(category);
            DessertType dessertType = dessertTypeService.findById(category);
            request.setAttribute(RequestAttribute.DESSERT_TYPE, dessertType);
            if (dessertList.isEmpty()) {
                request.setAttribute(RequestAttribute.EMPTY_LIST, true);
            } else {
                request.setAttribute(RequestAttribute.DESSERT_LIST, dessertList);
            }
            session.setAttribute(RequestAttribute.ADD_SUCCESS, false);
            session.setAttribute(RequestAttribute.NOT_ENOUGH, false);
            session.setAttribute(RequestAttribute.ADD_FAILED, false);
            router = new Router(PagePath.ITEM_PAGE, RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at FindDessertByCategoryCommand", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, RouterType.REDIRECT);
        }
        return router;
    }
}
