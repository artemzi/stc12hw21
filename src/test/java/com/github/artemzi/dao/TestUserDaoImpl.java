package com.github.artemzi.dao;

import com.github.artemzi.dao.manager.Factory;
import com.github.artemzi.dao.manager.Utils;
import com.github.artemzi.pojo.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Utils.class)
class TestUserDaoImpl {
    public TestUserDaoImpl() {
    }

    @Mock User user;
    private UserDaoImpl userDao;

    @Before public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Factory connectionManager = mock(Factory.class);
        Connection connection = mock(Connection.class);
        PreparedStatement statement = mock(PreparedStatement.class);
        PowerMockito.mockStatic(Utils.class);

        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(connectionManager.getConnection()).thenReturn(connection);
        when(statement.executeUpdate()).thenReturn(0);

        userDao = new UserDaoImpl(connectionManager);
    }

    @Test public void testAddUser() {
        Assert.assertTrue(userDao.add(user));
    }
}


