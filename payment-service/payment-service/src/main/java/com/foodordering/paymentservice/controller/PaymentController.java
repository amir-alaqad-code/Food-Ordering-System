package com.foodordering.paymentservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @PostMapping("/process")
    public String processPayment(@RequestBody String orderDetails) {
        return "Payment completed successfully for order: " + orderDetails;
    }
}