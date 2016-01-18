package com.home.it.jdbc.beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnonymousUser extends AbstractDataBaseBean<Integer>
    implements DataBaseBeanInterface<Integer> {

    private Integer id;
    private String login;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public void prepareCreateStatement(PreparedStatement stmt) throws SQLException {
        stmt.setString(1, login);
    }

    @Override
    public void setDataFormResultSet(ResultSet resultSet) throws SQLException {
        login = resultSet.getString(1);
    }

    @Override
    public void prepareUpdateStatement(PreparedStatement stmt) throws SQLException {
        prepareCreateStatement(stmt);
        stmt.setInt(2, id);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
