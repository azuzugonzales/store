package com.home.it.jdbc.beans;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Garment extends AbstractDataBaseBean<Integer>
        implements DataBaseBeanInterface<Integer> {

    private Integer id;
    private String name;
    private String description;
    private BigInteger price;
    private String picturePath;

    public Garment() {
    }

    public Garment(Integer id, String name, String description, BigInteger price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

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
        stmt.setString(1, name);
        stmt.setString(2, description);
        stmt.setLong(3, price.longValue());
        stmt.setString(4, picturePath);
    }

    @Override
    public void setDataFormResultSet(ResultSet resultSet) throws SQLException {
        name        = resultSet.getString(1);
        description = resultSet.getString(2);
        price       = BigInteger.valueOf(
                        resultSet.getLong(3));
        picturePath = resultSet.getString(4);
    }

    @Override
    public void prepareUpdateStatement(PreparedStatement stmt) throws SQLException {
        prepareCreateStatement(stmt);
        stmt.setInt(4, id);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public String getPicturePath() {
        return picturePath;
    }
}
