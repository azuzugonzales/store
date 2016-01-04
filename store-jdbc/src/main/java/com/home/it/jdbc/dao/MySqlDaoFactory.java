package com.home.it.jdbc.dao;

import com.home.it.jdbc.beans.DataBaseBeanInterface;
import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;

public class MySqlDaoFactory<T extends DataBaseBeanInterface> extends AbstractDaoFactory {
    @Autowired
    protected DataSource dataSource;

    private Class<T> type;

    protected MySqlDaoFactory(Class<T> type) {
        this.type = type;
    }

    @Override
    public GenericDao getDao() {
        return new GenericDaoImpl(dataSource, type);
    }
}
