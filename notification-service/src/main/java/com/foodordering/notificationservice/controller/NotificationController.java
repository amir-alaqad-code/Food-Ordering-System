package com.foodordering.notificationservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @PostMapping("/send")
    public String sendNotification(@RequestBody String message) {
        return "Notification sent to customer: " + message;
    }
}