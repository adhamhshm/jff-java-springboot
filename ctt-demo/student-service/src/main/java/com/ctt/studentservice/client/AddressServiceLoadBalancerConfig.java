// with the api gateway, no need use of this load balancer for feign client

//package com.ctt.studentservice.client;
//
//import feign.Feign;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
//import org.springframework.context.annotation.Bean;
//
//@LoadBalancerClient(value = "address-service")
//public class AddressServiceLoadBalancerConfig {
//
//    @LoadBalanced
//    @Bean
//    public Feign.Builder feignBuilder () {
//        return Feign.builder();
//    }
//}
