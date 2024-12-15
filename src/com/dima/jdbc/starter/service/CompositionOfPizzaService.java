package com.dima.jdbc.starter.service;

import com.dima.jdbc.starter.dto.CompositionOfPizzaDto;

import java.util.List;

public interface CompositionOfPizzaService {

    List<CompositionOfPizzaDto> findAll();

    CompositionOfPizzaDto findById(Long id);

    CompositionOfPizzaDto save(CompositionOfPizzaDto compositionOfPizzaDto);

    void update(CompositionOfPizzaDto compositionOfPizzaDto);

    boolean delete(Long id);
}
