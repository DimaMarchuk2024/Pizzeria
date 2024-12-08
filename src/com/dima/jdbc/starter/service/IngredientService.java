package com.dima.jdbc.starter.service;

import com.dima.jdbc.starter.dto.IngredientDto;
import com.dima.jdbc.starter.dto.PizzaDto;

import java.util.List;

public interface IngredientService {

    IngredientDto findById(Long id);

    List<IngredientDto> findAll();

    IngredientDto save(IngredientDto ingredientDto);

    void update(IngredientDto ingredientDto);

    boolean delete(Long id);
}
