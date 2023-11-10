package com.example.timelineservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

@FeignClient(name = "socialGraphFeignClient", url = "localhost:8082/twutter/followings",
        configuration = ClientConfiguration.class)
public interface SocialGraphClient {

    @RequestMapping(method = RequestMethod.GET, value = "/get-followings/{userId}", produces = "application/json")
    Set<Long> getFollowings(@PathVariable Long userId);
}
