package com.dima.jdbc.starter.service.impl;

import com.dima.jdbc.starter.dao.DeliveryAddressDao;
import com.dima.jdbc.starter.dao.implement.DeliveryAddressDaoImpl;
import com.dima.jdbc.starter.dto.CompositionOfPizzaDto;
import com.dima.jdbc.starter.dto.DeliveryAddressDto;
import com.dima.jdbc.starter.mapper.DeliveryAddressMapper;
import com.dima.jdbc.starter.mapper.impl.DeliveryAddressMapperImpl;
import com.dima.jdbc.starter.service.DeliveryAddressService;

import java.util.List;

public class DeliveryAddressServiceImpl implements DeliveryAddressService {

    private static DeliveryAddressServiceImpl instance;
    private final DeliveryAddressDao deliveryAddressDao = DeliveryAddressDaoImpl.getInstance();
    private final DeliveryAddressMapper deliveryAddressMapper = DeliveryAddressMapperImpl.getInstance();

    private DeliveryAddressServiceImpl() {
    }

    public static synchronized DeliveryAddressServiceImpl getInstance() {
        if (instance == null) {
            instance = new DeliveryAddressServiceImpl();
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


    public DeliveryAddressDto save(DeliveryAddressDto deliveryAddressDto) {
        return null;
    }


    public void update(DeliveryAddressDto deliveryAddressDto) {

    }

    public boolean delete(Long id) {
        return false;
    }
}
