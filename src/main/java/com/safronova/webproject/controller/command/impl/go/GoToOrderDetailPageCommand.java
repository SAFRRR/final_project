package com.safronova.webproject.controller.command.impl.go;

import com.safronova.webproject.controller.command.PagePath;
import com.safronova.webproject.controller.command.RequestAttribute;
import com.safronova.webproject.controller.command.RequestParameter;
import com.safronova.webproject.controller.command.Router;
import com.safronova.webproject.controller.command.Router.RouterType;
import com.safronova.webproject.controller.command.impl.admin.AdminCommand;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.entity.Order;
import com.safronova.webproject.model.entity.OrderDessert;
import com.safronova.webproject.model.service.OrderService;
import com.safronova.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class GoToOrderDetailPageCommand extends AdminCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected Router handle(HttpServletRequest request) {
        Router router;

        final ServiceProvider serviceProvider = ServiceProvider.getInstance();
        final OrderService orderService = serviceProvider.getOrderService();
        final String orderId = request.getParameter(RequestParameter.ORDER_ID);

        try {
            Order order = orderService.findById(orderId);
            List<OrderDessert> orderDessertList = orderService.findByOrder(order.getId());
            request.setAttribute(RequestAttribute.ORDER, order);
            request.setAttribute(RequestAttribute.ORDER_DESSERT, orderDessertList);
            router = new Router(PagePath.ORDER_DETAIL_PAGE, RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at GoToOrderDetailPageCommand", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, RouterType.REDIRECT);
        }
        return router;
    }
}

