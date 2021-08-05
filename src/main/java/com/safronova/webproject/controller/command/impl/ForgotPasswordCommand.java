package com.safronova.webproject.controller.command.impl;

import com.safronova.webproject.controller.command.*;
import com.safronova.webproject.controller.command.Router.RouterType;
import com.safronova.webproject.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ForgotPasswordCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        String email = request.getParameter(RequestParameter.RECOVER_EMAIL);

        final ServiceProvider serviceProvider = ServiceProvider.getInstance();
        final UserService userService = serviceProvider.getUserService();

        try {
            if (userService.forgetPassword(email)) {
                request.getSession().setAttribute(RequestAttribute.EMAIL_SENT, true);
            } else {
                request.getSession().setAttribute(RequestAttribute.EMAIL_NOT_EXISTS, true);
            }
            router = new Router(PagePath.GO_TO_LOGIN_PAGE, RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Error at ForgetPasswordCommand", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, RouterType.REDIRECT);
        }
        return router;
    }
}
