package com.foodordering.orderservice.controller;

import com.foodordering.orderservice.service.PaymentClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final PaymentClient paymentClient;

    public OrderController(PaymentClient paymentClient) {
        this.paymentClient = paymentClient;
    }

    @PostMapping("/create")
    public String createOrder(@RequestBody String orderDetails) {
        String paymentResponse = paymentClient.callPaymentService(orderDetails);
        return "Order created successfully. " + paymentResponse;
    }
}