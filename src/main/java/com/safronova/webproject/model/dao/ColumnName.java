package com.safronova.webproject.model.dao;

/**
 * Describes all column name
 *
 * @author Anna Safronova
 */
public final class ColumnName {
    /* User Table */
    public static final String USERS_ID = "u_id";
    public static final String USERS_USERNAME = "u_login";
    public static final String USERS_PASSWORD = "u_password";
    public static final String USERS_EMAIL = "u_email";
    public static final String USERS_ROLE = "u_role";
    public static final String USERS_FIRST_NAME = "u_name";
    public static final String USERS_LAST_NAME = "u_surname";
    public static final String USERS_ADDRESS = "u_address";
    public static final String USERS_PHONE = "u_phone";

    /* Basket table */
    public static final String BASKET_ID = "b_id";
    public static final String BASKET_TOTAL_COST = "b_total_cost";

    /* BasketDessert table */
    public static final String BASKET_DESSERT_ID = "bd_id";
    public static final String BASKET_DESSERT_BASKET_ID = "bd_basket_id";
    public static final String BASKET_DESSERT_DESSERT_ID = "bd_dessert_id";
    public static final String BASKET_DESSERT_COUNT = "bd_count";
    public static final String BASKET_DESSERT_SUB_TOTAL = "bd_sub_total";

    /* DessertType table */
    public static final String DESSERT_TYPE_ID = "dt_id";
    public static final String DESSERT_TYPE_CATEGORY = "dt_category";
    public static final String DESSERT_TYPE_DESCRIPTION = "dt_description";

    /* Dessert table */
    public static final String DESSERT_ID = "d_id";
    public static final String DESSERT_NAME = "d_name";
    public static final String DESSERT_DESCRIPTION = "d_description";
    public static final String DESSERT_PRICE = "d_price";
    public static final String DESSERT_WEIGHT = "d_weight";
    public static final String DESSERT_IMAGE = "d_image";
    public static final String DESSERT_QUANTITY = "d_quantity";

    /* Storage table */
    public static final String STORAGE_ID = "st_id";
    public static final String STORAGE_COUNT = "st_count";

    /*Order table*/
    public static final String ORDER_ID = "o_id";
    public static final String ORDER_STATUS = "o_status";
    public static final String ORDER_DATE_DELIVERY = "o_date_delivery";
    public static final String ORDER_TOTAL_COST = "o_total_cost";
    public static final String ORDER_USER_ID = "o_user_id";
    public static final String ORDER_TIME_ORDER = "o_time_order";
    public static final String ORDER_ADDRESS = "o_address";
    public static final String ORDER_DATE = "o_date_order";
    public static final String ORDER_CASH = "o_cash";

    /* OrderDessert table */
    public static final String ORDER_DESSERT_ID = "od_id";
    public static final String ORDER_DESSERT_COUNT = "od_count";
    public static final String ORDER_DESSERT_SUB_TOTAL = "od_sub_total";

    private ColumnName() {
    }
}
