package com.safronova.webproject.controller.command.impl.user.impl;

import com.safronova.webproject.controller.command.PagePath;
import com.safronova.webproject.controller.command.RequestAttribute;
import com.safronova.webproject.controller.command.RequestParameter;
import com.safronova.webproject.controller.command.Router;
import com.safronova.webproject.controller.command.Router.RouterType;
import com.safronova.webproject.controller.command.impl.user.UserCommand;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.dao.ResultCode;
import com.safronova.webproject.model.entity.SignInData;
import com.safronova.webproject.model.entity.SignUpData;
import com.safronova.webproject.model.entity.User;
import com.safronova.webproject.model.service.ServiceProvider;
import com.safronova.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class SettingsEditCommand extends UserCommand {
    private static final Logger logger = LogManager.getLogger();
    @Override
    protected Router handle(HttpServletRequest request) {
        Router router;

        final User user = (User) request.getSession().getAttribute(RequestAttribute.USER);
        final String firstName = request.getParameter(RequestParameter.FIRSTNAME);
        final String lastName = request.getParameter(RequestParameter.LASTNAME);
        final String address = request.getParameter(RequestParameter.ADDRESS);
        final String username = request.getParameter(RequestParameter.USERNAME);
        final String phone = request.getParameter(RequestParameter.PHONE);
        final String currentPassword = request.getParameter(RequestParameter.CURRENT_PASSWORD);
        final String newPassword = request.getParameter(RequestParameter.NEW_PASSWORD);
        final String confirmPassword = request.getParameter(RequestParameter.CONFIRM_PASSWORD);


        final SignInData signInData = new SignUpData();
        signInData.setEmail(user.getEmail());
        signInData.setPassword(currentPassword);

        final ServiceProvider serviceProvider = ServiceProvider.getInstance();
        final UserService userService = serviceProvider.getUserService();

        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAddress(address);
        user.setPhone(phone);
        user.setPassword(currentPassword);

        try {
            ResultCode resultCode = userService.updateUser(signInData, user, newPassword, confirmPassword);
            switch (resultCode) {
                case WRONG_PASSWORD: {
                    request.getSession().setAttribute(RequestAttribute.WRONG_PASSWORD, true);
                    break;
                }
                case WRONG_CONFIRMATION: {
                    request.getSession().setAttribute(RequestAttribute.WRONG_CONFIRMATION, true);
                    break;
                }
                case SUCCESS: {
                    request.getSession().setAttribute(RequestAttribute.SUCCESS_EDIT, true);
                    request.getSession().setAttribute(RequestAttribute.USER, user);
                    break;
                }
            }
            router = new Router(PagePath.GO_TO_PROFILE_PAGE, RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Error at PersonalEditCommand", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, RouterType.REDIRECT);
        }
        return router;
    }
}

