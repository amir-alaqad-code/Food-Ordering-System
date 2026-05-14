package com.foodordering.orderservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DeliveryClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public String callDeliveryService(String orderDetails) {
        String url = "http://host.docker.internal:8083/api/deliveries/assign";
        return restTemplate.postForObject(url, orderDetails, String.class);
    }
}