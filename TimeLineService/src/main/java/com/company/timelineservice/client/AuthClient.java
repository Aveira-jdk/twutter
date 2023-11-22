package com.company.timelineservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "AUTH-SERVICE", url = "localhost:8080")
public interface AuthClient {

    @RequestMapping(method = RequestMethod.GET, value = "/twutter/AUTH/extract-id")
    Long extractId(@RequestParam String token);
}
