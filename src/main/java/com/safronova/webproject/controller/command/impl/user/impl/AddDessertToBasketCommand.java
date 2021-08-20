package com.safronova.webproject.controller.command.impl.user.impl;

import com.safronova.webproject.controller.command.PagePath;
import com.safronova.webproject.controller.command.RequestAttribute;
import com.safronova.webproject.controller.command.RequestParameter;
import com.safronova.webproject.controller.command.Router;
import com.safronova.webproject.controller.command.Router.RouterType;
import com.safronova.webproject.controller.command.impl.user.UserCommand;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.entity.*;
import com.safronova.webproject.model.service.BasketDessertService;
import com.safronova.webproject.model.service.DessertService;
import com.safronova.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

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
        final DessertService dessertService = serviceProvider.getDessertService();


        try {
            Basket basket = user.getBasket();
            session.setAttribute(RequestAttribute.NOT_ENOUGH, false);
            session.setAttribute(RequestAttribute.ADD_SUCCESS, false);
            session.setAttribute(RequestAttribute.ADD_FAILED , false);
            if (dessertService.checkQuantity(count) > Integer.parseInt(storageAmount)) {
                session.setAttribute(RequestAttribute.NOT_ENOUGH, true);
                String page = (String) session.getAttribute(RequestAttribute.CURRENT_PAGE);
                router = new Router(page, RouterType.REDIRECT);
            } else {
                List<BasketDessert> basketDesserts = basketDessertService.findByDessertId(Integer.parseInt(dessertId));
                if (basketDesserts.isEmpty()){
                    basketDessertService.addToBasket(basket.getId(), dessertId, count, price);
                    session.setAttribute(RequestAttribute.ADD_SUCCESS, true);
                } else {
                    int existCount = basketDesserts.get(0).getCount();
                    if (Integer.parseInt(count) + existCount > Integer.parseInt(storageAmount)){
                        session.setAttribute(RequestAttribute.ADD_FAILED , true);
                        session.setAttribute(RequestAttribute.ADDED_COUNT, count);
                        session.setAttribute(RequestAttribute.BASKET_COUNT, existCount);
                    } else {
                        int newCount = Integer.parseInt(count) + existCount;
                        int basketDessertId = basketDesserts.get(0).getId();
                        basketDessertService.updateBasketDessert(String.valueOf(basketDessertId), String.valueOf(newCount));
                        session.setAttribute(RequestAttribute.ADD_SUCCESS, true);
                    }
                }
                String page = (String) session.getAttribute(RequestAttribute.CURRENT_PAGE);
                router = new Router(page, RouterType.REDIRECT);
            }
        } catch (ServiceException e) {
            logger.error("Error at AddDessertToBasketCommand", e);
            request.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, RouterType.REDIRECT);
        }
        return router;
    }
}
