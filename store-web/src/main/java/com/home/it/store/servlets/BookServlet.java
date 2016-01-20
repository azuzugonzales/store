package com.home.it.store.servlets;

import com.home.it.jdbc.beans.Garment;
import com.home.it.jdbc.beans.User;
import com.home.it.store.controllers.GarmentController;
import com.home.it.store.controllers.OrderController;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.home.it.store.servlets.ServletConstants.*;

public class BookServlet extends SpringContextLoaderAbstractServlet {
    @Autowired
    private GarmentController garmentController;

    @Autowired
    private OrderController orderController;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType(TEXT_HTML);
        req.setAttribute(GARMENTS, garmentController.getAllGarments());
        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(BOOK_JSP);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(TEXT_HTML);
        int garmentID = Integer.valueOf(req.getParameter(ID));
        Garment garment = garmentController.getGarment(garmentID);
        User user = (User)req.getSession().getAttribute(USER);
        OrderController.Order order =
                (OrderController.Order)req.getSession().getAttribute(ORDER);
        if (order == null) {
            order = orderController.createOrder(user.getId());
            req.getSession().setAttribute(ORDER, order);
        }
        orderController.addGarmentToOrder(order, garment);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(BOOK_JSP);
        dispatcher.forward(req, resp);
    }
}
