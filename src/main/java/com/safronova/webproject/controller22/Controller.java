package com.safronova.webproject.controller22;

import com.safronova.webproject.controller22.command2222.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;




@MultipartConfig
@WebServlet(name = "controller", urlPatterns = { "/controller" })
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    private static final long serialVersionUID = -6656382914151361780L;
    private static final String PAGES_PATH = "/WEB-INF/pages/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(RequestParameter.COMMAND.getValue());
        Command command = CommandDefiner.defineCommand(commandName);
        Router router = command.execute(request, response);
        switch (router.getRouterType()) {
            case AJAX_RESPONSE:
                response.getWriter().write(router.getJsonResponse());
                break;
            case FORWARD:
                request.getRequestDispatcher(router.getPageToGo()).forward(request, response);
                break;
            case REDIRECT:
                response.sendRedirect(request.getContextPath() + router.getPageToGo());
                break;
            default:
                logger.error("Invalid RouterType value: {}", commandName);
                request.getRequestDispatcher(PagePath.ERROR_500_PAGE.getValue()).forward(request, response);
        }
    }
}


    //
//    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        RequestMethod method = RequestMethod.valueOf(request.getMethod());
//        String url = (String) request.getAttribute(ORIGINAL_URL);
//        Optional<Command> command = CommandType.getCommand(url, method);
//
//        if (command.isPresent()) {
//            CommandResult commandResult = command.get().execute(request);
//
//            String resultDetail = commandResult.getDetail();
//            CommandResult.RouteType routeType = commandResult.getRouteType();
//
//            switch (routeType) {
//                case FORWARD:
//                    request.getRequestDispatcher(PAGES_PATH + resultDetail).forward(request, response);
//                    break;
//                case REDIRECT:
//                    response.sendRedirect(resultDetail);
//                    break;
//                case JSON:
//                    response.getWriter().write(resultDetail);
//                    break;
//                default:
//                    logger.error("Invalid route type: " + routeType.name());
//                    response.sendRedirect(ApplicationPath.INTERNAL_SERVER_ERROR_URL);
//            }
//        } else {
//            response.sendRedirect(NOT_FOUND_ERROR_URL);
//        }
//    }
//}
