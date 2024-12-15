package com.dima.jdbc.starter.mapper.impl;

import com.dima.jdbc.starter.dao.RoleDao;
import com.dima.jdbc.starter.dao.implement.RoleDaoImpl;
import com.dima.jdbc.starter.dto.UserPizzeriaDto;
import com.dima.jdbc.starter.entity.RoleEntity;
import com.dima.jdbc.starter.entity.UserPizzeriaEntity;
import com.dima.jdbc.starter.mapper.UserPizzeriaMapper;
import com.dima.jdbc.starter.util.LocalDateFormatter;

import java.time.LocalDate;
import java.util.stream.Stream;

public class UserPizzeriaMapperImpl implements UserPizzeriaMapper {

    RoleDao roleDao = RoleDaoImpl.getInstance();

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
        RoleEntity roleEntity = roleDao.findAll()
                .stream()
                .filter(role -> dto.getRoleName().equals(role.getRoleName()))
                .findAny()
                .orElseThrow();

        return UserPizzeriaEntity
                .builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .phoneNumber(dto.getPhoneNumber())
                .email(dto.getEmail())
                .roleEntity(roleEntity)
                .birthDate(LocalDateFormatter.format(dto.getBirthDate()))
                .password(dto.getPassword())
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
                .roleName(entity.getRoleEntity().getRoleName())
                .birthDate(String.valueOf(entity.getBirthDate()))
                .password(entity.getPassword())
                .build();
    }
}
