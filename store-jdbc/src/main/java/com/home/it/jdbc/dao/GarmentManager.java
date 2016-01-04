package com.home.it.jdbc.dao;

import com.home.it.jdbc.beans.Garment;
import com.home.it.jdbc.exception.GenericDaoException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class GarmentManager {
    private DataSource dataSource;

    @Autowired
    public GarmentManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Garment> getAllGarments() {
        try (Connection con = dataSource.getConnection()) {
            prepareConnection(con);
            PreparedStatement stmt = con.prepareStatement("SELECT ID, NAME, DESCRIPTION, PRICE FROM GARMENTS");
            ResultSet resultSet = stmt.executeQuery();
            List<Garment> result = new LinkedList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                BigInteger price = BigInteger.valueOf(resultSet.getLong(4));
                result.add(new Garment(id, name, description, price));
            }
            con.commit();
            return result;
        } catch (SQLException e) {
            throw new GenericDaoException("Error reading all garments from data base", e);
        }
    }

    private void prepareConnection(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
    }
}
