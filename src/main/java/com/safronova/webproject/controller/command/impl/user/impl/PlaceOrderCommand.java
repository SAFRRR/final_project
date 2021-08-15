package com.safronova.webproject.controller.command.impl.user.impl;

import com.safronova.webproject.controller.command.PagePath;
import com.safronova.webproject.controller.command.RequestAttribute;
import com.safronova.webproject.controller.command.RequestParameter;
import com.safronova.webproject.controller.command.Router;
import com.safronova.webproject.controller.command.Router.RouterType;
import com.safronova.webproject.controller.command.impl.user.UserCommand;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.entity.Basket;
import com.safronova.webproject.model.entity.BasketDessert;
import com.safronova.webproject.model.entity.Order;
import com.safronova.webproject.model.entity.User;
import com.safronova.webproject.model.service.BasketDessertService;
import com.safronova.webproject.model.service.BasketService;
import com.safronova.webproject.model.service.OrderService;
import com.safronova.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import java.util.Optional;

public class PlaceOrderCommand extends UserCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected Router handle(HttpServletRequest request) {
        Router router;
        final User user = (User) request.getSession().getAttribute(RequestAttribute.USER);
        final String address = request.getParameter(RequestParameter.ADDRESS);
        final String cash = request.getParameter(RequestParameter.CASH);
        final String date = request.getParameter(RequestParameter.DATE);
        final String time = request.getParameter(RequestParameter.TIME);
        final ServiceProvider serviceProvider = ServiceProvider.getInstance();
        final OrderService orderService = serviceProvider.getOrderService();
        final BasketDessertService basketDessertService = serviceProvider.getBasketDessertService();
        final BasketService basketService = serviceProvider.getBasketService();

        try {
            Basket basket = user.getBasket();
            List<BasketDessert> basketDessertList = basketDessertService.findByBasketId(basket.getId());
            Optional<Order> order = orderService.createOrder(address, cash, date, time, user, basket);
            if (order.isPresent()) {
                Order savedOrder = orderService.saveOrder(order.get());
                orderService.createOrderDessertByOrder(savedOrder, basketDessertList);
                Basket clearedBasket = basketDessertService.clearBasket(basket);
                basketService.updateTotalCost(clearedBasket);
                router = new Router(PagePath.GO_TO_ORDER_HISTORY_PAGE, RouterType.REDIRECT);
            } else {
                request.setAttribute(RequestAttribute.MISS_REQUIRED_FIELDS, true);
                router = new Router(PagePath.GO_TO_CHECKOUT_PAGE, RouterType.REDIRECT);
            }
        } catch (ServiceException e) {
            logger.error("Error at PlaceOrderCommand", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
