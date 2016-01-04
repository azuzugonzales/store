package com.home.it.store.servlets;

import com.home.it.jdbc.beans.Garment;
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
import java.util.List;
import java.util.Map;

import static com.home.it.store.servlets.ServletConstants.*;
import static com.home.it.jdbc.utils.LoggingUtil.getCurrentClassName;


public class LoginServlet extends SpringContextLoaderAbstractServlet {
    private static final Logger logger = Logger.getLogger(getCurrentClassName());

    @Autowired
    private GarmentController garmentController;

    @Autowired
    private UserController userController;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> parameterNames = req.getParameterNames();
        StringBuilder sb = new StringBuilder("?");
        while (parameterNames.hasMoreElements()) {
            String current = parameterNames.nextElement();
            sb.append(current + "=" + req.getParameter(current));
            sb.append("&");
        }

        String log = req.getRequestURL() + sb.toString();
        logger.debug("Request received :" + (log.substring(0, log.length() -1)));

        resp.setContentType(TEXT_HTML);
        List<Garment> garments = garmentController.getAllGarments();
        req.setAttribute(GARMENTS, garments);
        RequestDispatcher dispatcher = req.getRequestDispatcher(LOGIN_JSP);
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
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(REGISTER_JSP);
            dispatcher.forward(req, resp);
        }
        if (params.get(LOGOUT) != null) {
            HttpSession session = req.getSession(true);
            session.removeAttribute(USER);
        }
        req.setAttribute(GARMENTS, garmentController.getAllGarments());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(LOGIN_JSP);
        dispatcher.forward(req, resp);
    }
}