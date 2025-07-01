package com.ctt.studentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ctt.studentservice.response.AddressResponse;

// @FeignClient(url = "${address.service.url}", value = "address-feign-client", path = "/api/address"), if url is just base url
// FeignClient(url = "${address.service.url}", value = "address-feign-client") // before using eureka server
@FeignClient(value = "address-service", path = "${address.api.base-path}") // with eureka server, here base path is defined, else define in each request
public interface AddressFeignClient {

    // @GetMapping("${address.api.base-path}/{addressId}"), if not defined on top
    @GetMapping("/{addressId}")
    public AddressResponse getAddressById (@PathVariable long addressId);

}
