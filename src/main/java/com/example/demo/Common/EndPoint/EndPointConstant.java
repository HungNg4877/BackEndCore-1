package com.example.demo.Common.EndPoint;

public class EndPointConstant {
    public static final String BASE_API = "/api/v1";

    public static final String AUTH = BASE_API + "/auth";
    public static final String POSTS = BASE_API + "/post";
    public static final String USERS = BASE_API + "/user";
    public static final String ROLES = BASE_API + "/role";
    public static final String PERMISSIONS = BASE_API + "/permission";
    public static final String BOARDING_HOUSE = BASE_API + "/boarding_house";
    // Auth Endpoints
    public static final String AUTH_LOGIN = "/login";
    public static final String AUTH_REGISTER = "/register";
    public static final String REFRESH_TOKEN = "/refresh-token";
    public static final String AUTH_LOGOUT = "/logout";
    public static final String AUTH_VERIFY_OTP = "/verify-otp";
    public static final String AUTH_FORGOT_PASSWORD = "/forgot-password/{email}";
    public static final String AUTH_SET_PASSWORD = "/set-password";
    //
    public static final String POST_ID = "/{postId}";
    public static final String GET_POST_NEWS = "/news";
    public static final String REPORT_POST = "/report";
    public static final String SHARE = "/share";
    //
    public static final String USER_ID = "/{userId}";
    //
    public static final String VIEW_LIST = "/view-list";
}
