package com.github.artemzi.services;

import com.github.artemzi.dao.Factory;
import com.github.artemzi.exceptions.DAOException;
import com.github.artemzi.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.github.artemzi.dao.Utils.prepareStatement;

public class UserService {
    private Factory connectionManager;
    private static final String SQL_SELECT_ALL = "SELECT u.id, u.name, u.email, u.password, r.name AS role " +
            "FROM users u JOIN roles r ON r.id = u.role_id";

    public UserService() {
        this.connectionManager = Factory.getInstance("javabase.jdbc");
    }

    public UserService(Factory connectionManager) {
        this.connectionManager = connectionManager;
    }

    public boolean add(String task) {
        // TODO
        return false;
    }

    public boolean delete(String task) {
        // TODO
        return false;
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = prepareStatement(connection, SQL_SELECT_ALL, false);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                ));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return users;
    }
}
