package com.dima.jdbc.starter.mapper.impl;

import com.dima.jdbc.starter.dto.CompositionOfPizzaDto;
import com.dima.jdbc.starter.entity.CompositionOfPizzaEntity;
import com.dima.jdbc.starter.mapper.CompositionOfPizzaMapper;

public class CompositionOfPizzaMapperImpl implements CompositionOfPizzaMapper {

    private static CompositionOfPizzaMapperImpl instance;
    private CompositionOfPizzaMapperImpl() {
    }

    public static synchronized CompositionOfPizzaMapperImpl getInstance() {
        if (instance == null) {
            instance = new CompositionOfPizzaMapperImpl();
        }
        return instance;
    }


    @Override
    public CompositionOfPizzaEntity toEntity(CompositionOfPizzaDto dto) {
        return CompositionOfPizzaEntity
                .builder()
                .id(dto.getId())
                .pizzaEntity(dto.getPizza())
                .listIngredientEntity(dto.getListIngredient())
                .build();
    }

    @Override
    public CompositionOfPizzaDto toDto(CompositionOfPizzaEntity entity) {
        return CompositionOfPizzaDto
                .builder()
                .id(entity.getId())
                .pizza(entity.getPizzaEntity())
                .listIngredient(entity.getListIngredientEntity())
                .build();
    }
}
