package com.dima.jdbc.starter.mapper.impl;

import com.dima.jdbc.starter.dto.RoleDto;
import com.dima.jdbc.starter.entity.RoleEntity;
import com.dima.jdbc.starter.mapper.RoleMapper;

public class RoleMapperImpl implements RoleMapper {

    private static RoleMapperImpl instance;
    private RoleMapperImpl() {
    }

    public static synchronized RoleMapperImpl getInstance() {
        if (instance == null) {
            instance = new RoleMapperImpl();
        }
        return instance;
    }
    @Override
    public RoleEntity toEntity(RoleDto dto) {
        return RoleEntity
                .builder()
                .id(dto.getId())
                .roleName(dto.getRoleName())
                .build();
    }

    @Override
    public RoleDto toDto(RoleEntity entity) {
        return RoleDto
                .builder()
                .id(entity.getId())
                .roleName(entity.getRoleName())
                .build();
    }
}
