package com.ctt.addressservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctt.addressservice.request.CreateAddressRequest;
import com.ctt.addressservice.response.AddressResponse;
import com.ctt.addressservice.service.AddressService;

@RestController
@RequestMapping("/api/address")
@RefreshScope
public class AddressController {

    @Autowired
    private Environment env;

    @Autowired
    AddressService addressService;

    @Value("${address.test}")
    private String testValue;

    @GetMapping(path="/{addressId}")
    public AddressResponse getAddress (@PathVariable long addressId) {
        return addressService.getAddressById(addressId);
    }

    @PostMapping(path="/create", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public AddressResponse createAddress (@RequestBody CreateAddressRequest createAddressRequest) {
        return addressService.createAddress(createAddressRequest);
    }

    // changing in config server will reflect instantly, but for microservices, need to restart manually for properties changes
    // the workaround, with actuator, need to add "refresh" endpoints in bootstrap.properties, add @RefreshScope in class
    // use POST http://localhost:[port]/actuator/refresh
    @GetMapping("/test")
    public String test () {
        return testValue;
    }
}
