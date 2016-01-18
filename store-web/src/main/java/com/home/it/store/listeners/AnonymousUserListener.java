package com.home.it.store.listeners;

import com.home.it.jdbc.beans.User;
import com.home.it.jdbc.dao.AnonymousUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;

public class AnonymousUserListener implements ServletRequestListener {
    @Autowired
    private AnonymousUserManager anonymousUserManager;

    private AutowireCapableBeanFactory ctx;

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        if (ctx == null) {
            WebApplicationContext context = WebApplicationContextUtils
                    .getWebApplicationContext(sre.getServletContext());
            ctx = context.getAutowireCapableBeanFactory();
            ctx.autowireBean(this);
        }

        HttpServletRequest request = (HttpServletRequest)sre.getServletRequest();
        HttpSession session = request.getSession(true);
        if (session.getAttribute("user") == null) {
            String login = anonymousUserManager.createAnonymouseUser().getLogin();
            User anonymous = new User("anonymous", "", login, "", "", new Date(System.currentTimeMillis()));
            session.setAttribute("user", anonymous);
        }
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {}
}
