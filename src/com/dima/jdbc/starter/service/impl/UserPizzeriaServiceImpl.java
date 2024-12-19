package com.dima.jdbc.starter.service.impl;

import com.dima.jdbc.starter.dao.CompositionOfPizzaDao;
import com.dima.jdbc.starter.dao.UserPizzeriaDao;
import com.dima.jdbc.starter.dao.implement.CompositionOfPizzaDaoImpl;
import com.dima.jdbc.starter.dao.implement.UserPizzeriaDaoImpl;
import com.dima.jdbc.starter.dto.UserPizzeriaDto;
import com.dima.jdbc.starter.entity.UserPizzeriaEntity;
import com.dima.jdbc.starter.exception.ValidationException;
import com.dima.jdbc.starter.mapper.CompositionOfPizzaMapper;
import com.dima.jdbc.starter.mapper.UserPizzeriaMapper;
import com.dima.jdbc.starter.mapper.impl.CompositionOfPizzaMapperImpl;
import com.dima.jdbc.starter.mapper.impl.UserPizzeriaMapperImpl;
import com.dima.jdbc.starter.service.UserPizzeriaService;
import com.dima.jdbc.starter.validator.UserPizzeriaValidator;
import com.dima.jdbc.starter.validator.ValidationResult;
import com.dima.jdbc.starter.validator.Validator;

import java.util.List;
import java.util.Optional;

public class UserPizzeriaServiceImpl implements UserPizzeriaService {
    private static UserPizzeriaServiceImpl instance;

    private final Validator<UserPizzeriaDto> userPizzeriaValidator = UserPizzeriaValidator.getInstance();
    private final UserPizzeriaDao userPizzeriaDao = UserPizzeriaDaoImpl.getInstance();
    private final UserPizzeriaMapper userPizzeriaMapper = UserPizzeriaMapperImpl.getInstance();

    private UserPizzeriaServiceImpl() {
    }

    public static synchronized UserPizzeriaServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserPizzeriaServiceImpl();
        }
        return instance;
    }

    public Optional<UserPizzeriaDto> login(String phoneNumber, String password) {
        return userPizzeriaDao.findByPhoneNumberAndPassword(phoneNumber, password)
                .map(userPizzeriaMapper::toDto);
//                .orElseThrow(() -> new RuntimeException("Can not find user pizzeria by phone number and password"));
    }

    @Override
    public UserPizzeriaDto save(UserPizzeriaDto userPizzeriaDto) {
        ValidationResult validationResult = userPizzeriaValidator.isValid(userPizzeriaDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        UserPizzeriaEntity userPizzeriaEntity = userPizzeriaMapper.toEntity(userPizzeriaDto);
        userPizzeriaDao.save(userPizzeriaEntity);
        return userPizzeriaDto;
    }

    @Override
    public UserPizzeriaDto findById(Long id) {
        return null;
    }

    @Override
    public List<UserPizzeriaDto> findAll() {
        return null;
    }

    @Override
    public void update(UserPizzeriaDto userPizzeriaDto) {

    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
