package com.safronova.webproject.controller.command.impl.admin.impl;

import com.safronova.webproject.controller.command.PagePath;
import com.safronova.webproject.controller.command.RequestAttribute;
import com.safronova.webproject.controller.command.RequestParameter;
import com.safronova.webproject.controller.command.Router;
import com.safronova.webproject.controller.command.Router.RouterType;
import com.safronova.webproject.controller.command.impl.admin.AdminCommand;
import com.safronova.webproject.exception.DaoException;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.entity.OrderDessert;
import com.safronova.webproject.model.entity.Storage;
import com.safronova.webproject.model.service.BasketDessertService;
import com.safronova.webproject.model.service.OrderService;
import com.safronova.webproject.model.service.ServiceProvider;
import com.safronova.webproject.model.service.StorageService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ChangeOrderStatusCommand extends AdminCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected Router handle(HttpServletRequest request) {
        Router router;
        final String orderStatus = request.getParameter(RequestParameter.ORDER_STATUS);
        final String orderId = request.getParameter(RequestParameter.ORDER_ID);
        final ServiceProvider serviceProvider = ServiceProvider.getInstance();
        final OrderService orderService = serviceProvider.getOrderService();
        final StorageService storageService = serviceProvider.getStorageService();


        try {
            orderService.changeStatus(orderStatus, orderId);
            if (orderStatus.equals("REJECTED")){
                List<OrderDessert> orderDessertList = orderService.findByOrder(Integer.parseInt(orderId));
//
                for (OrderDessert orderDessert : orderDessertList){
                    int count = orderDessert.getCount();
                    storageService.updateStorage(String.valueOf(orderDessert.getDessert().getId()),String.valueOf(count));
                }

            }
            if(orderStatus.equals("APPROVED") || orderStatus.equals("INPROCESS")){
                List<OrderDessert> orderDessertList = orderService.findByOrder(Integer.parseInt(orderId));
                for (OrderDessert orderDessert : orderDessertList){
                    int count = 0;
                    storageService.updateStorage(String.valueOf(orderDessert.getDessert().getId()),String.valueOf(count));
                }
            }


            router = new Router(PagePath.GO_TO_ORDER_LIST, RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Error at ChangeOrderStatusCommand", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, RouterType.REDIRECT);
        }
        return router;
    }
}
