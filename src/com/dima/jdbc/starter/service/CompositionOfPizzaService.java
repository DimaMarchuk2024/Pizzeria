package com.dima.jdbc.starter.service;

import com.dima.jdbc.starter.dao.CompositionOfPizzaDao;
import com.dima.jdbc.starter.dao.implement.CompositionOfPizzaDaoImpl;
import com.dima.jdbc.starter.dto.CompositionOfPizzaDto;
import com.dima.jdbc.starter.entity.CompositionOfPizzaEntity;
import com.dima.jdbc.starter.entity.IngredientEntity;
import com.dima.jdbc.starter.entity.PizzaEntity;
import com.dima.jdbc.starter.mapper.CompositionOfPizzaMapper;
import com.dima.jdbc.starter.mapper.impl.CompositionOfPizzaMapperImpl;


import java.util.ArrayList;
import java.util.List;

public class CompositionOfPizzaService {

    private static CompositionOfPizzaService instance;
    private final CompositionOfPizzaDao compositionOfPizzaDao = CompositionOfPizzaDaoImpl.getInstance();
    private final CompositionOfPizzaMapper compositionOfPizzaMapper = CompositionOfPizzaMapperImpl.getInstance();

    private CompositionOfPizzaService() {
    }

    public static synchronized CompositionOfPizzaService getInstance() {
        if (instance == null) {
            instance = new CompositionOfPizzaService();
        }
        return instance;
    }

    public List<CompositionOfPizzaDto> findAll() {
        return compositionOfPizzaDao.findAll()
                .stream()
                .map(compositionOfPizzaMapper::toDto)
                .toList();
    }


    public CompositionOfPizzaDto findById(Long id) {
        return compositionOfPizzaDao
                .findById(id)
                .map(compositionOfPizzaMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Can not find composition of pizza by id"));
    }

    public CompositionOfPizzaDto save(CompositionOfPizzaDto compositionOfPizzaDto) {
        PizzaEntity pizzaEntity = new PizzaEntity();
        pizzaEntity.setId(8L);

        List<IngredientEntity> listIngredientEntity = new ArrayList<>();
        listIngredientEntity.add(new IngredientEntity());
        listIngredientEntity.stream().findAny().orElseThrow().setId(7L);

        compositionOfPizzaDto.setPizzaEntity(pizzaEntity);
        compositionOfPizzaDto.setListIngredientEntity(listIngredientEntity);

        CompositionOfPizzaEntity compositionOfPizzaEntity = compositionOfPizzaDao.save(compositionOfPizzaMapper.toEntity(compositionOfPizzaDto));
        return compositionOfPizzaMapper.toDto(compositionOfPizzaEntity);
    }

    public void update(CompositionOfPizzaDto compositionOfPizzaDto) {
        PizzaEntity pizzaEntity = new PizzaEntity();
        pizzaEntity.setId(7L);

        List<IngredientEntity> listIngredientEntity = new ArrayList<>();
        listIngredientEntity.add(new IngredientEntity());
        listIngredientEntity.stream().findAny().orElseThrow().setId(6L);

        compositionOfPizzaDto.setPizzaEntity(pizzaEntity);
        compositionOfPizzaDto.setListIngredientEntity(listIngredientEntity);
        compositionOfPizzaDto.setId(47L);

        compositionOfPizzaDao.update(compositionOfPizzaMapper.toEntity(compositionOfPizzaDto));
    }

    public boolean delete(Long id) {
        return compositionOfPizzaDao.delete(id);
    }
}
