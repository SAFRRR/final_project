package com.safronova.webproject.model.dao22;

public final class ColumnName {

    /* customusers table */
    public static final String USER_ID = "user_id";
    public static final String USER_EMAIL = "email";
    public static final String USER_LOGIN_NAME = "login_name";
    public static final String USER_PASSWORD_HASH = "password_hash";
    public static final String USER_SALT = "salt";
    public static final String USER_IMAGE_URL = "image_url";
    public static final String USER_ROLE_TYPE = "role_type";
    public static final String USER_STATUS = "status";
    public static final String USER_BANNED_TO_DATE = "banned_to_datetime";
//token
    public static final String TOKEN_ID ="token_id";
    public static final String TOKEN_EMAIL ="email";
    public static final String TOKEN ="token";
    public static final String TOKEN_STATUS ="status";
    public static final String TOKEN_EXPIRATION ="expiration_date";
    public static final String TOKEN_NEW_EMAIL ="new_email";

    /* users table */
//    public static final String USER_ID = "u_id";
    public static final String USER_NAME = "u_name";
    public static final String USER_LOGIN = "u_login";
    public static final String USER_PASSWORD = "u_password";
//    public static final String USER_EMAIL = "u_email";
    public static final String USER_ROLE = "u_role";
    public static final String USER_ADDRESS = "u_address";
    public static final String USER_AVATAR = "u_avatar";
    public static final String USER_SURNAME = "u_surname";
    public static final String USER_ORDER = "u_order";
//    public static final String USER_STATUS = "u_status";

    /* statuses table */
    public static final String STATUS_ID = "s_id";
    public static final String STATUS_NAME = "s_name";

    /* roles table */
    public static final String ROLE_ID = "r_id";
    public static final String ROLE_NAME = "r_name";

    /* promocodes table */
    public static final String PROMOCODE_ID = "p_id";
    public static final String PROMOCODE_NAME = "p_name";
    public static final String PROMOCODE_DISCOUNT = "p_discount";

    /* orders table */
    public static final String ORDER_ID = "o_id";
    public static final String ORDER_DATE = "o_date";
    public static final String ORDER_TOTAL_PRICE = "o_total_price";
    public static final String ORDER_ADDRESS = "o_address";
    public static final String ORDER_COMMENT = "o_comment";
    public static final String ORDER_GETTING_TYPE = "o_getting_type";
    public static final String ORDER_PAYMENT_TYPE = "o_payment_type";
    public static final String ORDER_PROMOCODE = "o_promocode";

    /* ordered_desserts table */
    public static final String ORDERED_DESSERT_ID = "od_id";
    public static final String ORDERED_DESSERT_DESSERT = "od_dessert";
    public static final String ORDERED_DESSERT_COUNT = "od_count";
    public static final String ORDERED_DESSERT_PRICE = "od_price";

    /* desserts table */
    public static final String DESSERT_ID = "d_id";
    public static final String DESSERT_NAME = "d_name";
    public static final String DESSERT_CATEGORY = "d_category";
    public static final String DESSERT_PICTURE = "d_picture";
    public static final String DESSERT_PRICE = "d_price";
    public static final String DESSERT_DESCRIPTION = "d_description";
    public static final String DESSERT_WEIGHT = "d_weight";

    /* cafes table */
    public static final String CAFE_ID = "c_id";
    public static final String CAFE_PHONE = "c_phone";
    public static final String CAFE_OPEN_TIME = "c_open_time";
    public static final String CAFE_CLOSE_TIME = "c_close_time";
    public static final String CAFE_ADDRESS = "c_address";

    /* addresses table */
    public static final String ADDRESS_ID = "a_id";
    public static final String ADDRESS_CITY = "a_city";
    public static final String ADDRESS_STREET = "a_street";
    public static final String ADDRESS_HOUSE = "a_house";
    public static final String ADDRESS_APARTMENT = "a_apartment";
    public static final String ADDRESs_FLOOR = "a_floor";

    private ColumnName(){}
}
