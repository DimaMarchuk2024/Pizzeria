package com.dima.jdbc.starter.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class DeliveryAddressEntity {

    private Long id;
    private UserPizzeriaEntity userPizzeriaEntity;
    private String deliveryAddressName;

}
