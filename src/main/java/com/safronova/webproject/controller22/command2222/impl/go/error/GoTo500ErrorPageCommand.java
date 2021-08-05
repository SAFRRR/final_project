package com.safronova.webproject.controller22.command2222.impl.go.error;

import com.safronova.webproject.controller22.command2222.AllowedRoles;
import com.safronova.webproject.controller22.command2222.Command;
import com.safronova.webproject.controller22.command2222.PagePath;
import com.safronova.webproject.controller22.command2222.Router;
import com.safronova.webproject.model.entity2.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoTo500ErrorPageCommand  implements Command {

    @AllowedRoles({ Role.GUEST, Role.USER, Role.ADMIN })
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        return new Router(PagePath.ERROR_500_PAGE.getValue(), null, Router.RouterType.FORWARD);
    }
}
