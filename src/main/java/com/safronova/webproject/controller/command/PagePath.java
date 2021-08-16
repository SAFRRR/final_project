package com.safronova.webproject.controller.command;

public final class PagePath {
    public static final String HOME_PAGE = "pages/home_page.jsp";
    public static final String SIGNIN_PAGE = "pages/signin_page.jsp";
    public static final String SIGNUP_PAGE = "pages/signup_page.jsp";
    public static final String EMAIL_PAGE = "pages/common/email_send.jsp";
    public static final String PROFILE_PAGE = "pages/user/profile_page.jsp";
    public static final String ORDER_HISTORY_PAGE = "pages/user/order_history_page.jsp";
    public static final String ERROR_404_PAGE = "pages/common/404.jsp";

    public static final String ERROR_PAGE = "pages/common/error.jsp";
    public static final String ITEM_PAGE = "pages/item_page.jsp";
    public static final String BASKET_PAGE = "pages/user/basket_page.jsp";
    public static final String CHECKOUT_PAGE = "pages/user/checkout_page.jsp";
    public static final String DESSERT_DETAIL_PAGE = "pages/item_detail_page.jsp";
    public static final String ADD_DESSERT_PAGE = "pages/admin/add_item_page.jsp";
    public static final String UPDATE_DESSERT_PAGE = "pages/admin/update_dessert_page.jsp";
    public static final String DESSERT_LIST_PAGE = "pages/admin/dessert_list_page.jsp";
    public static final String ORDER_ALL_INFO_PAGE = "pages/admin/order_all_info_page.jsp";
    public static final String ORDER_DETAIL_PAGE = "pages/admin/order_detail_page.jsp";
    public static final String GO_TO_SIGNIN_PAGE = "controller?command=go_to_signin_page_command";
    public static final String GO_TO_SIGNUP_PAGE = "controller?command=go_to_signup_page_command";
    public static final String GO_TO_PROFILE_PAGE = "controller?command=go_to_profile_page_command";
    public static final String GO_TO_ORDER_LIST = "controller?command=go_to_orders_page_command";
    public static final String GO_TO_DESSERT_LIST = "controller?command=go_to_dessert_list_page_command";
    public static final String GO_TO_HOME_PAGE = "controller?command=go_to_home_page_command";
    public static final String GO_TO_BASKET_PAGE = "controller?command=go_to_basket_page_command";
    public static final String GO_TO_CHECKOUT_PAGE = "controller?command=go_to_check_out_page_command";
    public static final String GO_TO_ADD_DESSERT_PAGE_COMMAND = "controller?command=go_to_add_dessert_page_command";
    public static final String GO_TO_ORDER_HISTORY_PAGE = "controller?command=go_to_order_history_page_command";
    public static final String DESSERT_DETAIL_BY_ID = "controller?command=go_to_dessert_detail_page_command&dessertId=";

    private PagePath() {
    }
}
