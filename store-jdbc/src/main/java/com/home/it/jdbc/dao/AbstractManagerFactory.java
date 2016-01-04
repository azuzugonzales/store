package com.home.it.jdbc.dao;

public abstract class AbstractManagerFactory {
    public static AbstractManagerFactory getManagerFactory() {
        return new MySqlManagerFactory();
    }

    public abstract UserManager getUserManager();

    public abstract GarmentManager getGarmentManager();
}
