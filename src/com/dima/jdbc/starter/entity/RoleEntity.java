package com.dima.jdbc.starter.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class RoleEntity {

    private Long id;
    private String roleName;

}
