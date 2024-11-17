package com.dima.jdbc.starter.entity;

import lombok.*;

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
    private IngredientEntity ingredientEntity;

}
