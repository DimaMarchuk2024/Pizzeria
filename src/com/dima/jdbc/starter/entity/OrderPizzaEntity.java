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
public class OrderPizzaEntity {

    private Long id;
    private PizzaEntity pizzaEntity;
    private PizzaSizeEntity pizzaSizeEntity;
    private TypeOfPizzaDoughEntity typeOfPizzaDoughEntity;
    private Integer numberOfPizza;
    private BigDecimal sumCostAddedIngredient;
    private BigDecimal sumCostRemovedIngredient;
    private BigDecimal costOfPizza;
    private UserOrderPizzaEntity userOrderPizzaEntity;
}
