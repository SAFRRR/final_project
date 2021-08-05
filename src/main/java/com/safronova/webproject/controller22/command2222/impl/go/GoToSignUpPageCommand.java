package com.safronova.webproject.controller22.command2222.impl.go;

import com.safronova.webproject.controller22.command2222.Command;
import com.safronova.webproject.controller22.command2222.PagePath;
import com.safronova.webproject.controller22.command2222.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToSignUpPageCommand implements Command {
    @Override
//    public Router execute(HttpServletRequest request) {
//        return new Router(PagePath.SIGNUP_PAGE.getValue(), null, Router.RouterType.FORWARD);
//    }
    public Router execute(HttpServletRequest request, HttpServletResponse response) {

        return new Router(PagePath.SIGNUP_PAGE.getValue(), null, Router.RouterType.FORWARD);

    }

}
