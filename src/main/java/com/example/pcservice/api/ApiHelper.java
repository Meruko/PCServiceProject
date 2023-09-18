package com.example.pcservice.api;

import lombok.Getter;

@Getter
public class ApiHelper {
    private static final String DEFAULT_URL = "http://localhost:8081/api/public/";
    private static final String DEFAULT_URL_USERS = DEFAULT_URL + "users";
    private static final String DEFAULT_URL_ORDERS = DEFAULT_URL + "orders";
    private static final String DEFAULT_URL_CLIENT_PCS = DEFAULT_URL + "clientpcs";
    private static final String DEFAULT_URL_ORDER_TYPES = DEFAULT_URL + "ordertypes";
    private static final String DEFAULT_URL_ORDER_STATUSES = DEFAULT_URL + "orderstatuses";
    private static final String DEFAULT_URL_PC_TYPES = DEFAULT_URL + "pctypes";
    private static final String DEFAULT_URL_PC_MARKS = DEFAULT_URL + "pcmarks";
    private static final String DEFAULT_URL_ROLES = DEFAULT_URL + "roles";

    public static String getDefaultUrl() {
        return DEFAULT_URL;
    }

    public static String getUsersUrl() {
        return DEFAULT_URL_USERS;
    }

    public static String getOrdersUrl() {
        return DEFAULT_URL_ORDERS;
    }

    public static String getClientPcsUrl() {
        return DEFAULT_URL_CLIENT_PCS;
    }

    public static String getOrderTypesUrl() {
        return DEFAULT_URL_ORDER_TYPES;
    }

    public static String getOrderStatusesUrl() {
        return DEFAULT_URL_ORDER_STATUSES;
    }

    public static String getPcTypesUrl() {
        return DEFAULT_URL_PC_TYPES;
    }

    public static String getPcMarksUrl() {
        return DEFAULT_URL_PC_MARKS;
    }

    public static String getRolesUrl() {
        return DEFAULT_URL_ROLES;
    }
}
