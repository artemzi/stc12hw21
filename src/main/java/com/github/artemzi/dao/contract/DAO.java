package com.github.artemzi.dao.contract;

import java.util.List;

public interface DAO<T> {
    // TODO: implement at least getById, getByName, etc
    boolean add(T element);
    boolean delete(T element);
    List<T> getAll();
}
