package com.example.timelineservice.service;

import com.example.timelineservice.client.SocialGraphClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class TimeLineService {

    private final SocialGraphClient socialGraphClient;

    public Set<Long> getFollowings(Long userId){
        Set<Long> set = socialGraphClient.getFollowings(userId);
        return set;
    }
}
