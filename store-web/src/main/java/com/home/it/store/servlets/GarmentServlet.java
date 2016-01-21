package com.home.it.store.servlets;

import com.home.it.jdbc.beans.Garment;
import com.home.it.store.controllers.GarmentController;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.home.it.store.servlets.ServletConstants.*;

public class GarmentServlet extends SpringContextLoaderAbstractServlet {

    @Autowired
    private GarmentController garmentController;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(TEXT_HTML);
        /*String garmentNumber = req.getParameter(ID);
        int id;
        if (garmentNumber == null || garmentNumber == "") {
            id = 1;
        } else {
            id = Integer.parseInt(garmentNumber);
        }
        if (req.getSession().getAttribute("maxGarmentId") == null) {
            List<Garment> garments = garmentController.getAllGarments();
            int maxId = getMaxIdFromList(garments);
            req.getSession().setAttribute("maxGarmentId", maxId);
        }
        Garment garment = garmentController.getGarment(id);
        req.setAttribute(GARMENT, garment);*/
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(MAIN_JSP);
        dispatcher.forward(req, resp);
    }

    /*@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(TEXT_HTML);
        Enumeration<String> parameterNames = req.getParameterNames();
        Map<String, String> params = new HashMap<>();
        while (parameterNames.hasMoreElements()) {
            String current = parameterNames.nextElement();
            params.put(current, req.getParameter(current));
        }
        if (params.containsKey("next")) {
            req.setAttribute(GARMENT, getNextGarment(params.get("garmentId")));
        }
            if (params.containsKey("prev")) {
            req.setAttribute(GARMENT, getPrevGarment(params.get("garmentId")));
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(MAIN_JSP);
        dispatcher.forward(req, resp);
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

    private Garment getNextGarment(String garmentId) {
        int id = Integer.parseInt(garmentId);
        return garmentController.getGarment(++id);
    }

    private Garment getPrevGarment(String garmentId) {
        int id = Integer.parseInt(garmentId);
        return garmentController.getGarment(--id);
    }*/
}
