package com.foodordering.orderservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public String callNotificationService(String message) {
        String url = "http://host.docker.internal:8084/api/notifications/send";
        return restTemplate.postForObject(url, message, String.class);
    }
}