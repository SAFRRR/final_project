package com.safronova.webproject.controller22.command2222.impl.user;

import com.safronova.webproject.controller22.command2222.*;
import com.safronova.webproject.exception.ServiceException;
import com.safronova.webproject.model.service.UserService;
import com.safronova.webproject.model.service.ServiceProvider;
import com.safronova.webproject.model.util.localization.LocaleAttribute;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.util.Map;

public class SignUpUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
//    @AllowedRoles({ Role.GUEST })
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router;
        Map<Feedback.Key, Object> result;
//        Map<Feedback.Key, Object> result = new EnumMap<>(Feedback.Key.class);
        try{
            String email = request.getParameter(RequestParameter.EMAIL.getValue());
            String login = request.getParameter(RequestParameter.LOGIN.getValue());
            String password = request.getParameter(RequestParameter.PASSWORD.getValue());
            String passwordRepeat = request.getParameter(RequestParameter.PASSWORD_CONFIRM.getValue());
            LocaleAttribute localeAttribute = (LocaleAttribute) request.getSession().getAttribute(SessionAttribute.CURRENT_LOCALE.name());

            UserService userService = ServiceProvider.getInstance().getUserService();
            //
//            result.put(Feedback.Key.RESPONSE_CODE, Feedback.Code.OK);
//            result.put(Feedback.Key.EMAIL_FEEDBACK, LocaleKey.CHECK_YOUR_EMAIL.getValue());
//            result.put(Feedback.Key.LOGIN_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
//            result.put(Feedback.Key.PASSWORD_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
//            result.put(Feedback.Key.PASSWORD_CONFIRMATION_FEEDBACK, LocaleKey.EMPTY_MESSAGE.getValue());
            //
            result = userService.signUpUser(email, login, password, passwordRepeat, localeAttribute);
            String jsonResponse = new JSONObject()
                    .put(Feedback.Key.EMAIL_STATUS.getValue(), result.get(Feedback.Key.EMAIL_STATUS))
                    .put(Feedback.Key.LOGIN_STATUS.getValue(), result.get(Feedback.Key.LOGIN_STATUS))
                    .put(Feedback.Key.PASSWORD_STATUS.getValue(), result.get(Feedback.Key.PASSWORD_STATUS))
                    .put(Feedback.Key.PASSWORD_CONFIRMATION_STATUS.getValue(), result.get(Feedback.Key.PASSWORD_CONFIRMATION_STATUS))
                    .put(Feedback.Key.EMAIL_FEEDBACK.getValue(), localeAttribute.getLocalizedMessage(result.get(Feedback.Key.EMAIL_FEEDBACK).toString()))
                    .put(Feedback.Key.LOGIN_FEEDBACK.getValue(), localeAttribute.getLocalizedMessage(result.get(Feedback.Key.LOGIN_FEEDBACK).toString()))
                    .put(Feedback.Key.PASSWORD_FEEDBACK.getValue(), localeAttribute.getLocalizedMessage(result.get(Feedback.Key.PASSWORD_FEEDBACK).toString()))
                    .put(Feedback.Key.PASSWORD_CONFIRMATION_FEEDBACK.getValue(), localeAttribute.getLocalizedMessage(result.get(Feedback.Key.PASSWORD_CONFIRMATION_FEEDBACK).toString()))
                    .toString();
            response.setStatus(((Feedback.Code) result.get(Feedback.Key.RESPONSE_CODE)).getStatusCode());
            router = new Router(null, jsonResponse, Router.RouterType.AJAX_RESPONSE);
        }
        catch (ServiceException e) {
            response.setStatus(500);
            logger.error( "Exception occured while register:", e);
            router = new Router(PagePath.ERROR_500_PAGE.getValue(), null, Router.RouterType.FORWARD);
        }
        return router;

    }
}

