package com.example.authservice.client;

import com.example.authservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "USER-SERVICE")
public interface UserClient {

    @RequestMapping(method = RequestMethod.GET, value = "/accounts/get-by-username")
    User getUserByUsername(@RequestParam String username);
}
