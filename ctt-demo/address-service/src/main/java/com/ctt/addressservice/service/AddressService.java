package com.ctt.addressservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctt.addressservice.entity.Address;
import com.ctt.addressservice.repository.AddressRepository;
import com.ctt.addressservice.request.CreateAddressRequest;
import com.ctt.addressservice.response.AddressResponse;

@Service
public class AddressService {

    Logger logger = LoggerFactory.getLogger(AddressService.class);

    @Autowired
    AddressRepository addressRepository;

    public AddressResponse createAddress(CreateAddressRequest createAddressRequest) {
        
        Address address = new Address();
        address.setStreet(createAddressRequest.getStreet());
        address.setCity(createAddressRequest.getCity());

        addressRepository.save(address);

        return new AddressResponse(address);
    }

    public AddressResponse getById (long id) {
        
        logger.info("Id is: " + id);

        Address address = addressRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Address with id: " + id + " not found."));

        return new AddressResponse(address);
    }
}
