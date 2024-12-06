package com.dima.jdbc.starter.mapper.impl;

import com.dima.jdbc.starter.dto.IngredientDto;
import com.dima.jdbc.starter.entity.IngredientEntity;
import com.dima.jdbc.starter.mapper.IngredientMapper;

public class IngredientMapperImpl implements IngredientMapper {

    private static IngredientMapperImpl instance;
    private IngredientMapperImpl() {
    }

    public static synchronized IngredientMapperImpl getInstance() {
        if (instance == null) {
            instance = new IngredientMapperImpl();
        }
        return instance;
    }
    @Override
    public IngredientEntity toEntity(IngredientDto dto) {
        return IngredientEntity
                .builder()
                .id(dto.getId())
                .ingredientName(dto.getIngredientName())
                .costOfIngredient(dto.getCostOfIngredient())
                .build();
    }

    @Override
    public IngredientDto toDto(IngredientEntity entity) {
        return IngredientDto
                .builder()
                .id(entity.getId())
                .ingredientName(entity.getIngredientName())
                .costOfIngredient(entity.getCostOfIngredient())
                .build();
    }
}
