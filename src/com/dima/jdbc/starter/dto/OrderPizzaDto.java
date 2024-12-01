package com.dima.jdbc.starter.dto;

import com.dima.jdbc.starter.entity.PizzaEntity;
import com.dima.jdbc.starter.entity.PizzaSizeEntity;
import com.dima.jdbc.starter.entity.TypeOfPizzaDoughEntity;
import com.dima.jdbc.starter.entity.UserOrderPizzaEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderPizzaDto {

    private Long id;
    private List<PizzaEntity> listPizzaEntity;
    private PizzaSizeEntity pizzaSizeEntity;
    private TypeOfPizzaDoughEntity typeOfPizzaDoughEntity;
    private Integer numberOfPizza;
    private BigDecimal sumCostAddedIngredient;
    private BigDecimal sumCostRemovedIngredient;
    private BigDecimal costOfPizza;
    private UserOrderPizzaEntity userOrderPizzaEntity;
}
