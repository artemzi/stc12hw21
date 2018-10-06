package com.github.artemzi.dao.contracts;

import com.github.artemzi.pojo.User;

public interface UserDao extends DAO<User> {
    User getUserByEmail(String email);
    boolean checkAuth(String email, String password);
    boolean addFromStringsParam(String name, String email, String password, int roleId);
}
