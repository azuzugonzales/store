package com.home.it.jdbc.beans;

import com.home.it.jdbc.exception.GenericDaoException;

import java.io.IOException;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public abstract class AbstractDataBaseBean<PK extends Serializable> implements DataBaseBeanInterface<PK> {
    protected final String READ_SQL;
    protected final String DELETE_SQL;
    protected final String CREATE_SQL;
    protected final String UPDATE_SQL;

    private static final String RESOURCES_DB_PROPERTIES = "db.properties";

    {
        Properties props = new Properties();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            props.load(classLoader.getResourceAsStream(RESOURCES_DB_PROPERTIES));
            READ_SQL        = props.getProperty("sql." + this.getClass().getSimpleName().toLowerCase() + "read");
            DELETE_SQL      = props.getProperty("sql." + this.getClass().getSimpleName().toLowerCase() + "delete");
            CREATE_SQL      = props.getProperty("sql." + this.getClass().getSimpleName().toLowerCase() + "create");
            UPDATE_SQL = props.getProperty("sql." + this.getClass().getSimpleName().toLowerCase() + "update");
        } catch (IOException e) {
            throw new GenericDaoException("Error loading SQL definitions", e);
        }
    }

    @Override
    public String getCreateSql() {
        return CREATE_SQL;
    }

    @Override
    public String getReadSql() {
        return READ_SQL;
    }

    @Override
    public String getUpdateSql() {
        return UPDATE_SQL;
    }

    @Override
    public String getDeleteSql() {
        return DELETE_SQL;
    }

    public void prepareReadStatement(PreparedStatement stmt, Integer id) throws SQLException {
        stmt.setInt(1, id);
    }
}
