package com.company.timelineservice.client;

import com.company.timelineservice.model.dto.response.TweetResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@FeignClient(name = "SG-SERVICE", url = "localhost:8082")
public interface SocialGraphClient {

    @RequestMapping(method = RequestMethod.GET, value = "/twutter/SOCIAL-GRAPH/followings/get-followings/{userId}")
    @ResponseBody
    Set<Long> getFollowingsId(@PathVariable Long userId);

    @RequestMapping(method = RequestMethod.GET, value = "/twutter/SOCIAL-GRAPH/followings/get-recommended/{userId}")
    Set<Long> getRecommendedUsersId(@PathVariable Long userId);
}
