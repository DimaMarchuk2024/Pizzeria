package com.dima.jdbc.starter.service.impl;

import com.dima.jdbc.starter.dao.IngredientDao;
import com.dima.jdbc.starter.dao.PizzaDao;
import com.dima.jdbc.starter.dao.implement.IngredientDaoImpl;
import com.dima.jdbc.starter.dao.implement.PizzaDaoImpl;
import com.dima.jdbc.starter.dto.IngredientDto;
import com.dima.jdbc.starter.entity.IngredientEntity;
import com.dima.jdbc.starter.entity.PizzaEntity;
import com.dima.jdbc.starter.mapper.IngredientMapper;
import com.dima.jdbc.starter.mapper.PizzaMapper;
import com.dima.jdbc.starter.mapper.impl.IngredientMapperImpl;
import com.dima.jdbc.starter.mapper.impl.PizzaMapperImpl;
import com.dima.jdbc.starter.service.IngredientService;

import java.util.List;

public class IngredientServiceImpl implements IngredientService {

    private static IngredientServiceImpl instance;
    private final IngredientDao ingredientDao = IngredientDaoImpl.getInstance();
    private final IngredientMapper ingredientMapper = IngredientMapperImpl.getInstance();

    private IngredientServiceImpl() {
    }

    public static synchronized IngredientServiceImpl getInstance() {
        if (instance == null) {
            instance = new IngredientServiceImpl();
        }
        return instance;
    }
    @Override
    public IngredientDto findById(Long id) {
        return ingredientDao.findById(id)
                .map(ingredientMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Can not find ingredient by id"));
    }

    @Override
    public List<IngredientDto> findAll() {
        return ingredientDao.findAll()
                .stream()
                .map(ingredientMapper::toDto)
                .toList();
    }

    @Override
    public IngredientDto save(IngredientDto ingredientDto) {
        IngredientEntity ingredientEntity = ingredientMapper.toEntity(ingredientDto);
        ingredientDao.save(ingredientEntity);
        return ingredientDto;
    }

    @Override
    public void update(IngredientDto ingredientDto) {

    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
