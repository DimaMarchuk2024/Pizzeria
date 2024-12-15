package com.dima.jdbc.starter.validator;

import com.dima.jdbc.starter.dto.UserPizzeriaDto;
import com.dima.jdbc.starter.service.impl.UserPizzeriaServiceImpl;
import com.dima.jdbc.starter.util.LocalDateFormatter;

public class UserPizzeriaValidator implements Validator<UserPizzeriaDto>{

    private static UserPizzeriaValidator instance;
    private UserPizzeriaValidator() {
    }

    @Override
    public ValidationResult isValid(UserPizzeriaDto userPizzeriaDto) {
        ValidationResult validationResult = new ValidationResult();
        if (userPizzeriaDto.getFirstName() == null) {
            validationResult.add(Error.of("invalid.firstname", "Firstname is invalid"));
        }
        if (userPizzeriaDto.getLastName() == null) {
            validationResult.add(Error.of("invalid.lastname", "Lastname is invalid"));
        }
        if (userPizzeriaDto.getPhoneNumber() == null) {
            validationResult.add(Error.of("invalid.phoneNumber", "Phone number is invalid"));
        }
        if (userPizzeriaDto.getEmail() == null) {
            validationResult.add(Error.of("invalid.email", "Email is invalid"));
        }
        if (!LocalDateFormatter.isValid(userPizzeriaDto.getBirthDate())) {
            validationResult.add(Error.of("invalid.birthdate", "Birthdate is invalid"));
        }
        if (userPizzeriaDto.getPassword() == null) {
            validationResult.add(Error.of("invalid.password", "Password is invalid"));
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
