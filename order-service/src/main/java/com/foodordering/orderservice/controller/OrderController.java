package com.foodordering.orderservice.controller;

import com.foodordering.orderservice.service.PaymentClient;
import com.foodordering.orderservice.service.RestaurantClient;
import com.foodordering.orderservice.service.DeliveryClient;
import com.foodordering.orderservice.service.NotificationClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final PaymentClient paymentClient;
    private final RestaurantClient restaurantClient;
    private final DeliveryClient deliveryClient;
    private final NotificationClient notificationClient;

    public OrderController(
            PaymentClient paymentClient,
            RestaurantClient restaurantClient,
            DeliveryClient deliveryClient,
            NotificationClient notificationClient) {
        this.paymentClient = paymentClient;
        this.restaurantClient = restaurantClient;
        this.deliveryClient = deliveryClient;
        this.notificationClient = notificationClient;
    }

    @PostMapping("/create")
    public String createOrder(@RequestBody String orderDetails) {
        String paymentResponse = paymentClient.callPaymentService(orderDetails);
        String restaurantResponse = restaurantClient.callRestaurantService(orderDetails);
        String deliveryResponse = deliveryClient.callDeliveryService(orderDetails);
        String notificationResponse = notificationClient.callNotificationService(
                "Your order has been confirmed: " + orderDetails
        );

        return "Order created successfully.\n"
                + paymentResponse + "\n"
                + restaurantResponse + "\n"
                + deliveryResponse + "\n"
                + notificationResponse;
    }
}