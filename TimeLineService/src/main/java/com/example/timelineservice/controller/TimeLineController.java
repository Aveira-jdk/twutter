package com.example.timelineservice.controller;

import com.example.timelineservice.service.TimeLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/twutter/time-line")
@RequiredArgsConstructor
public class TimeLineController {

    private final TimeLineService timeLineService;


    @GetMapping("/{userId}")
    public Set<Long> getFollowings(@PathVariable Long userId){
        return timeLineService.getFollowings(userId);
    }
}
