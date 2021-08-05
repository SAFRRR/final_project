package com.safronova.webproject.controller.command.impl.user;

import com.safronova.webproject.controller.command.Command;
import com.safronova.webproject.controller.command.PagePath;
import com.safronova.webproject.controller.command.RequestAttribute;
import com.safronova.webproject.controller.command.Router;
import com.safronova.webproject.controller.command.Router.RouterType;
import jakarta.servlet.http.HttpServletRequest;

public abstract class UserCommand implements Command {

    @Override
    public final Router execute(HttpServletRequest request) {
        return checkUser(request);
    }

    private Router checkUser(HttpServletRequest request) {
        if (request.getSession().getAttribute(RequestAttribute.USER) == null) {
            request.setAttribute(RequestAttribute.MESSAGE, true);
            Router router = new Router(PagePath.GO_TO_LOGIN_PAGE, RouterType.FORWARD);
            return router;
        } else {
            return process(request);
        }
    }

    protected abstract Router handle(HttpServletRequest request);
}
