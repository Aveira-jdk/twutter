package com.company.timelineservice;

import feign.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class TimeLineServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeLineServiceApplication.class, args);
    }

}
