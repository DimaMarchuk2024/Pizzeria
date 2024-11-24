package com.dima.jdbc.starter.mapper;

public interface RootMapper <E,D> {

    E toEntity (D dto);

    D toDto (E entity);
}
