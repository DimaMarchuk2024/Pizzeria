package com.dima.jdbc.starter.mapper.impl;

import com.dima.jdbc.starter.dto.OrderPizzaDto;
import com.dima.jdbc.starter.entity.OrderPizzaEntity;
import com.dima.jdbc.starter.mapper.OrderPizzaMapper;

public class OrderPizzaMapperImpl implements OrderPizzaMapper {

    private static OrderPizzaMapperImpl instance;
    private OrderPizzaMapperImpl() {
    }

    public static synchronized OrderPizzaMapperImpl getInstance() {
        if (instance == null) {
            instance = new OrderPizzaMapperImpl();
        }
        return instance;
    }
    @Override
    public OrderPizzaEntity toEntity(OrderPizzaDto dto) {
        return OrderPizzaEntity
                .builder()
                .id(dto.getId())
                .listPizzaEntity(dto.getListPizzaEntity())
                .pizzaSizeEntity(dto.getPizzaSizeEntity())
                .typeOfPizzaDoughEntity(dto.getTypeOfPizzaDoughEntity())
                .numberOfPizza(dto.getNumberOfPizza())
                .sumCostAddedIngredient(dto.getSumCostAddedIngredient())
                .sumCostRemovedIngredient(dto.getSumCostRemovedIngredient())
                .costOfPizza(dto.getCostOfPizza())
                .userOrderPizzaEntity(dto.getUserOrderPizzaEntity())
                .build();
    }

    @Override
    public OrderPizzaDto toDto(OrderPizzaEntity entity) {
        return OrderPizzaDto
                .builder()
                .id(entity.getId())
                .listPizzaEntity(entity.getListPizzaEntity())
                .pizzaSizeEntity(entity.getPizzaSizeEntity())
                .typeOfPizzaDoughEntity(entity.getTypeOfPizzaDoughEntity())
                .numberOfPizza(entity.getNumberOfPizza())
                .sumCostAddedIngredient(entity.getSumCostAddedIngredient())
                .sumCostRemovedIngredient(entity.getSumCostRemovedIngredient())
                .costOfPizza(entity.getCostOfPizza())
                .userOrderPizzaEntity(entity.getUserOrderPizzaEntity())
                .build();
    }
}
