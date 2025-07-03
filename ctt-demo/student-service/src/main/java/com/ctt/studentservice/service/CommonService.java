package com.ctt.studentservice.service;

import com.ctt.studentservice.client.AddressFeignClient;
import com.ctt.studentservice.response.AddressResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    Logger logger = LoggerFactory.getLogger(CommonService.class);

    long count = 1;

    @Autowired
    AddressFeignClient addressFeignClient;

    // we call this in other functions
    // need to set up the resilience4j in properties, "addressService" is defined in properties
    @CircuitBreaker(name = "addressService", fallbackMethod = "fallbackGetAddressById")
    public AddressResponse getAddressById (long addressId) {

        logger.info("count: {}", count);
        count++;

        AddressResponse addressResponse = addressFeignClient.getAddressById(addressId);

        return addressResponse;
    }

    public AddressResponse fallbackGetAddressById (long addressId, Throwable th) {

        logger.error("Error: " + th );

        return new AddressResponse();
    }
}
