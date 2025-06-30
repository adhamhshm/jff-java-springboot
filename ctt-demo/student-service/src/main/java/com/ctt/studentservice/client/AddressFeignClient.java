package com.ctt.studentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ctt.studentservice.response.AddressResponse;

// @FeignClient(url = "${address.service.url}", value = "address-feign-client", path = "/api/address"), if url is just base url
@FeignClient(url = "${address.service.url}", value = "address-feign-client")
public interface AddressFeignClient {

    @GetMapping("/{addressId}")
    public AddressResponse getAddressById (@PathVariable long addressId);

}
