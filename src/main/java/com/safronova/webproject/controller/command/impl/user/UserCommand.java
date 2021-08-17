package com.safronova.webproject.controller.command.impl.user;

import com.safronova.webproject.controller.command.Command;
import com.safronova.webproject.controller.command.PagePath;
import com.safronova.webproject.controller.command.RequestAttribute;
import com.safronova.webproject.controller.command.Router;
import com.safronova.webproject.controller.command.Router.RouterType;
import com.safronova.webproject.model.entity.Role;
import com.safronova.webproject.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;

public abstract class UserCommand implements Command {

    @Override
    public final Router execute(HttpServletRequest request) {
        return verifyUser(request);
    }

    private Router verifyUser(HttpServletRequest request) {
        Router router;
        User user = (User) request.getSession().getAttribute(RequestAttribute.USER);
        if (user != null) {
            if (user.getRole() == Role.USER) {
                return handle(request);
            } else {
                router = new Router(PagePath.ERROR_404_PAGE, RouterType.REDIRECT);
            }
        } else {
            request.setAttribute(RequestAttribute.MESSAGE, true);
            router = new Router(PagePath.GO_TO_SIGNIN_PAGE, RouterType.FORWARD);
        }
        return router;
    }

    protected abstract Router handle(HttpServletRequest request);
}
