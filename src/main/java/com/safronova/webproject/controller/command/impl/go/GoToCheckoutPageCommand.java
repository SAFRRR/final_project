package com.safronova.webproject.controller.command.impl.go;

import com.safronova.webproject.controller.command.PagePath;
import com.safronova.webproject.controller.command.RequestAttribute;
import com.safronova.webproject.controller.command.RequestParameter;
import com.safronova.webproject.controller.command.Router;
import com.safronova.webproject.controller.command.Router.RouterType;
import com.safronova.webproject.controller.command.impl.user.UserCommand;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.entity.Basket;
import com.safronova.webproject.model.entity.BasketDessert;
import com.safronova.webproject.model.service.BasketDessertService;
import com.safronova.webproject.model.service.BasketService;
import com.safronova.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GoToCheckoutPageCommand extends UserCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected Router handle(HttpServletRequest request) {
        Router router;
        final String basketId = request.getParameter(RequestParameter.BASKET_ID);

        final ServiceProvider serviceProvider = ServiceProvider.getInstance();
        final BasketService basketService = serviceProvider.getBasketService();
        final BasketDessertService basketDessertService = serviceProvider.getBasketDessertService();
        try{
            Basket basket = basketService.findById(basketId);
            List<BasketDessert> basketDessertList = basketDessertService.findByBasketId(Integer.parseInt(basketId));
            if (basketDessertList.size() == 0) {
                request.setAttribute(RequestAttribute.EMPTY_BASKET, true);
                router = new Router(PagePath.BASKET_PAGE, Router.RouterType.FORWARD);
            } else {
                request.setAttribute(RequestAttribute.BASKET, basket);
                request.setAttribute(RequestAttribute.BASKET_DESSERT_LIST, basketDessertList);
                router = new Router(PagePath.CHECKOUT_PAGE, Router.RouterType.FORWARD);
            }
            for (BasketDessert basketDessert : basketDessertList) {
                if (basketDessert.getDessert().getStorage().getCount() < basketDessert.getCount()) {
                    request.setAttribute(RequestAttribute.NOT_ENOUGH, true);
                    router = new Router(PagePath.BASKET_PAGE, Router.RouterType.FORWARD);
                }
            }
        }catch (ServiceException e) {
            logger.error("Error at GoToCheckoutPageCommand", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, RouterType.FORWARD);
        }
        return router;
    }
}
