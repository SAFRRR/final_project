package com.safronova.webproject.controller.command.impl;

import com.safronova.webproject.controller.command.Command;
import com.safronova.webproject.controller.command.PagePath;
import com.safronova.webproject.controller.command.Router;
import com.safronova.webproject.controller.command.Router.RouterType;
import jakarta.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        request.getSession(false).invalidate();
        Router router = new Router(PagePath.ABOUT_PAGE, RouterType.REDIRECT);
        return router;
    }
}
