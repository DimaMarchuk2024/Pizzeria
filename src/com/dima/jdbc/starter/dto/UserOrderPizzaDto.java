package com.dima.jdbc.starter.dto;

import com.dima.jdbc.starter.entity.UserPizzeriaEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserOrderPizzaDto {

    private Long id;
    private UserPizzeriaEntity userPizzeriaEntity;
    private LocalDateTime dateOrder;
    private BigDecimal finalCostOrder;
}
