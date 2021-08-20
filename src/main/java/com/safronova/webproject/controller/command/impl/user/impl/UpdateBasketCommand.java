package com.safronova.webproject.controller.command.impl.user.impl;

import com.safronova.webproject.controller.command.PagePath;
import com.safronova.webproject.controller.command.RequestAttribute;
import com.safronova.webproject.controller.command.RequestParameter;
import com.safronova.webproject.controller.command.Router;
import com.safronova.webproject.controller.command.Router.RouterType;
import com.safronova.webproject.controller.command.impl.user.UserCommand;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.service.BasketDessertService;
import com.safronova.webproject.model.service.DessertService;
import com.safronova.webproject.model.service.ServiceProvider;
import com.safronova.webproject.model.service.StorageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateBasketCommand extends UserCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected Router handle(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession(true);
        final String basketDessertId = request.getParameter(RequestParameter.BASKET_ID);
        final String storageAmount = request.getParameter(RequestParameter.STORAGE_AMOUNT);
        final String count = request.getParameter(RequestParameter.COUNT);
        final ServiceProvider serviceProvider = ServiceProvider.getInstance();
        final BasketDessertService basketDessertService = serviceProvider.getBasketDessertService();

        try {
            if (Integer.parseInt(count) > Integer.parseInt(storageAmount)) {
                session.setAttribute(RequestAttribute.NOT_ENOUGH, true);
            } else {
                basketDessertService.updateBasketDessert(basketDessertId, count);
            }
            router = new Router(PagePath.GO_TO_BASKET_PAGE, RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Error at UpdateBasketCommand", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, RouterType.REDIRECT);
        }
        return router;
    }
}


