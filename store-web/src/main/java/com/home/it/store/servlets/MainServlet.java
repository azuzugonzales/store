package com.home.it.store.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.home.it.store.servlets.ServletConstants.MAIN_JSP;
import static com.home.it.store.servlets.ServletConstants.TEXT_HTML;

public class MainServlet extends SpringContextLoaderAbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(TEXT_HTML);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(MAIN_JSP);
        dispatcher.forward(req, resp);
    }
}
