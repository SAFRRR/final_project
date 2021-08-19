package com.safronova.webproject.controller.command.impl.go;

import com.safronova.webproject.controller.command.PagePath;
import com.safronova.webproject.controller.command.RequestAttribute;
import com.safronova.webproject.controller.command.Router;
import com.safronova.webproject.controller.command.Router.RouterType;
import com.safronova.webproject.controller.command.impl.user.UserCommand;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.entity.Basket;
import com.safronova.webproject.model.entity.BasketDessert;
import com.safronova.webproject.model.entity.User;
import com.safronova.webproject.model.service.BasketDessertService;
import com.safronova.webproject.model.service.BasketService;
import com.safronova.webproject.model.service.ServiceProvider;
import com.safronova.webproject.model.util.RegexpPropertiesReader;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Properties;

public class GoToBasketPageCommand extends UserCommand {
    private static final Logger logger = LogManager.getLogger();
    private static final Properties properties = RegexpPropertiesReader.getProperties();
    private static final String REGEXP_QUANTITY = properties.getProperty( "regexp.dessert.quantity");

    @Override
    protected Router handle(HttpServletRequest request) {
        Router router;
        final User user = (User) request.getSession().getAttribute(RequestAttribute.USER);
        final ServiceProvider serviceProvider = ServiceProvider.getInstance();
        final BasketService basketService = serviceProvider.getBasketService();
        final BasketDessertService basketDessertService = serviceProvider.getBasketDessertService();
        try {
            Basket basket = user.getBasket();
            List<BasketDessert> basketDessertList = basketDessertService.findByBasketId(basket.getId());
            request.getSession().setAttribute(RequestAttribute.REGEXP_COUNT, REGEXP_QUANTITY);
            request.setAttribute(RequestAttribute.BASKET_DESSERT_LIST, basketDessertList);
            basketService.updateBasket(basket, basketDessertList);
            if (basketDessertList.isEmpty()) {
                request.setAttribute(RequestAttribute.EMPTY_BASKET, true);
            }
            request.setAttribute(RequestAttribute.BASKET, basket);
            router = new Router(PagePath.BASKET_PAGE, RouterType.FORWARD);
        }catch (ServiceException e) {
            logger.error("Error at GoToBasketPageCommand", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, Router.RouterType.FORWARD);
        }
        return router;
    }
}
