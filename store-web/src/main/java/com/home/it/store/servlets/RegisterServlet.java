package com.home.it.store.servlets;

import com.home.it.jdbc.beans.User;
import com.home.it.store.controllers.UserController;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
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
        User user = new User();
        user.setName(params.get(NAME));
        user.setLastname(params.get(LAST_NAME));
        user.setLogin(params.get(LOGIN));
        user.setEmail(params.get(EMAIL));
        user.setPassword(params.get(PASSWORD));
        user.setDateOfBirth(Date.valueOf(DATE_OF_BIRTH));
        userController.addUser(user);
        resp.sendRedirect(STORE_LOGIN);
    }
}
