package com.company.timelineservice.client;

import com.company.timelineservice.model.dto.response.TweetResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@FeignClient(name = "SG-SERVICE", url = "localhost:8082", configuration = FeignConfig.class)
public interface SocialGraphClient {

    @RequestMapping(method = RequestMethod.GET, value = "/twutter/followings/get-followings/{userId}")
    @ResponseBody
    Set<Long> getFollowingsId(@PathVariable Long userId);

    @RequestMapping(method = RequestMethod.GET, value = "/followings/get-tweet")
    TweetResponseDTO getTweet();
}
