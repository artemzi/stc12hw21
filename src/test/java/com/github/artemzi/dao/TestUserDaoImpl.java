package com.github.artemzi.dao;

import com.github.artemzi.dao.manager.Factory;
import com.github.artemzi.dao.manager.Utils;
import com.github.artemzi.pojo.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.*;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Utils.class, DriverManager.class, UserDaoImpl.class})
class TestUserDaoImpl {
    public TestUserDaoImpl() {
    }

    @Mock User user;
    @Mock PreparedStatement statement;
    @Mock Connection connection;
    @Mock Factory connectionManager;
    @Mock ResultSet resultSet;
    private UserDaoImpl userDao;

    @Before public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(Utils.class);

        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(connectionManager.getConnection()).thenReturn(connection);
//        when(Utils.prepareStatement(connection, anyString(), false, anyObject())).thenReturn(statement);
//        when(Utils.setValues(statement, anyObject())).thenReturn(true);

        userDao = new UserDaoImpl(connectionManager);
    }

    @Ignore
    @Test public void testGetUserByEmail() throws SQLException {
        when(statement.executeQuery()).thenReturn(resultSet);

        Assert.assertNotNull(userDao.getUserByEmail("demo@example.com"));
    }

    @Ignore
    @Test public void testAddUser() throws SQLException {
        when(statement.executeUpdate()).thenReturn(1);

        Assert.assertTrue(userDao.add(user));
    }
}


