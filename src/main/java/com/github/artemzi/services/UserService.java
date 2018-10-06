package com.github.artemzi.services;

import com.github.artemzi.dao.contracts.UserDao;
import com.github.artemzi.dao.UserDaoImpl;
import com.github.artemzi.pojo.User;

public class UserService {
    private UserDao userDAO;

    public UserService() {
        this.userDAO = new UserDaoImpl();
    }

    public User getUserByEmail(String email) {
        return this.userDAO.getUserByEmail(email);
    }

    public boolean checkAuth(String email, String password) {
        return this.userDAO.checkAuth(email, password);
    }

    public boolean addUser(String name, String email, String password, int roleId) {
        return this.userDAO.addFromStringsParam(name, email, password, roleId);
    }
}
