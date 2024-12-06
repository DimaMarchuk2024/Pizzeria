package com.dima.jdbc.starter.dto;

import com.dima.jdbc.starter.entity.UserPizzeriaEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddressDto {

    private Long id;
    private UserPizzeriaEntity userPizzeriaEntity;
    private String deliveryAddressName;
}
