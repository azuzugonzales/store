package com.home.it.jdbc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class MySqlManagerFactory extends AbstractManagerFactory {
    @Autowired
    protected DataSource dataSource;

    @Override
    public UserManager getUserManager() {
        return new UserManager(dataSource);
    }

    @Override
    public GarmentManager getGarmentManager() {
        return new GarmentManager(dataSource);
    }

    @Override
    public AnonymousUserManager getAnonymousUserManager() {
        return new AnonymousUserManager();
    }


}
