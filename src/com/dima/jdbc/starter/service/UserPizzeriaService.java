package com.dima.jdbc.starter.service;

import com.dima.jdbc.starter.dto.PizzaDto;
import com.dima.jdbc.starter.dto.UserPizzeriaDto;

import java.util.List;

public interface UserPizzeriaService {

    UserPizzeriaDto findById(Long id);

    List<UserPizzeriaDto> findAll();

    UserPizzeriaDto save(UserPizzeriaDto pizzaDto);

    void update(UserPizzeriaDto pizzaDto);

    boolean delete(Long id);
}
