package com.dima.jdbc.starter.service.impl;

import com.dima.jdbc.starter.dao.PizzaDao;
import com.dima.jdbc.starter.dao.RoleDao;
import com.dima.jdbc.starter.dao.implement.PizzaDaoImpl;
import com.dima.jdbc.starter.dao.implement.RoleDaoImpl;
import com.dima.jdbc.starter.dto.PizzaDto;
import com.dima.jdbc.starter.dto.RoleDto;
import com.dima.jdbc.starter.mapper.PizzaMapper;
import com.dima.jdbc.starter.mapper.RoleMapper;
import com.dima.jdbc.starter.mapper.impl.PizzaMapperImpl;
import com.dima.jdbc.starter.mapper.impl.RoleMapperImpl;
import com.dima.jdbc.starter.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    private static RoleServiceImpl instance;
    private final RoleDao roleDao = RoleDaoImpl.getInstance();
    private final RoleMapper roleMapper = RoleMapperImpl.getInstance();

    private RoleServiceImpl() {
    }

    public static synchronized RoleServiceImpl getInstance() {
        if (instance == null) {
            instance = new RoleServiceImpl();
        }
        return instance;
    }

    public RoleDto findById(Long id) {
        return roleDao.findById(id)
                .map(roleMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Can not find role by id"));
    }

    public List<RoleDto> findAll() {
        return roleDao.findAll()
                .stream()
                .map(roleMapper::toDto)
                .toList();
    }

    @Override
    public RoleDto save(RoleDto roleDto) {
        return null;
    }

    @Override
    public void update(RoleDto roleDto) {

    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
