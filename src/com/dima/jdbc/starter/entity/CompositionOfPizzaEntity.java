package com.dima.jdbc.starter.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class CompositionOfPizzaEntity {

    private Long id;
    private PizzaEntity pizzaEntity;
    private List<IngredientEntity> listIngredientEntity;

}
