package com.dima.jdbc.starter.service.impl;

import com.dima.jdbc.starter.dao.PizzaSizeDao;
import com.dima.jdbc.starter.dao.TypeOfPizzaDoughDao;
import com.dima.jdbc.starter.dao.implement.PizzaSizeDaoImpl;
import com.dima.jdbc.starter.dao.implement.TypeOfPizzaDoughDaoImpl;
import com.dima.jdbc.starter.dto.TypeOfPizzaDoughDto;
import com.dima.jdbc.starter.mapper.PizzaSizeMapper;
import com.dima.jdbc.starter.mapper.TypeOfPizzaDoughMapper;
import com.dima.jdbc.starter.mapper.impl.PizzaSizeMapperImpl;
import com.dima.jdbc.starter.mapper.impl.TypeOfPizzaDoughMapperImpl;
import com.dima.jdbc.starter.service.TypeOfPizzaDoughService;

import java.util.List;

public class TypeOfPizzaDoughServiceImpl implements TypeOfPizzaDoughService {

    private static TypeOfPizzaDoughServiceImpl instance;
    private final TypeOfPizzaDoughDao typeOfPizzaDoughDao = TypeOfPizzaDoughDaoImpl.getInstance();
    private final TypeOfPizzaDoughMapper typeOfPizzaDoughMapper = TypeOfPizzaDoughMapperImpl.getInstance();

    private TypeOfPizzaDoughServiceImpl() {
    }

    public static synchronized TypeOfPizzaDoughServiceImpl getInstance() {
        if (instance == null) {
            instance = new TypeOfPizzaDoughServiceImpl();
        }
        return instance;
    }
    @Override
    public TypeOfPizzaDoughDto findById(Long id) {
        return typeOfPizzaDoughDao.findById(id)
                .map(typeOfPizzaDoughMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Can not find type of pizza dough by id"));
    }

    @Override
    public List<TypeOfPizzaDoughDto> findAll() {
        return typeOfPizzaDoughDao.findAll()
                .stream()
                .map(typeOfPizzaDoughMapper::toDto)
                .toList();
    }

    @Override
    public TypeOfPizzaDoughDto save(TypeOfPizzaDoughDto typeOfPizzaDoughDto) {
        return null;
    }

    @Override
    public void update(TypeOfPizzaDoughDto typeOfPizzaDoughDto) {

    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
