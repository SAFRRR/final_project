package com.safronova.webproject.controller.command.impl.go;

import com.safronova.webproject.controller.command.Command;
import com.safronova.webproject.controller.command.PagePath;
import com.safronova.webproject.controller.command.Router;
import com.safronova.webproject.controller.command.Router.RouterType;
import jakarta.servlet.http.HttpServletRequest;

public class GoToHomePageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest req) {
        Router router = new Router(PagePath.ABOUT_PAGE, RouterType.REDIRECT);
        return router;
    }
}
