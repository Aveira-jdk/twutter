package com.company.timelineservice.client;

import com.company.timelineservice.model.dto.response.TweetResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

@FeignClient(name = "TWEET-SERVICE", url = "localhost:8083")
public interface TweetClient {
    @RequestMapping(method = RequestMethod.POST, value = "/twutter/tweets/get-tweets-by-user")
    Set<TweetResponseDTO> getTweetsByUser(@RequestBody Set<Long> usersId);
}
