package com.home.it.jdbc.dao;

import com.home.it.jdbc.beans.DataBaseBeanInterface;
import com.home.it.jdbc.exception.GenericDaoException;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.*;

import static com.home.it.jdbc.utils.LoggingUtil.getCurrentClassName;

public class GenericDaoImpl <T extends DataBaseBeanInterface<PK>, PK extends Serializable> implements GenericDao <T, PK> {
    private final static Logger logger = Logger.getLogger(getCurrentClassName());

    private Class<T> type;
    private DataSource dataSource;

    protected GenericDaoImpl(DataSource dataSource, Class<T> type) {
        this.dataSource = dataSource;
        this.type = type;
    }


    @Override
    public PK create(T objectToCreate) {
        logger.info("Received request to create " + type.getSimpleName() + "...start recording to DB");
        try (Connection con = dataSource.getConnection()) {
            prepareConnection(con);
            PreparedStatement stmt =
                    con.prepareStatement(objectToCreate.getCreateSql(),
                            Statement.RETURN_GENERATED_KEYS);
            objectToCreate.prepareCreateStatement(stmt);
            logger.debug(stmt.toString());
            stmt.execute();
            ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.next()) {
                PK id = (PK) new Integer(resultSet.getInt(1));
                objectToCreate.setId(id);
                con.commit();
                return id;
            }
        } catch (Exception e) {
            GenericDaoException external;
            if (e.getMessage().toLowerCase().contains("unique")) {
                external = new GenericDaoException(e.getMessage(), e);
            } else {
                external = new GenericDaoException("Error saving user to data base", e);
            }
            throw external;
        }
        throw new GenericDaoException("Error saving user to data base");
    }

    @Override
    public T read(PK id) {
        logger.info("Received request for " + type.getSimpleName() + " with id = " + id + " ...start searching in DB");
        try (Connection con = dataSource.getConnection()) {
            prepareConnection(con);
            T result = type.newInstance();
            String request = result.getReadSql();
            logger.debug("Started SQL creation : " + request);
            PreparedStatement stmt = con.prepareStatement(request);
            result.prepareReadStatement(stmt, id);
            logger.debug("Statement creation - SQL prepared : " + stmt.toString());
            ResultSet resultSet = stmt.executeQuery();
            logger.debug("Statement executed - Result received " + resultSet.getMetaData().toString());
            if (resultSet.next()) {
                result.setId(id);
                result.setDataFormResultSet(resultSet);
                con.commit();
                return result;
            }
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            logger.error(e);
            throw new GenericDaoException("Error reading user from data base", e);
        }
        logger.info("No records of " + type.getSimpleName() + " with id = " + id + "wasn't wound in DB");
        throw new GenericDaoException("Error reading user from data base");
    }

    @Override
    public void update(T objectToUpdate) {
        try(Connection con = dataSource.getConnection()) {
            prepareConnection(con);
            String request = objectToUpdate.getUpdateSql();
            logger.info("Started SQL creation : " + request);
            PreparedStatement stmt = con.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            objectToUpdate.prepareUpdateStatement(stmt);
            logger.debug("Statement creation - SQL prepared : " + stmt.toString());
            int countOfUpdateRows = stmt.executeUpdate();
            logger.debug("Statement execute - Update " + countOfUpdateRows + " rows");
            if (countOfUpdateRows == 1) {
                logger.debug("Commiting transaction");
                con.commit();
                return;
            } else {
                logger.debug("Rolling back transaction");
                con.rollback();
                throw  new GenericDaoException("Error updating user 0 or more than 1 user was found. ROLLBACK!");
            }
        } catch (SQLException e) {
            logger.error("Error updating " + type.getSimpleName().toLowerCase() + " ", e);
            throw new GenericDaoException("Error updating " + type.getSimpleName().toLowerCase(), e);
        }
    }

    @Override
    public void delete(T objectToDelete) {

    }

    private void prepareConnection(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
    }
}
