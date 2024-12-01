package com.dima.jdbc.starter.dto;

import com.dima.jdbc.starter.entity.IngredientEntity;
import com.dima.jdbc.starter.entity.PizzaEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompositionOfPizzaDto {

    private Long id;
    private PizzaEntity pizzaEntity;
    private List<IngredientEntity> listIngredientEntity;
}
