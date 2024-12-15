package com.dima.jdbc.starter.service;

import com.dima.jdbc.starter.dto.TypeOfPizzaDoughDto;

import java.util.List;

public interface TypeOfPizzaDoughService {

    TypeOfPizzaDoughDto findById(Long id);

    List<TypeOfPizzaDoughDto> findAll();

    TypeOfPizzaDoughDto save(TypeOfPizzaDoughDto typeOfPizzaDoughDto);

    void update(TypeOfPizzaDoughDto typeOfPizzaDoughDto);

    boolean delete(Long id);
}
