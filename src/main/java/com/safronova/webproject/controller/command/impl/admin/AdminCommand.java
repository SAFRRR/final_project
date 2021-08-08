package com.safronova.webproject.controller.command.impl.admin;

import com.safronova.webproject.controller.command.Command;
import com.safronova.webproject.controller.command.PagePath;
import com.safronova.webproject.controller.command.RequestAttribute;
import com.safronova.webproject.controller.command.Router;
import com.safronova.webproject.model.entity.Role;
import com.safronova.webproject.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;

public abstract class AdminCommand implements Command {
    @Override
    public final Router execute(HttpServletRequest request) {
        return verifyAdmin(request);
    }

    private Router verifyAdmin(HttpServletRequest request) {
        Router router;
        User user = (User) request.getSession().getAttribute(RequestAttribute.USER);
        if (user != null) {
            if (user.getRole() == Role.ADMIN) {
                return handle(request);
            } else {
                router = new Router(PagePath.ERROR_404_PAGE, Router.RouterType.REDIRECT);
            }
        } else {
            request.setAttribute(RequestAttribute.MESSAGE, true);
            router = new Router(PagePath.GO_TO_SIGNIN_PAGE, Router.RouterType.FORWARD);
        }
        return router;
    }
    protected abstract Router handle(HttpServletRequest req);
}


