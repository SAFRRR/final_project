package com.safronova.webproject.controller.command.impl.admin.impl;

import com.safronova.webproject.controller.command.PagePath;
import com.safronova.webproject.controller.command.RequestAttribute;
import com.safronova.webproject.controller.command.RequestParameter;
import com.safronova.webproject.controller.command.Router;
import com.safronova.webproject.controller.command.Router.RouterType;
import com.safronova.webproject.controller.command.impl.admin.AdminCommand;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.entity.Dessert;
import com.safronova.webproject.model.entity.DessertType;
import com.safronova.webproject.model.service.DessertService;
import com.safronova.webproject.model.service.DessertTypeService;
import com.safronova.webproject.model.service.ServiceProvider;
import com.safronova.webproject.model.service.StorageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ResourceBundle;

public class AddDessertCommand extends AdminCommand {
    private static final Logger logger = LogManager.getLogger();
    private static final String BUNDLE_NAME = "imagePath";
    private static final String PATH_IMG = "path.image";

    @Override
    protected Router handle(HttpServletRequest request) {
        Router router;
        Part inputFile;
        InputStream inputStream;
        OutputStream outStream;

        final String nameDessert = request.getParameter(RequestParameter.NAME);
        final String description = request.getParameter(RequestParameter.DESCRIPTION);
        final String category = request.getParameter(RequestParameter.CATEGORY);
        final String weight = request.getParameter(RequestParameter.WEIGHT);
        final String price = request.getParameter(RequestParameter.PRICE);
        final String count = request.getParameter(RequestParameter.COUNT);


        final ServiceProvider serviceProvider = ServiceProvider.getInstance();
        final DessertService dessertService = serviceProvider.getDessertService();
        final DessertTypeService dessertTypeService = serviceProvider.getDessertTypeService();
        final StorageService storageService = serviceProvider.getStorageService();

        try {
            DessertType dessertType = dessertTypeService.findById(category);
            Dessert dessert = dessertService.createDessert(nameDessert, description, price,  weight,  dessertType);
            storageService.createStorage(dessert, count);
            inputFile = request.getPart(RequestParameter.IMAGE);
            inputStream = inputFile.getInputStream();
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            final String path = ResourceBundle.getBundle(BUNDLE_NAME).getString(PATH_IMG);
            File imageFile = new File(path + dessert.getDessertImage());
            if (!imageFile.exists()) {
                imageFile.createNewFile();
            }
            outStream = new FileOutputStream(imageFile);
            outStream.write(buffer);
            outStream.close();
            router = new Router(PagePath.GO_TO_DESSERT_LIST, RouterType.REDIRECT);
        } catch (ServiceException | IOException | ServletException e) {
            logger.error("Error at AddItemCommand", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, RouterType.REDIRECT);
        }
        return router;
    }
}
