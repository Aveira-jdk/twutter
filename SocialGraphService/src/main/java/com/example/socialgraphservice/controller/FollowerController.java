package com.example.socialgraphservice.controller;

import com.example.socialgraphservice.service.FollowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/twutter/followers")
@RequiredArgsConstructor
public class FollowerController {

    private final FollowerService followerService;

    @PostMapping("/add-follower/{userId}/{followerId}")
    public void addFollower(@PathVariable Long userId, @PathVariable Long followerId){
        followerService.addFollower(userId, followerId);
    }

    @DeleteMapping("/delete-follower/{followerId}")
    public void deleteFollower(@PathVariable Long followerId){
        followerService.deleteFollower(followerId);
    }
}
