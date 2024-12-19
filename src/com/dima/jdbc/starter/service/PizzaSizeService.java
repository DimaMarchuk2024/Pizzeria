package com.dima.jdbc.starter.service;

import com.dima.jdbc.starter.dto.PizzaSizeDto;

import java.util.List;

public interface PizzaSizeService {

    PizzaSizeDto findById(Long id);

    List<PizzaSizeDto> findAll();

    PizzaSizeDto save(PizzaSizeDto pizzaSizeDto);

    void update(PizzaSizeDto pizzaSizeDto);

    boolean delete(Long id);
}
