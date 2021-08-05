package com.safronova.webproject.controller22.command2222;

public class Router {
    public enum RouterType{
        AJAX_RESPONSE, FORWARD, REDIRECT;
    }

    private final String pageToGo; //pagePath
    private final String jsonResponse;
    private final RouterType routerType;


    public Router(String  pageToGo, String jsonResponse, RouterType routerType){
        this.pageToGo = pageToGo;
        this.jsonResponse = jsonResponse;
        this.routerType = routerType;
    }

    public String getPageToGo() { return pageToGo; }

    public String getJsonResponse() {
        return jsonResponse;
    }

    public RouterType getRouterType() {
        return routerType;
    }

}


