package com.safronova.webproject.controller.command.impl.go;
import com.safronova.webproject.controller.command.PagePath;
import com.safronova.webproject.controller.command.RequestAttribute;
import com.safronova.webproject.controller.command.Router;
import com.safronova.webproject.controller.command.Router.RouterType;
import com.safronova.webproject.controller.command.impl.admin.AdminCommand;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.entity.DessertType;
import com.safronova.webproject.model.service.DessertTypeService;
import com.safronova.webproject.model.service.ServiceProvider;
import com.safronova.webproject.model.util.RegexpPropertiesReader;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import java.util.Properties;

public class GoToAddDessertPageCommand extends AdminCommand {
    private static final Logger logger = LogManager.getLogger();
    private static final Properties properties = RegexpPropertiesReader.getProperties();
    private static final String REGEXP_NAME = properties.getProperty("regexp.dessert.name");
    private static final String REGEXP_PRICE = properties.getProperty("regexp.dessert.price");
    private static final String REGEXP_DESCRIPTION = properties.getProperty("regexp.dessert.description");
    private static final String REGEXP_WEIGHT = properties.getProperty("regexp.dessert.weight");
    private static final String REGEXP_QUANTITY = properties.getProperty( "regexp.dessert.quantity");

    @Override
    protected Router handle(HttpServletRequest request) {
        Router router;

        final ServiceProvider serviceProvider = ServiceProvider.getInstance();
        final DessertTypeService dessertTypeService = serviceProvider.getDessertTypeService();

        try {
            List<DessertType> dessertTypeList = dessertTypeService.findAll();
            request.setAttribute(RequestAttribute.DESSERT_TYPE_LIST, dessertTypeList);
            request.setAttribute(RequestAttribute.REGEXP_NAME, REGEXP_NAME);
            request.setAttribute(RequestAttribute.REGEXP_PRICE, REGEXP_PRICE);
            request.setAttribute(RequestAttribute.REGEXP_WEIGHT, REGEXP_WEIGHT);
            request.setAttribute(RequestAttribute.REGEXP_DESCRIPTION, REGEXP_DESCRIPTION);
            request.setAttribute(RequestAttribute.REGEXP_COUNT, REGEXP_QUANTITY);
            router = new Router(PagePath.ADD_DESSERT_PAGE, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at GoToAddDessertCommand", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, RouterType.REDIRECT);
        }
        return router;
    }
}
