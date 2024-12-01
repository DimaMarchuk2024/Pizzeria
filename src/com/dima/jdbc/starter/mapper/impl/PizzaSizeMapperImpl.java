package com.dima.jdbc.starter.mapper.impl;

import com.dima.jdbc.starter.dto.PizzaSizeDto;
import com.dima.jdbc.starter.entity.PizzaSizeEntity;
import com.dima.jdbc.starter.mapper.PizzaSizeMapper;

public class PizzaSizeMapperImpl implements PizzaSizeMapper {

    private static PizzaSizeMapperImpl instance;
    private PizzaSizeMapperImpl() {
    }

    public static synchronized PizzaSizeMapperImpl getInstance() {
        if (instance == null) {
            instance = new PizzaSizeMapperImpl();
        }
        return instance;
    }
    @Override
    public PizzaSizeEntity toEntity(PizzaSizeDto dto) {
        return PizzaSizeEntity
                .builder()
                .id(dto.getId())
                .size(dto.getSize())
                .build();
    }

    @Override
    public PizzaSizeDto toDto(PizzaSizeEntity entity) {
        return PizzaSizeDto
                .builder()
                .id(entity.getId())
                .size(entity.getSize())
                .build();
    }
}
