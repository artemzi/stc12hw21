package com.github.artemzi.dao;

import com.github.artemzi.dao.manager.ConnectionManager;
import com.github.artemzi.pojo.User;
import com.github.artemzi.services.UserService;
import com.github.artemzi.utility.Helpers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TestUserDaoImpl {
    @InjectMocks private UserDaoImpl userDao;
    @InjectMocks private UserService userService;
    @Mock User user;

    @BeforeEach void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test void testAddUser() {
        when(userDao.add(user)).thenReturn(true);

        assertThat(userService.addUser("Demo", "demo@example.com", "secret", 1),
                is(notNullValue()));
    }

    @Test void testCheckAuth() {
        when(userDao.getUserByEmail("demo@example.com")).thenAnswer((Answer<User>) invocation -> new User(
                "Demo",
                "demo@example.com",
                Helpers.hashPassword("secret"),
                1
        ));

        Assertions.assertTrue(userDao.checkAuth("demo@example.com", "secret"));
    }
}


