package com.github.artemzi.dao.contract;

import java.util.List;

public interface DAO<T> {
    // TODO: implement at least getById, getByName, etc
    boolean add(String element);
    boolean delete(String element);
    List<T> getAll();
}
