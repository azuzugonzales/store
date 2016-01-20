package com.home.it.store.servlets;

import com.home.it.jdbc.beans.User;
import com.home.it.jdbc.exception.GenericDaoException;
import com.home.it.store.controllers.UserController;
import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static com.home.it.store.servlets.ServletConstants.*;

public class RegisterServlet extends SpringContextLoaderAbstractServlet {
    @Autowired
    private UserController userController;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(TEXT_HTML);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(REGISTER_JSP);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(TEXT_HTML);
        Enumeration<String> parameterNames = req.getParameterNames();
        Map<String, String> params = new HashMap<>();
        while (parameterNames.hasMoreElements()) {
            String current = parameterNames.nextElement();
            params.put(current, req.getParameter(current));
        }
        boolean errorsInForm = false;
        try {
            if (!validateDate(params.get(DATE_OF_BIRTH))) {
                req.setAttribute(DATE_ERROR, DATE_MESSAGE);
                errorsInForm = true;
            }
            if (!validateEmail(params.get(EMAIL))) {
                req.setAttribute(EMAIL_ERROR, EMAIL_NOT_VALID);
                errorsInForm = true;
            }
            if (!errorsInForm) {
                User user = createUser(params);
                userController.addUser(user);
            }
        } catch (GenericDaoException e) {
            if (e.getMessage().toLowerCase().contains("email_unique")) {
                req.setAttribute(EMAIL_ERROR, EMAIL_MESSAGE);
            }
            if (e.getMessage().toLowerCase().contains("login_unique")) {
                req.setAttribute(LOGIN_ERROR, LOGIN_MESSAGE);
            }
            errorsInForm = true;
        }
        if (errorsInForm) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(REGISTER_JSP);
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect(STORE_MAIN);
        }
    }

    private User createUser(Map<String, String> params) {
        User user = new User();
        user.setName(params.get(NAME));
        user.setLastname(params.get(LAST_NAME));
        user.setLogin(params.get(LOGIN));
        user.setEmail(params.get(EMAIL));
        user.setPassword(params.get(PASSWORD));
        user.setDateOfBirth(Date.valueOf(params.get(DATE_OF_BIRTH)));
        return user;
    }

    private boolean validateDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        try {
            sdf.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    private boolean validateEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }
}
