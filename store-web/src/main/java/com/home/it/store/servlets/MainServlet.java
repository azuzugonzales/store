package com.home.it.store.servlets;

import com.home.it.jdbc.beans.Garment;
import com.home.it.jdbc.beans.User;
import com.home.it.jdbc.exception.GenericDaoException;
import com.home.it.store.controllers.GarmentController;
import com.home.it.store.controllers.UserController;
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

public class MainServlet extends SpringContextLoaderAbstractServlet {
    @Autowired
    private UserController userController;

    @Autowired
    private GarmentController garmentController;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(TEXT_HTML);
        req.setAttribute(GARMENT, getCurrentGarment(req));
        if (req.getSession().getAttribute("maxGarmentId") == null) {
            List<Garment> garments = garmentController.getAllGarments();
            int maxId = getMaxIdFromList(garments);
            req.getSession().setAttribute("maxGarmentId", maxId);
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(MAIN_JSP);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(TEXT_HTML);
        req.setAttribute(GARMENT, getCurrentGarment(req));
        Enumeration<String> parameterNames = req.getParameterNames();
        Map<String, String> params = new HashMap<>();
        while (parameterNames.hasMoreElements()) {
            String current = parameterNames.nextElement();
            params.put(current, req.getParameter(current));
        }

        if (params.get(LOGIN) != null) {
            try {
                User user = userController.getByLogin(params.get(EMAIL));
                if (user == null || !user.getPassword().equals(params.get(PASSWORD))) {
                    req.setAttribute(LOGIN_FAILED, true);
                } else {
                    HttpSession session = req.getSession(true);
                    session.setAttribute(USER, user);
                }
            } catch (GenericDaoException e) {
                req.setAttribute(LOGIN_FAILED, true);

            } finally {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(MAIN_JSP);
            }
        }
        if (params.get(REGISTER) != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(REGISTER_JSP);
            dispatcher.forward(req, resp);
        } else if (params.get(LOGIN) != null) {
            HttpSession session = req.getSession(true);
            session.removeAttribute(USER);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(MAIN_JSP);
            dispatcher.forward(req, resp);
        }
    }

    private Garment getCurrentGarment(HttpServletRequest req) {
        String garmentNumber = req.getParameter(ID);
        int id;
        if (garmentNumber == null || garmentNumber == "") {
            id = 1;
        } else {
            id = Integer.parseInt(garmentNumber);
        }
        return garmentController.getGarment(id);
    }

    private int getMaxIdFromList(List<Garment> garments) {
        int result = 0;
        for (Garment garment : garments) {
            if (result < garment.getId()) {
                result = garment.getId();
            }
        }
        return result;
    }
}
