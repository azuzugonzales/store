package com.home.it.store.servlets;

public class ServletConstants {
    protected final static String TEXT_HTML     = "text/html";
    protected final static String IMAGE_GIF     = "image/gif";

    /*Parameters constants*/
    protected final static String LOGIN         = "login";
    protected final static String EMAIL         = "email";
    protected final static String NAME          = "name";
    protected final static String LAST_NAME     = "lastName";
    protected final static String GARMENT       = "garment";
    protected final static String GARMENTS      = "garments";
    protected final static String ID            = "id";
    protected final static String PASSWORD      = "password";
    protected final static String LOGIN_FAILED  = "loginFailed";
    protected final static String USER          = "user";
    protected final static String REGISTER      = "register";
    protected final static String LOGOUT        = "logout";
    protected final static String DATE_OF_BIRTH = "dateOfBirth";
    protected final static String ORDER         = "order";

    protected final static String DATE_FORMAT   = "YYYY-mm-DD";
    protected final static String DATE_ERROR    = "dateError";
    protected final static String EMAIL_ERROR   = "emailError";
    protected final static String LOGIN_ERROR   = "loginError";
    protected final static String LOGIN_MESSAGE = "Login is already used";
    protected final static String EMAIL_MESSAGE = "Email is already used";
    protected final static String DATE_MESSAGE  = "Date should be at \"YYYY-mm-DD\" format";

    protected final static String EMAIL_NOT_VALID = "Email is not valid";

    /*JSP mapping constants*/

    protected final static String GARMENT_JSP   = "/JSP/garment.jsp";
    protected final static String LOGIN_JSP     = "/JSP/login.jsp";
    protected final static String REGISTER_JSP  = "/JSP/register.jsp";
    protected final static String MAIN_JSP      = "/JSP/main.jsp";
    protected final static String BOOK_JSP      = "/JSP/book.jsp";

    /*PATH constants*/
    protected final static String STORE_MAIN   = "/store/main";
}
