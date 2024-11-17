package com.dima.jdbc.starter.dao.interfaceDao;

import java.util.List;
import java.util.Optional;

public interface RootDao<T> {
    Optional<T> findById(Long id);

    List<T> findAll();

    T save(T entity);

    void update(T entity);

    boolean delete(Long id);





}
