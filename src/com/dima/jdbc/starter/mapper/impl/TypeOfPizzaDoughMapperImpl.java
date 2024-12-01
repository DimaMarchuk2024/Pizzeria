package com.dima.jdbc.starter.mapper.impl;

import com.dima.jdbc.starter.dto.TypeOfPizzaDoughDto;
import com.dima.jdbc.starter.entity.TypeOfPizzaDoughEntity;
import com.dima.jdbc.starter.mapper.TypeOfPizzaDoughMapper;

public class TypeOfPizzaDoughMapperImpl implements TypeOfPizzaDoughMapper {

    private static TypeOfPizzaDoughMapperImpl instance;
    private TypeOfPizzaDoughMapperImpl() {
    }

    public static synchronized TypeOfPizzaDoughMapperImpl getInstance() {
        if (instance == null) {
            instance = new TypeOfPizzaDoughMapperImpl();
        }
        return instance;
    }
    @Override
    public TypeOfPizzaDoughEntity toEntity(TypeOfPizzaDoughDto dto) {
        return TypeOfPizzaDoughEntity
                .builder()
                .id(dto.getId())
                .typeDough(dto.getTypeDough())
                .build();
    }

    @Override
    public TypeOfPizzaDoughDto toDto(TypeOfPizzaDoughEntity entity) {
        return TypeOfPizzaDoughDto
                .builder()
                .id(entity.getId())
                .typeDough(entity.getTypeDough())
                .build();
    }
}
