package com.safronova.webproject.controller.command.impl.go;

import com.safronova.webproject.controller.command.*;
import com.safronova.webproject.controller.command.Router.RouterType;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.entity.Dessert;
import com.safronova.webproject.model.entity.Storage;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoToDessertDetailPageCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        String dessertId = request.getParameter(RequestParameter.FLOWER_ID);

        final ServiceProvider serviceProvider = ServiceProvider.getInstance();
        final FlowerService flowerService = serviceProvider.getFlowerService();
        final StorageService storageService = serviceProvider.getStorageService();

        try {
            Dessert dessert = dessertService.findById(dessertId);
            Storage storage = storageService.findByFlowerId(dessertId);
            request.setAttribute(RequestAttribute.FLOWER, dessert);
            request.setAttribute(RequestAttribute.STORAGE, storage);
            request.getSession().setAttribute(RequestAttribute.CURRENT_PAGE, PagePath.FLOWER_DETAIL_BY_ID + dessertId);
            router = new Router(PagePath.ITEM_DETAIL_PAGE, RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at FlowerDetailCommand", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, RouterType.REDIRECT);
        }
        return router;
    }
}
