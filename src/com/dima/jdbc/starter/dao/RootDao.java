package com.dima.jdbc.starter.dao;

import java.util.List;
import java.util.Optional;

public interface RootDao<K, E> {
    Optional<E> findById(K id);

    List<E> findAll();

    E save(E entity);

    void update(E entity);

    boolean delete(K id);


}
