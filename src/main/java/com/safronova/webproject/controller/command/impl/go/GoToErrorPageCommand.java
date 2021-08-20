package com.safronova.webproject.controller.command.impl.go;

import com.safronova.webproject.controller.command.Command;
import com.safronova.webproject.controller.command.PagePath;
import com.safronova.webproject.controller.command.RequestAttribute;
import com.safronova.webproject.controller.command.Router;
import com.safronova.webproject.controller.command.Router.RouterType;
import jakarta.servlet.http.HttpServletRequest;

public class GoToErrorPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Throwable throwable = (Throwable) request.getAttribute(RequestAttribute.EXCEPTION_CLASS);
        if (throwable != null && request.getAttribute(RequestAttribute.EXCEPTION_CLASS) != null) {
            request.getSession().setAttribute(RequestAttribute.EXCEPTION, throwable);
        }
        return new Router(PagePath.ERROR_PAGE, RouterType.REDIRECT);
    }
}
