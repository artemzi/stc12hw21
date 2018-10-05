package com.github.artemzi.services;

import com.github.artemzi.dao.Factory;
import com.github.artemzi.dao.contract.DAO;
import com.github.artemzi.exceptions.DAOException;
import com.github.artemzi.pojo.User;
import com.github.artemzi.utility.Helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.github.artemzi.dao.Utils.prepareStatement;

public class UserService implements DAO<User> {
    private Factory connectionManager;
    private static final String SQL_SELECT_ALL = "SELECT u.id, u.name, u.email, u.password, r.name AS role " +
            "FROM users u JOIN roles r ON r.id = u.role_id";
    private static final String SQL_SELECT_BY_EMAIL = "SELECT u.id, u.name, u.email, u.password, r.name AS role, u.role_id\n" +
            "FROM users u JOIN roles r ON r.id = u.role_id WHERE u.email LIKE ?";
    private static final String SQL_INSERT = "INSERT INTO users VALUES (DEFAULT, ?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM users WHERE id=?";

    public UserService() {
        this.connectionManager = Factory.getInstance("javabase.jdbc");
    }

    public UserService(Factory connectionManager) {
        this.connectionManager = connectionManager;
    }

    public boolean addFromStringsParam(String name, String email, String password, int roleId) {
        if (!validateParams(name, email, password, roleId)) {
            return false;
        }

        String passwordHashed = Helpers.hashPassword(password);
        User user = new User(name, email, passwordHashed, roleId);
        return add(user);
    }

    private boolean validateParams(String name, String email, String password, int roleId) {
        // TODO add proper validation
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || roleId < 1 || roleId > 2) {
            return false;
        }

        User user = getUserByEmail(email);
        return user == null; // if user found by email return false
    }

    public boolean checkAuth(String email, String password) {
        User user = getUserByEmail(email);
        return user != null && user.getPassword().equals(Helpers.hashPassword(password));
    }

    private User getUserByEmail(String email) {
        User user = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = prepareStatement(connection, SQL_SELECT_BY_EMAIL, false, email);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                user = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot get user by email" + e);
        }
        return user;
    }

    private User map(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                resultSet.getString("role"),
                resultSet.getInt("role_id")
        );
    }

    @Override
    public boolean add(User user) {
        Object[] values = {
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRoleId()
        };

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = prepareStatement(connection, SQL_INSERT, false, values)) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Add user failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return true;
    }

    @Override
    public boolean delete(User user) {
        try (
                Connection connection = connectionManager.getConnection();
                PreparedStatement statement = prepareStatement(connection, SQL_DELETE, false, user.getId());
        ) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Delete user failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return true;
    }

    @Override
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
