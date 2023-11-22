package com.example.authservice.client;

import com.example.authservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "USER-SERVICE", url = "localhost:8081")
public interface UserClient {

    @RequestMapping(value = "/twutter/USER/accounts/get-by-username", method = RequestMethod.GET)
    @ResponseBody
    User getUserByUsername(@RequestParam String username);
}
