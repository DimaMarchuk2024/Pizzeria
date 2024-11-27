package com.dima.jdbc.starter.service;

import com.dima.jdbc.starter.dto.PizzaDto;

import java.util.List;

public interface RootService <D,K>{
    D findById(K id);

    List<D> findAll();
    D save(D pizzaDto);

    void update(D pizzaDto);

    boolean delete(K id);
}
