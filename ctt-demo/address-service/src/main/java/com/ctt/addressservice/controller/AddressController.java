package com.ctt.addressservice.controller;

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
public class AddressController {

    @Autowired
    private Environment env;

    @Autowired
    AddressService addressService;

    @GetMapping(path="/{addressId}")
    public AddressResponse getAddress (@PathVariable long addressId) {
        return addressService.getAddressById(addressId);
    }

    @PostMapping(path="/create", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public AddressResponse createAddress (@RequestBody CreateAddressRequest createAddressRequest) {
        return addressService.createAddress(createAddressRequest);
    }

    @GetMapping("/status")
    public String status() {
        return "Get status API users is working on port " + env.getProperty("local.server.port");
    }
}
