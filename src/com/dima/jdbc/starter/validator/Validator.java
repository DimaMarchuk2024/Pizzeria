package com.dima.jdbc.starter.validator;

public interface Validator<T>{

    ValidationResult isValid(T object);
}
