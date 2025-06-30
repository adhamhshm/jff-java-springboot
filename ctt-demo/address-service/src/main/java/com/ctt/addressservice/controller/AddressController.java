package com.ctt.addressservice.controller;

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
    AddressService addressService;

    @GetMapping(path="/{addressId}")
    public AddressResponse getAddress (@PathVariable long addressId) {
        return addressService.getAddressById(addressId);
    }

    @PostMapping(path="/create", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public AddressResponse createAddress (@RequestBody CreateAddressRequest createAddressRequest) {
        return addressService.createAddress(createAddressRequest);
    }
}
