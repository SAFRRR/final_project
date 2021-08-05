package com.safronova.webproject.controller.command.impl;

import com.safronova.webproject.controller.command.PagePath;
import com.safronova.webproject.controller.command.RequestAttribute;
import com.safronova.webproject.controller.command.Router;
import com.safronova.webproject.controller.command.Router.RouterType;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.entity.Basket;
import com.safronova.webproject.model.entity.SignInData;
import com.safronova.webproject.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class SignInCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);

        final ServiceProvider serviceProvider = ServiceProvider.getInstance();
        final UserService userService = serviceProvider.getUserService();
        final BasketService basketService = serviceProvider.getBasketService();

        SignInData signInData = new SignInData();
        signInData.setEmail(email);
        signInData.setPassword(password);

        try {
            Optional<User> optionalUser = userService.signIn(signInData);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                Basket basket = basketService.findUserBasket(user.getId());
                user.setBasket(basket);
                request.getSession().setAttribute(RequestAttribute.USER, user);
                request.getSession().setAttribute(RequestAttribute.ROLE, user.getRole());
                router = new Router(PagePath.GO_TO_PROFILE_PAGE, RouterType.REDIRECT);
            } else {
                request.getSession().setAttribute(RequestAttribute.WRONG_DATA, true);
                router = new Router(PagePath.GO_TO_LOGIN_PAGE, RouterType.REDIRECT);
            }
        } catch (ServiceException e) {
            logger.error("Error at SignInCommand", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, RouterType.REDIRECT);
        }
        return router;
    }
}

