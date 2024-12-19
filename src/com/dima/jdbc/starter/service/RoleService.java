package com.dima.jdbc.starter.service;

import com.dima.jdbc.starter.dto.PizzaDto;
import com.dima.jdbc.starter.dto.RoleDto;

import java.util.List;

public interface RoleService {

    RoleDto findById(Long id);

    List<RoleDto> findAll();

    RoleDto save(RoleDto roleDto);

    void update(RoleDto roleDto);

    boolean delete(Long id);
}
