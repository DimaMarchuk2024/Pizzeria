package com.dima.jdbc.starter.service.impl;

import com.dima.jdbc.starter.dao.implement.PizzaDaoImpl;
import com.dima.jdbc.starter.dao.PizzaDao;
import com.dima.jdbc.starter.dto.PizzaDto;
import com.dima.jdbc.starter.entity.PizzaEntity;
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

    public PizzaDto findById(Long id) {
        return pizzaDao.findById(id)
                .map(pizzaMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Can not find pizza by id"));
    }

    public List<PizzaDto> findAll() {
        return pizzaDao.findAll()
                .stream()
                .map(pizzaMapper::toDto)
                .toList();
    }


    public PizzaDto save(PizzaDto pizzaDto) {
        PizzaEntity pizzaEntity = pizzaMapper.toEntity(pizzaDto);
        pizzaDao.save(pizzaEntity);
        return pizzaDto;
    }

    public void update(PizzaDto pizzaDto) {
    }

    public boolean delete(Long id) {
        return false;
    }
}
