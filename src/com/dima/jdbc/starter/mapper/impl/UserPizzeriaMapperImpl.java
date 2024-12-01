package com.dima.jdbc.starter.mapper.impl;

import com.dima.jdbc.starter.dto.UserPizzeriaDto;
import com.dima.jdbc.starter.entity.UserPizzeriaEntity;
import com.dima.jdbc.starter.mapper.UserPizzeriaMapper;

public class UserPizzeriaMapperImpl implements UserPizzeriaMapper {

    private static UserPizzeriaMapperImpl instance;
    private UserPizzeriaMapperImpl() {
    }

    public static synchronized UserPizzeriaMapperImpl getInstance() {
        if (instance == null) {
            instance = new UserPizzeriaMapperImpl();
        }
        return instance;
    }
    @Override
    public UserPizzeriaEntity toEntity(UserPizzeriaDto dto) {
        return UserPizzeriaEntity
                .builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .phoneNumber(dto.getPhoneNumber())
                .email(dto.getEmail())
                .roleEntity(dto.getRoleEntity())
                .birthDate(dto.getBirthDate())
                .build();
    }

    @Override
    public UserPizzeriaDto toDto(UserPizzeriaEntity entity) {
        return UserPizzeriaDto
                .builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .phoneNumber(entity.getPhoneNumber())
                .email(entity.getEmail())
                .roleEntity(entity.getRoleEntity())
                .birthDate(entity.getBirthDate())
                .build();
    }
}
