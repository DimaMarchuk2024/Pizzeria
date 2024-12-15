package com.dima.jdbc.starter.service.impl;

import com.dima.jdbc.starter.dao.CompositionOfPizzaDao;
import com.dima.jdbc.starter.dao.implement.CompositionOfPizzaDaoImpl;
import com.dima.jdbc.starter.dto.CompositionOfPizzaDto;
import com.dima.jdbc.starter.entity.CompositionOfPizzaEntity;
import com.dima.jdbc.starter.entity.IngredientEntity;
import com.dima.jdbc.starter.entity.PizzaEntity;
import com.dima.jdbc.starter.mapper.CompositionOfPizzaMapper;
import com.dima.jdbc.starter.mapper.impl.CompositionOfPizzaMapperImpl;
import com.dima.jdbc.starter.service.CompositionOfPizzaService;


import java.util.ArrayList;
import java.util.List;

public class CompositionOfPizzaServiceImpl implements CompositionOfPizzaService {

    private static CompositionOfPizzaServiceImpl instance;
    private final CompositionOfPizzaDao compositionOfPizzaDao = CompositionOfPizzaDaoImpl.getInstance();
    private final CompositionOfPizzaMapper compositionOfPizzaMapper = CompositionOfPizzaMapperImpl.getInstance();
    public static Long pizzaId;
    public static Long ingredientId;

    public static Long id;
    private CompositionOfPizzaServiceImpl() {
    }

    public static synchronized CompositionOfPizzaServiceImpl getInstance() {
        if (instance == null) {
            instance = new CompositionOfPizzaServiceImpl();
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
        createPizza(compositionOfPizzaDto);

        CompositionOfPizzaEntity compositionOfPizzaEntity = compositionOfPizzaDao.save(compositionOfPizzaMapper
                        .toEntity(compositionOfPizzaDto));
        return compositionOfPizzaMapper.toDto(compositionOfPizzaEntity);
    }

    public void update(CompositionOfPizzaDto compositionOfPizzaDto) {
        createPizza(compositionOfPizzaDto);
        compositionOfPizzaDto.setId(id);

        compositionOfPizzaDao.update(compositionOfPizzaMapper.toEntity(compositionOfPizzaDto));
    }

    private void createPizza(CompositionOfPizzaDto compositionOfPizzaDto) {
        PizzaEntity pizzaEntity = new PizzaEntity();
        pizzaEntity.setId(pizzaId);

        List<IngredientEntity> listIngredientEntity = new ArrayList<>();
        listIngredientEntity.add(new IngredientEntity());
        listIngredientEntity.stream().findAny().orElseThrow().setId(ingredientId);

        compositionOfPizzaDto.setPizza(pizzaEntity);
        compositionOfPizzaDto.setListIngredient(listIngredientEntity);
    }

    public boolean delete(Long id) {
        return compositionOfPizzaDao.delete(id);
    }
}
