package com.safronova.webproject.controller.command.impl;

import com.safronova.webproject.controller.command.*;
import com.safronova.webproject.controller.command.Router.RouterType;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.dao.ResultCode;
import com.safronova.webproject.model.entity.SignUpData;
import com.safronova.webproject.model.service.ServiceProvider;
import com.safronova.webproject.model.service.UserService;
import com.safronova.webproject.model.util.MailSender;
import com.safronova.webproject.model.util.PasswordEncryptor;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignUpCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        final ServiceProvider serviceProvider = ServiceProvider.getInstance();
        final UserService userService = serviceProvider.getUserService();

        final String username = request.getParameter(RequestParameter.USERNAME);
        final String firstName = request.getParameter(RequestParameter.FIRSTNAME);
        final String lastName = request.getParameter(RequestParameter.LASTNAME);
        final String address = request.getParameter(RequestParameter.ADDRESS);
        final String email = request.getParameter(RequestParameter.EMAIL);
        final String phone = request.getParameter(RequestParameter.PHONE);
        final String password = PasswordEncryptor.generateRandomPassword();

        SignUpData signUpData = new SignUpData(username, firstName, lastName, address, phone);

        signUpData.setPassword(password);
        signUpData.setEmail(email);
        router = new Router(PagePath.GO_TO_SIGNUP_PAGE, RouterType.FORWARD);
        try {
            ResultCode resultCode = userService.signUp(signUpData);
            switch (resultCode) {
                case ERROR_DUPLICATE_EMAIL:
                    request.setAttribute(RequestAttribute.DUPLICATE_EMAIL, true);
                    break;
                case SUCCESS:
                    MailSender.send(email, MailSender.messageEmailUser(username, password));
                    request.setAttribute(RequestAttribute.EMAIL_SENT, true);
                    router = new Router(PagePath.EMAIL_PAGE, RouterType.FORWARD);
                    break;
            }
        } catch (ServiceException e) {
            logger.error("Error at SignUpCommand", e);
            request.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, RouterType.REDIRECT);
        }
        return router;
    }
}
