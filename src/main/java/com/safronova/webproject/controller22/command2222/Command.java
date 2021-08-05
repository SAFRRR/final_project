package com.safronova.webproject.controller22.command2222;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Command {
//    Router execute(HttpServletRequest request);
    Router execute(HttpServletRequest request, HttpServletResponse response);

}
