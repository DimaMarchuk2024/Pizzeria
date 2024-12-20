package com.dima.jdbc.starter;

import com.dima.jdbc.starter.dto.PizzaDto;
import com.dima.jdbc.starter.service.PizzaService;

import java.util.List;

public class ServiceRunner {

    public static void main(String[] args) {
        PizzaService pizzaService = PizzaService.getInstance();
        List<PizzaDto> pizzaDtos = pizzaService.findAll();
        for (PizzaDto pizzaDto : pizzaDtos) {
            pizzaDto.getPizzaName();
        }
    }
}
