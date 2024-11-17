package com.dima.jdbc.starter.entity;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class UserPizzeriaEntity {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private RoleEntity roleEntity;
    private LocalDate birthDate;


}
