package com.ctt.studentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ctt.studentservice.response.AddressResponse;

// this class should be universal, but as of now, the name is kept the same
@FeignClient(value = "api-gateway") // with eureka server, here base path is defined, else define in each request
public interface AddressFeignClient {

    @GetMapping("address-service/api/address/{addressId}")
    public AddressResponse getAddressById (@PathVariable long addressId);

}


// ---------------------------- Feign client within the services without api gateway ----------------------------
//@FeignClient(url = "${address.service.url}", value = "address-feign-client", path = "/api/address"), if url is just base url
//FeignClient(url = "${address.service.url}", value = "address-feign-client") // before using eureka server
//@FeignClient(value = "address-service", path = "${address.api.base-path}") // with eureka server, here base path is defined, else define in each request
//public interface AddressFeignClient {
//
//    // @GetMapping("${address.api.base-path}/{addressId}"), if not defined on top
//    @GetMapping("/{addressId}")
//    public AddressResponse getAddressById (@PathVariable long addressId);
//
//}
