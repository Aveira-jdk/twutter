package com.example.timelineservice.controller;

import com.example.timelineservice.model.TimelineTweet;
import com.example.timelineservice.service.TimelineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/twutter/time-line")
@RequiredArgsConstructor
public class TimelineController {

    private final TimelineService timeLineService;

    @GetMapping("/get-timeline-tweets/{userId}")
    public Set<TimelineTweet> getTimelineTweets(@PathVariable Long userId){
        return timeLineService.getTimelineTweets(userId);
    }
}
