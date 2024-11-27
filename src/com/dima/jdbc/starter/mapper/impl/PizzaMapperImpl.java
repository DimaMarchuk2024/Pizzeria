package com.dima.jdbc.starter.mapper.impl;

import com.dima.jdbc.starter.dto.PizzaDto;
import com.dima.jdbc.starter.entity.PizzaEntity;
import com.dima.jdbc.starter.mapper.PizzaMapper;

public class PizzaMapperImpl implements PizzaMapper {

    private static PizzaMapperImpl instance;
    private PizzaMapperImpl() {
    }

    public static synchronized PizzaMapperImpl getInstance() {
        if (instance == null) {
            instance = new PizzaMapperImpl();
        }
        return instance;
    }

    @Override
    public PizzaEntity toEntity(PizzaDto dto) {
        return PizzaEntity
                .builder()
                .id(dto.getId())
                .pizzaName(dto.getPizzaName())
                .build();
    }

    @Override
    public PizzaDto toDto(PizzaEntity entity) {
        return PizzaDto
                .builder()
                .id(entity.getId())
                .pizzaName(entity.getPizzaName())
                .build();
    }
}
