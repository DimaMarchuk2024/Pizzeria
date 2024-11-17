package com.dima.jdbc.starter.entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class UserOrderPizzaEntity {

    private Long id;
    private UserPizzeriaEntity userPizzeriaEntity;
    private LocalDateTime dateOrder;
    private BigDecimal finalCostOrder;

}
