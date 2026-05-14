package com.foodordering.orderservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestaurantClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public String callRestaurantService(String orderDetails) {
        String url = "http://host.docker.internal:8082/api/restaurants/accept";
        return restTemplate.postForObject(url, orderDetails, String.class);
    }
}