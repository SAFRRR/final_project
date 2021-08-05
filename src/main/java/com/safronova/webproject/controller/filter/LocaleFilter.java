package com.safronova.webproject.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

@WebFilter(urlPatterns = {"/Controller"})
public class LocaleFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(true);
        String queryString = httpRequest.getQueryString();
        String prevRequest = RequestAttribute.CONTROLLER_URL + queryString;
        if (session.getAttribute(RequestAttribute.LOCALE) == null) {
            session.setAttribute(RequestAttribute.LOCALE, Locale.getDefault());
        }
        if (queryString != null) {
            session.setAttribute(RequestAttribute.PREV_REQUEST, prevRequest);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
