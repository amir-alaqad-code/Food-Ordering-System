package com.foodordering.restaurantservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @PostMapping("/accept")
    public String acceptOrder(@RequestBody String orderDetails) {
        return "Restaurant accepted the order: " + orderDetails;
    }
}