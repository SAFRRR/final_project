package com.safronova.webproject.controller.command.impl.go;

import com.safronova.webproject.controller.command.Command;
import com.safronova.webproject.controller.command.PagePath;
import com.safronova.webproject.controller.command.RequestAttribute;
import com.safronova.webproject.controller.command.Router;
import com.safronova.webproject.controller.command.Router.RouterType;
import com.safronova.webproject.controller.command.impl.user.UserCommand;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.entity.Order;
import com.safronova.webproject.model.entity.User;
import com.safronova.webproject.model.service.OrderService;
import com.safronova.webproject.model.service.ServiceProvider;
import com.safronova.webproject.model.util.RegexpPropertiesReader;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Properties;

public class GoToOrderHistoryPageCommand extends UserCommand{
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router handle(HttpServletRequest request) {
        Router router;
        final User user = (User) request.getSession().getAttribute(RequestAttribute.USER);
        final ServiceProvider serviceProvider = ServiceProvider.getInstance();
        final OrderService orderService = serviceProvider.getOrderService();
        try {
            List<Order> orderList = orderService.findByUser(user.getId());
            request.setAttribute(RequestAttribute.ORDER_LIST, orderList);
            router = new Router(PagePath.ORDER_HISTORY_PAGE, RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at GoToProfilePageCommand", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, RouterType.FORWARD);
        }
        return router;
    }
}
