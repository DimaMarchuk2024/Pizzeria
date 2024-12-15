package com.dima.jdbc.starter.service;

import com.dima.jdbc.starter.dto.PizzaDto;

import java.util.List;

public interface PizzaService {

    PizzaDto findById(Long id);

    List<PizzaDto> findAll();

    PizzaDto save(PizzaDto pizzaDto);

    void update(PizzaDto pizzaDto);

    boolean delete(Long id);
}
