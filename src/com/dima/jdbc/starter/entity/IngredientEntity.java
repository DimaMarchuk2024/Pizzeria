package com.dima.jdbc.starter.entity;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class IngredientEntity {

    private Long id;
    private String ingredientName;
    private BigDecimal costOfIngredient;
}
