package com.home.it.jdbc.dao;

import com.home.it.jdbc.beans.User;
import com.home.it.jdbc.exception.GenericDaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Date;
import java.util.Calendar;

import static org.testng.Assert.assertEquals;

@ContextConfiguration(locations = {"classpath:store-jdbc-test-context.xml"})
public class TestGenericDaoImpl extends AbstractTestNGSpringContextTests {
    @Autowired
    private GenericDao<User, Integer> sut;

    private User doc;

    //Given
    @BeforeTest
    public void setUp() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(1990, 0, 27, 0, 0, 0);
        doc = new User("Bill", "Duke", "bill", "12345", "bill@gmail.com", new Date(cal.getTimeInMillis()));
    }
    @Test(priority = 0,
    //then
    expectedExceptions = {GenericDaoException.class})
    public void testReadUserNotExists() {
        //when
        sut.read(1);
    }

    @Test(priority = 10)
    public void testCreate() {
        //when
        int id = sut.create(doc);
        //then
        assertEquals(id, 1);
    }

    @Test(priority = 20,
    //then
    expectedExceptions = {GenericDaoException.class})
    public void testCreateUserAlreadyExists() {
        //when
        sut.create(doc);
    }

    @Test(priority = 30)
    public void testRead() {
        //when
        User result = sut.read(1);
        //then
        assertEquals(result, doc);
    }
}
