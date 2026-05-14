package com.foodordering.orderservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public String callPaymentService(String orderDetails) {
        String url = "http://host.docker.internal:8081/api/payments/process";
        return restTemplate.postForObject(url, orderDetails, String.class);
    }
}