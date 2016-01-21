package com.home.it.jdbc.beans;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User
        extends AbstractDataBaseBean<Integer>
        implements DataBaseBeanInterface<Integer> {
    private Integer id;
    private String name;
    private String lastName;
    private String login;
    private String password;
    private String email;
    private Date dateOfBirth;

    public User() {
    }

    public User(String name, String lastName, String login, String password, String email, Date dateOfBirth) {
        this.name = name;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public User(int id, String name, String lastName, String login, String password, String email, Date dateOfBirth) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
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
    public void setDataFormResultSet(ResultSet resultSet) throws SQLException {
        name        = resultSet.getString(1);
        lastName    = resultSet.getString(2);
        login       = resultSet.getString(3);
        password    = resultSet.getString(4);
        email       = resultSet.getString(5);
        dateOfBirth = resultSet.getDate(  6);
    }

    @Override
    public void prepareUpdateStatement(PreparedStatement stmt) throws SQLException {
        prepareCreateStatement(stmt);
        stmt.setInt(7, id);
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

    @Override
    public void prepareCreateStatement(PreparedStatement stmt) throws SQLException {
        stmt.setString(1, name);
        stmt.setString(2, lastName);
        stmt.setString(3, login);
        stmt.setString(4, password);
        stmt.setString(5, email);
        stmt.setDate(  6, dateOfBirth);
    }

    @Override
    public void prepareReadStatement(PreparedStatement stmt, Integer id) throws SQLException {
        stmt.setInt(1, id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastname(String lastName) {
        this.lastName = lastName;
    }

    public String getlLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date setDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        return !(dateOfBirth != null ? !dateOfBirth.equals(user.dateOfBirth) : user.dateOfBirth != null);
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}