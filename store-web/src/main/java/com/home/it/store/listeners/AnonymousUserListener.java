package com.home.it.store.listeners;

import com.home.it.jdbc.beans.User;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Date;

public class AnonymousUserListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        if (sce.getServletContext().getAttribute("user") == null) {
            User anonymous = new User("anonymous", "anonymous", "anonymous", "anonymous", "anonymous", new Date(System.currentTimeMillis()));
            sce.getServletContext().setAttribute("user", anonymous);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
