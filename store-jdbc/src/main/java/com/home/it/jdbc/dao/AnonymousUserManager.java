package com.home.it.jdbc.dao;

import com.home.it.jdbc.beans.AnonymousUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class AnonymousUserManager {
    @Autowired
    private GenericDao<AnonymousUser, Integer> anonymousUserDao;

    public AnonymousUser createAnonymousUser() {
        AnonymousUser result = new AnonymousUser();
        String login = UUID.randomUUID().toString();
        result.setLogin(login);
        anonymousUserDao.create(result);
        result.setLogin("Anonymous_" + result.getId());
        anonymousUserDao.update(result);
        return result;
    }
}
