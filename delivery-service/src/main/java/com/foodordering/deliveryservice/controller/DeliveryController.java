package com.foodordering.deliveryservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    @PostMapping("/assign")
    public String assignDriver(@RequestBody String orderDetails) {
        return "Driver assigned for the order: " + orderDetails;
    }
}