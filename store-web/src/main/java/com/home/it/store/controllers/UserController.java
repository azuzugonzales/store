package com.home.it.store.controllers;

import com.home.it.jdbc.beans.User;
import com.home.it.jdbc.dao.GenericDao;
import com.home.it.jdbc.dao.UserManager;
import org.springframework.beans.factory.annotation.Autowired;

public class UserController {
    @Autowired
    private GenericDao<User, Integer> userDao;

    @Autowired
    private UserManager manager;

    public User getBylogin(String login) {
        return manager.getByLogin(login);
    }

    public void addUser(User user) {
        userDao.create(user);
    }
}
