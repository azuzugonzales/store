package com.home.it.store.servlets;

import com.home.it.store.controllers.GarmentController;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class GarmentServlet extends SpringContextLoaderAbstractServlet {


    @Autowired
    private GarmentController garmentController;

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


    }
}
