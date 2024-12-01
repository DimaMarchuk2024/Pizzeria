package com.dima.jdbc.starter.mapper.impl;

import com.dima.jdbc.starter.dto.UserOrderPizzaDto;
import com.dima.jdbc.starter.entity.UserOrderPizzaEntity;
import com.dima.jdbc.starter.entity.UserPizzeriaEntity;
import com.dima.jdbc.starter.mapper.UserOrderPizzaMapper;

public class UserOrderPizzaMapperImpl implements UserOrderPizzaMapper {

    private static UserOrderPizzaMapperImpl instance;
    private UserOrderPizzaMapperImpl() {
    }

    public static synchronized UserOrderPizzaMapperImpl getInstance() {
        if (instance == null) {
            instance = new UserOrderPizzaMapperImpl();
        }
        return instance;
    }

    @Override
    public UserOrderPizzaEntity toEntity(UserOrderPizzaDto dto) {
        return UserOrderPizzaEntity
                .builder()
                .id(dto.getId())
                .userPizzeriaEntity(dto.getUserPizzeriaEntity())
                .dateOrder(dto.getDateOrder())
                .finalCostOrder(dto.getFinalCostOrder())
                .build();
    }

    @Override
    public UserOrderPizzaDto toDto(UserOrderPizzaEntity entity) {
        return UserOrderPizzaDto
                .builder()
                .id(entity.getId())
                .userPizzeriaEntity(entity.getUserPizzeriaEntity())
                .dateOrder(entity.getDateOrder())
                .finalCostOrder(entity.getFinalCostOrder())
                .build();
    }
}
