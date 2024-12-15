package com.dima.jdbc.starter.dao;

import com.dima.jdbc.starter.entity.UserPizzeriaEntity;

import java.util.Optional;

public interface UserPizzeriaDao extends RootDao<Long, UserPizzeriaEntity>{

    Optional<UserPizzeriaEntity> findByPhoneNumberAndPassword(String phoneNumber, String password);
}
