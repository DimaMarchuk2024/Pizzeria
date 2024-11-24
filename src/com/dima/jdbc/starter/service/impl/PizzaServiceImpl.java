package com.dima.jdbc.starter.service.impl;

import com.dima.jdbc.starter.dao.implement.PizzaDaoImpl;
import com.dima.jdbc.starter.dao.PizzaDao;
import com.dima.jdbc.starter.dto.PizzaDto;
import com.dima.jdbc.starter.mapper.PizzaMapper;
import com.dima.jdbc.starter.mapper.impl.PizzaMapperImpl;
import com.dima.jdbc.starter.service.PizzaService;

import java.util.List;

public class PizzaServiceImpl implements PizzaService {
    private static PizzaServiceImpl instance;
    private final PizzaDao pizzaDao = PizzaDaoImpl.getInstance();
    private final PizzaMapper pizzaMapper = PizzaMapperImpl.getInstance();

    private PizzaServiceImpl() {
    }

    public static synchronized PizzaServiceImpl getInstance() {
        if (instance == null) {
            instance = new PizzaServiceImpl();
        }
        return instance;
    }

    @Override
    public PizzaDto findById(Long id) {
        return pizzaDao.findById(id)
                .map(pizzaMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Can not find pizza by id"));
    }

    @Override
    public List<PizzaDto> findAll() {
        return pizzaDao.findAll()
                .stream()
                .map(pizzaMapper::toDto)
                .toList();
    }

    @Override
    public PizzaDto save(PizzaDto pizzaDto) {
        return null;
    }

    @Override
    public void update(PizzaDto pizzaDto) {

    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
