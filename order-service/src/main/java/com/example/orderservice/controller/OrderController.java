package com.example.orderservice.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
public class OrderController {

    @GetMapping("/orders")
    public List<Map<String, Object>> getOrders() {
        RestTemplate rest = new RestTemplate();
        List<Map<String, String>> users = rest.exchange(
                "http://localhost:8081/users",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Map<String, String>>>() {
                }
        ).getBody();

        return List.of(
                Map.of("orderId", "1001", "user", users.get(0)),
                Map.of("orderId", "1002", "user", users.get(1))
        );
    }

}
