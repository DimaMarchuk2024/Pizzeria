package com.dima.jdbc.starter.service.impl;

import com.dima.jdbc.starter.dao.PizzaSizeDao;
import com.dima.jdbc.starter.dao.RoleDao;
import com.dima.jdbc.starter.dao.implement.PizzaSizeDaoImpl;
import com.dima.jdbc.starter.dao.implement.RoleDaoImpl;
import com.dima.jdbc.starter.dto.PizzaSizeDto;
import com.dima.jdbc.starter.mapper.PizzaSizeMapper;
import com.dima.jdbc.starter.mapper.RoleMapper;
import com.dima.jdbc.starter.mapper.impl.PizzaSizeMapperImpl;
import com.dima.jdbc.starter.mapper.impl.RoleMapperImpl;
import com.dima.jdbc.starter.service.PizzaSizeService;

import java.util.List;

public class PizzaSizeServiceImpl implements PizzaSizeService {

    private static PizzaSizeServiceImpl instance;
    private final PizzaSizeDao pizzaSizeDao = PizzaSizeDaoImpl.getInstance();
    private final PizzaSizeMapper pizzaSizeMapper = PizzaSizeMapperImpl.getInstance();

    private PizzaSizeServiceImpl() {
    }

    public static synchronized PizzaSizeServiceImpl getInstance() {
        if (instance == null) {
            instance = new PizzaSizeServiceImpl();
        }
        return instance;
    }
    @Override
    public PizzaSizeDto findById(Long id) {
        return pizzaSizeDao.findById(id)
                .map(pizzaSizeMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Can not find pizza size by id"));
    }

    @Override
    public List<PizzaSizeDto> findAll() {
        return pizzaSizeDao.findAll()
                .stream()
                .map(pizzaSizeMapper::toDto)
                .toList();
    }

    @Override
    public PizzaSizeDto save(PizzaSizeDto pizzaSizeDto) {
        return null;
    }

    @Override
    public void update(PizzaSizeDto pizzaSizeDto) {

    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
