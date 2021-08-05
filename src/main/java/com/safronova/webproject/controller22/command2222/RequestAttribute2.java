package com.safronova.webproject.controller22.command2222;

import com.safronova.webproject.controller22.EnumValue;

public enum RequestAttribute2 implements EnumValue {

//    ALIEN("alien"),
//    AVERAGE_RATING("averageRating"),
//    ALIEN_LIST("aliensList"),
//    ALIENS_IMAGES_LIST("aliensImagesList"),
//    ALIEN_COMMENTS("alienComments"),
    LOGIN_NAME("login_name"),
    CURRENT_USER_ROLE("currentUserRole"),
    CURRENT_USER_ID("currentUserId"),
    CURRENT_USER_LOGIN("currentUserLogin"),
    CURRENT_USER_EMAIL("currentUserEmail"),
    CURRENT_USER("currentUser"),
    CURRENT_PAGE("currentPage"),
    PAGES_COUNT("pagesCount");
//    ALIEN_IMAGES("alienImages");

    private String value;

    private RequestAttribute2(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

}
