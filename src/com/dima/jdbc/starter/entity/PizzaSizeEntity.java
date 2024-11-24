package com.dima.jdbc.starter.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class PizzaSizeEntity {

    private Long id;
    private String size;

}
