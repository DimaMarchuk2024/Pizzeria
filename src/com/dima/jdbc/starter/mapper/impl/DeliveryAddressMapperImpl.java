package com.dima.jdbc.starter.mapper.impl;

import com.dima.jdbc.starter.dto.DeliveryAddressDto;
import com.dima.jdbc.starter.entity.DeliveryAddressEntity;
import com.dima.jdbc.starter.mapper.DeliveryAddressMapper;

public class DeliveryAddressMapperImpl implements DeliveryAddressMapper {

    private static DeliveryAddressMapperImpl instance;
    private DeliveryAddressMapperImpl() {
    }

    public static synchronized DeliveryAddressMapperImpl getInstance() {
        if (instance == null) {
            instance = new DeliveryAddressMapperImpl();
        }
        return instance;
    }
    @Override
    public DeliveryAddressEntity toEntity(DeliveryAddressDto dto) {
        return DeliveryAddressEntity
                .builder()
                .id(dto.getId())
                .userPizzeriaEntity(dto.getUserPizzeriaEntity())
                .deliveryAddressName(dto.getDeliveryAddressName())
                .build();

    }

    @Override
    public DeliveryAddressDto toDto(DeliveryAddressEntity entity) {
        return DeliveryAddressDto
                .builder()
                .id(entity.getId())
                .userPizzeriaEntity(entity.getUserPizzeriaEntity())
                .deliveryAddressName(entity.getDeliveryAddressName())
                .build();
    }
}
