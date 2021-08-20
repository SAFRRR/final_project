package com.safronova.webproject.controller.command.impl.admin.impl;

import com.safronova.webproject.controller.command.PagePath;
import com.safronova.webproject.controller.command.RequestAttribute;
import com.safronova.webproject.controller.command.RequestParameter;
import com.safronova.webproject.controller.command.Router;
import com.safronova.webproject.controller.command.Router.RouterType;
import com.safronova.webproject.controller.command.impl.admin.AdminCommand;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.service.DessertService;
import com.safronova.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteDessertCommand extends AdminCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected Router handle(HttpServletRequest request) {
        Router router;
        final String dessertId = request.getParameter(RequestParameter.DESSERT_ID);
        final ServiceProvider serviceProvider = ServiceProvider.getInstance();
        final DessertService dessertService = serviceProvider.getDessertService();
        try {
            dessertService.deleteDessertById(dessertId);
            router = new Router(PagePath.GO_TO_DESSERT_LIST, RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Error at DeleteDessertCommand", e);
            request.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, RouterType.REDIRECT);
        }
        return router;
    }
}


