package com.dima.jdbc.starter.validator;

import com.dima.jdbc.starter.dto.UserPizzeriaDto;
import com.dima.jdbc.starter.service.impl.UserPizzeriaServiceImpl;

public class UserPizzeriaValidator implements Validator<UserPizzeriaDto>{

    private static UserPizzeriaValidator instance;
    private UserPizzeriaValidator() {
    }

    @Override
    public ValidationResult isValid(UserPizzeriaDto userPizzeriaDto) {
        ValidationResult validationResult = new ValidationResult();

        if (userPizzeriaDto.getPhoneNumber() == null) {
            validationResult.add(Error.of("invalid.phonenumber", "Phone number is invalid"));
        }
        return validationResult;
    }

    public static synchronized UserPizzeriaValidator getInstance() {
        if (instance == null) {
            instance = new UserPizzeriaValidator();
        }
        return instance;
    }
}
