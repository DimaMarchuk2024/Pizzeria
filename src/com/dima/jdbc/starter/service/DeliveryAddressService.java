package com.dima.jdbc.starter.service;

import com.dima.jdbc.starter.dao.CompositionOfPizzaDao;
import com.dima.jdbc.starter.dao.DeliveryAddressDao;
import com.dima.jdbc.starter.dao.implement.CompositionOfPizzaDaoImpl;
import com.dima.jdbc.starter.dao.implement.DeliveryAddressDaoImpl;
import com.dima.jdbc.starter.dto.CompositionOfPizzaDto;
import com.dima.jdbc.starter.dto.DeliveryAddressDto;
import com.dima.jdbc.starter.mapper.CompositionOfPizzaMapper;
import com.dima.jdbc.starter.mapper.DeliveryAddressMapper;
import com.dima.jdbc.starter.mapper.impl.CompositionOfPizzaMapperImpl;
import com.dima.jdbc.starter.mapper.impl.DeliveryAddressMapperImpl;

import java.util.List;

public class DeliveryAddressService {

    private static DeliveryAddressService instance;
    private final DeliveryAddressDao deliveryAddressDao = DeliveryAddressDaoImpl.getInstance();
    private final DeliveryAddressMapper deliveryAddressMapper = DeliveryAddressMapperImpl.getInstance();

    private DeliveryAddressService() {
    }

    public static synchronized DeliveryAddressService getInstance() {
        if (instance == null) {
            instance = new DeliveryAddressService();
        }
        return instance;
    }

    public List<DeliveryAddressDto> findAll() {
        return deliveryAddressDao.findAll()
                .stream()
                .map(deliveryAddressMapper::toDto)
                .toList();
    }


    public DeliveryAddressDto findById(Long id) {
        return deliveryAddressDao
                .findById(id)
                .map(deliveryAddressMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Can not find composition of pizza by id"));
    }


    public CompositionOfPizzaDto save(CompositionOfPizzaDto pizzaDto) {
        return null;
    }


    public void update(CompositionOfPizzaDto pizzaDto) {

    }

    public boolean delete(Long id) {
        return false;
    }
}
