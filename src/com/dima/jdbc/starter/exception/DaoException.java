package com.dima.jdbc.starter.exception;

public class DaoException extends RuntimeException{

    public DaoException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
