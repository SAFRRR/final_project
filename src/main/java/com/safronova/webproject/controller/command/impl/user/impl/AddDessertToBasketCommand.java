package com.safronova.webproject.controller.command.impl.user.impl;

import com.safronova.webproject.controller.command.PagePath;
import com.safronova.webproject.controller.command.RequestAttribute;
import com.safronova.webproject.controller.command.RequestParameter;
import com.safronova.webproject.controller.command.Router;
import com.safronova.webproject.controller.command.Router.RouterType;
import com.safronova.webproject.controller.command.impl.user.UserCommand;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.entity.Basket;
import com.safronova.webproject.model.entity.User;
import com.safronova.webproject.model.service.BasketDessertService;
import com.safronova.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddDessertToBasketCommand extends UserCommand {
    private static final Logger logger = LogManager.getLogger();
    @Override
    protected Router handle(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession(true);
        final User user = (User) request.getSession().getAttribute(RequestAttribute.USER);

        final String dessertId = request.getParameter(RequestParameter.DESSERT_ID);
        final String count = request.getParameter(RequestParameter.DESSERT_COUNT);
        final String price = request.getParameter(RequestParameter.DESSERT_PRICE);
        final String storageAmount = request.getParameter(RequestParameter.STORAGE_AMOUNT);

        final ServiceProvider serviceProvider = ServiceProvider.getInstance();
        final BasketDessertService basketDessertService = serviceProvider.getBasketDessertService();

        try {
            Basket basket = user.getBasket();
            if (Integer.parseInt(count) > Integer.parseInt(storageAmount)) {
                request.setAttribute(RequestAttribute.NOT_ENOUGH, true);
                router = new Router(PagePath.GO_TO_DESSERT_DETAIL, RouterType.FORWARD);
            } else {
                basketDessertService.addToBasket(basket.getId(), dessertId, count, price);
                session.setAttribute(RequestAttribute.ADD_SUCCESS, true);
                String page = (String) session.getAttribute(RequestAttribute.CURRENT_PAGE);
                router = new Router(page, RouterType.REDIRECT);
            }
        } catch (ServiceException e) {
            logger.error("Error at AddDessertToBasketCommand", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, RouterType.REDIRECT);
        }
        return router;
    }
}
