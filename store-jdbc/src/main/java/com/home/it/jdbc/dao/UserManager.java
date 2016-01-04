package com.home.it.jdbc.dao;

import com.home.it.jdbc.beans.User;
import com.home.it.jdbc.exception.GenericDaoException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.*;

public class UserManager {
    private DataSource dataSource;

    @Autowired
    protected UserManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User getByLogin(String login) {
        try (Connection con = dataSource.getConnection()) {
            prepareConnection(con);
            PreparedStatement stmt = con.prepareStatement("SELECT ID, NAME, LAST_NAME, PASSWORD, EMAIL, DATE_OF_BIRTH FROM USERS WHERE LOGIN = ?");
            stmt.setString(1, login);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String pwd = resultSet.getString(4);
                String email = resultSet.getString(5);
                Date date = resultSet.getDate(6);
                User result = new User(id, name, lastName, login, pwd, email, date);
                con.commit();
                return result;
            }
        } catch (SQLException e) {
            throw new GenericDaoException("Error reading user from data base", e);
        }
        throw new GenericDaoException("User not found!");
    }

    private void prepareConnection(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
    }
}