package com.home.it.store.servlets;

import com.home.it.jdbc.beans.User;
import com.home.it.jdbc.exception.GenericDaoException;
import com.home.it.store.controllers.GarmentController;
import com.home.it.store.controllers.UserController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static com.home.it.jdbc.utils.LoggingUtil.getCurrentClassName;
import static com.home.it.store.servlets.ServletConstants.*;


public class LoginServlet extends SpringContextLoaderAbstractServlet {
    private static final Logger logger = Logger.getLogger(getCurrentClassName());

    @Autowired
    private GarmentController garmentController;

    @Autowired
    private UserController userController;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(TEXT_HTML);
        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_JSP);
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

        if (params.get(LOGIN) != null) {
            try {
                User user = userController.getBylogin(params.get(EMAIL));
                if (user == null || !user.getPassword().equals(params.get(PASSWORD))) {
                    req.setAttribute(LOGIN_FAILED, true);
                } else {
                    HttpSession session = req.getSession(true);
                    session.setAttribute(USER, user);
                }
            } catch (GenericDaoException e) {
                req.setAttribute(LOGIN_FAILED, true);
            }
        }
        if (params.get(REGISTER) != null) {
            resp.sendRedirect("/store/register");
        } else {
            if (params.get(LOGOUT) != null) {
                HttpSession session = req.getSession(true);
                session.removeAttribute(USER);
            }
            req.setAttribute(GARMENTS, garmentController.getAllGarments());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(MAIN_JSP);
            dispatcher.forward(req, resp);
        }
    }
}
