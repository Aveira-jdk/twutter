package com.company.timelineservice.controller;

import com.company.timelineservice.client.AuthClient;
import com.company.timelineservice.model.TimelineTweet;
import com.company.timelineservice.service.TimelineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/twutter/TIME-LINE")
@RequiredArgsConstructor
public class TimelineController {

    private final TimelineService timeLineService;
    private final AuthClient authClient;

    @GetMapping("/get-timeline-tweets")
    public Set<TimelineTweet> getTimelineTweets(@RequestHeader(name = "Authorization") String authorizationHeader){
        String token = authorizationHeader.replace("Bearer ", "");
        Long userId = authClient.extractId(token);
        return timeLineService.getTimelineTweets(userId);
    }

    @GetMapping("/get-recommended-timeline-tweets")
    public Set<TimelineTweet> getRecommendedTimelineTweets(@RequestHeader(name = "Authorization") String authorizationHeader){
        String token = authorizationHeader.replace("Bearer ", "");
        Long userId = authClient.extractId(token);
        return timeLineService.getRecommendedTimelineTweets(userId);
    }
}
