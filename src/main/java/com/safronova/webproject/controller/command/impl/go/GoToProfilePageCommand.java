package com.safronova.webproject.controller.command.impl.go;

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

public class GoToProfilePageCommand extends UserCommand {
    private static final Logger logger = LogManager.getLogger();
    private static final Properties properties = RegexpPropertiesReader.getProperties();
    private static final String REGEXP_USERNAME = properties.getProperty("regexp.username");
    private static final String REGEXP_PASSWORD = properties.getProperty("regexp.password");
    private static final String REGEXP_USER_FIO = properties.getProperty("regexp.user_fio");
    private static final String REGEXP_PHONE_NUMBER = properties.getProperty("regexp.phone_number");

    @Override
    public Router handle(HttpServletRequest request) {
        Router router;

        final User user = (User) request.getSession().getAttribute(RequestAttribute.USER);
        final ServiceProvider serviceProvider = ServiceProvider.getInstance();
        final OrderService orderService = serviceProvider.getOrderService();

        try {
            List<Order> orderList = orderService.findByUser(user.getId());
            request.setAttribute(RequestAttribute.REGEXP_USERNAME, REGEXP_USERNAME);
            request.setAttribute(RequestAttribute.REGEXP_FIO, REGEXP_USER_FIO);
            request.setAttribute(RequestAttribute.REGEXP_PHONE, REGEXP_PHONE_NUMBER);
            request.setAttribute(RequestAttribute.REGEXP_PASSWORD, REGEXP_PASSWORD);
            request.setAttribute(RequestAttribute.ORDER_LIST, orderList);
            router = new Router(PagePath.PROFILE_PAGE, RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at GoToProfilePageCommand", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, RouterType.FORWARD);
        }
        return router;
    }
}

