package com.safronova.webproject.controller.command.impl.go;

import com.safronova.webproject.controller.command.Command;
import com.safronova.webproject.controller.command.PagePath;
import com.safronova.webproject.controller.command.RequestAttribute;
import com.safronova.webproject.controller.command.Router;
import com.safronova.webproject.controller.command.Router.RouterType;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.entity.Dessert;
import com.safronova.webproject.model.entity.DessertType;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GoToItemPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        final ServiceProvider serviceProvider = ServiceProvider.getInstance();
        final FlowerService flowerService = serviceProvider.getFlowerService();
        final FlowerTypeService flowerTypeService = serviceProvider.getFlowerTypeService();

        try {
            List<DessertType> dessertTypeList = dessertTypeService.findAll();
            List<Dessert> dessertList = dessertService.findAll();
            request.setAttribute(RequestAttribute.FLOWER_LIST, dessertList);
            request.setAttribute(RequestAttribute.FLOWER_TYPE_LIST, dessertTypeList);
            if (dessertList.isEmpty()) {
                request.setAttribute(RequestAttribute.EMPTY_LIST, true);
            }
            router = new Router(PagePath.ITEM_PAGE, RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at GoToItemPageCommand", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, RouterType.REDIRECT);
        }
        return router;
    }
}
