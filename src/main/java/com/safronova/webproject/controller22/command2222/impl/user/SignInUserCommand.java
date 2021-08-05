package com.safronova.webproject.controller22.command2222.impl.user;

import com.safronova.webproject.controller22.command2222.*;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.entity2.CustomUser;
import com.safronova.webproject.model.service.ServiceProvider;
import com.safronova.webproject.model.service.UserService;
import com.safronova.webproject.model.util.localization.LocaleAttribute;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.util.Map;

public class SignInUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router;
        Map<Feedback.Key, Object> result;
        try{
            String email = request.getParameter(RequestParameter.EMAIL.getValue());
            String password = request.getParameter(RequestParameter.PASSWORD.getValue());
            CustomUser user = null;
            UserService userService = ServiceProvider.getInstance().getUserService();
            result = userService.signInUser(email, password);
            LocaleAttribute localeAttribute = (LocaleAttribute) request.getSession().getAttribute(SessionAttribute.CURRENT_LOCALE.name());


            String jsonResponse = new JSONObject()
                    .put(Feedback.Key.EMAIL_STATUS.getValue(), result.get(Feedback.Key.EMAIL_STATUS))
                    .put(Feedback.Key.PASSWORD_STATUS.getValue(), result.get(Feedback.Key.PASSWORD_STATUS))
                    .put(Feedback.Key.EMAIL_FEEDBACK.getValue(), localeAttribute.getLocalizedMessage(result.get(Feedback.Key.EMAIL_FEEDBACK).toString()))
                    .put(Feedback.Key.PASSWORD_FEEDBACK.getValue(), localeAttribute.getLocalizedMessage(result.get(Feedback.Key.PASSWORD_FEEDBACK).toString()))
                    .toString();

            if (Boolean.TRUE.equals(result.get(Feedback.Key.EMAIL_STATUS)) && Boolean.TRUE.equals(result.get(Feedback.Key.PASSWORD_STATUS))) {
                user = userService.findUserByEmail(email).get();
                request.getSession(true).setAttribute(RequestAttribute2.CURRENT_USER.getValue(), user);
                request.getSession().setAttribute(RequestAttribute2.LOGIN_NAME.getValue(), user.getLogin());
                request.getSession().setAttribute(RequestAttribute2.CURRENT_USER_ROLE.getValue(),
                        user.getRole().getValue());
            }
            response.setStatus(((Feedback.Code) result.get(Feedback.Key.RESPONSE_CODE)).getStatusCode());
            router = new Router(null, jsonResponse, Router.RouterType.AJAX_RESPONSE);
        } catch (ServiceException e) {
            response.setStatus(500);
            logger.error( "Exception occured while user logining: {} {}", e.getMessage(), e.getStackTrace());
            router = new Router(PagePath.ERROR_500_PAGE.getValue(), null, Router.RouterType.FORWARD);

        }
        return router;
    }
}
