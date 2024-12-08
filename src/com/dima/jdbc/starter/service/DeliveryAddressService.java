package com.dima.jdbc.starter.service;

import com.dima.jdbc.starter.dto.DeliveryAddressDto;

import java.util.List;

public interface DeliveryAddressService {

    List<DeliveryAddressDto> findAll();

    DeliveryAddressDto findById(Long id);

    DeliveryAddressDto save(DeliveryAddressDto deliveryAddressDto);

    void update(DeliveryAddressDto deliveryAddressDto);

    boolean delete(Long id);
}
