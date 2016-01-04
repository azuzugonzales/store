package com.home.it.jdbc.beans;

import com.home.it.jdbc.exception.GenericDaoException;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

public abstract class AbstractDataBaseBean<PK extends Serializable> implements DataBaseBeanInterface<PK> {
    protected final String READ_SQL;
    protected final String DELETE_SQL;
    protected final String CREATE_SQL;
    protected final String UPDATEE_SQL;

    private static final String RESOURCES_DB_PROPERTIES = "db.properties";

    {
        Properties props = new Properties();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            props.load(classLoader.getResourceAsStream(RESOURCES_DB_PROPERTIES));
            READ_SQL        = props.getProperty("sql." + this.getClass().getSimpleName().toLowerCase() + "read");
            DELETE_SQL      = props.getProperty("sql." + this.getClass().getSimpleName().toLowerCase() + "delete");
            CREATE_SQL      = props.getProperty("sql." + this.getClass().getSimpleName().toLowerCase() + "create");
            UPDATEE_SQL     = props.getProperty("sql." + this.getClass().getSimpleName().toLowerCase() + "update");
        } catch (IOException e) {
            throw new GenericDaoException("Error loading SQL definitions", e);
        }
    }
}
