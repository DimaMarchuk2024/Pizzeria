package com.dima.jdbc.starter.service;

import com.dima.jdbc.starter.dao.implement.PizzaDaoImpl;
import com.dima.jdbc.starter.dao.PizzaDao;
import com.dima.jdbc.starter.dto.PizzaDto;
import com.dima.jdbc.starter.mapper.PizzaMapper;
import com.dima.jdbc.starter.mapper.impl.PizzaMapperImpl;

import java.util.List;

public class PizzaService {
    private static PizzaService instance;
    private final PizzaDao pizzaDao = PizzaDaoImpl.getInstance();
    private final PizzaMapper pizzaMapper = PizzaMapperImpl.getInstance();

    private PizzaService() {
    }

    public static synchronized PizzaService getInstance() {
        if (instance == null) {
            instance = new PizzaService();
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
        return null;
    }

    public void update(PizzaDto pizzaDto) {
    }

    public boolean delete(Long id) {
        return false;
    }
}
