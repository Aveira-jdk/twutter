package com.example.api_gateway.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AUTH-SERVICE", url = "localhost:8080")
public class AuthClient {
}
