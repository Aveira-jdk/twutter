package com.example.socialgraphservice.controller;

import com.example.socialgraphservice.service.FollowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/twutter/followers")
@RequiredArgsConstructor
public class FollowerController {

    private final FollowerService followerService;

    @PostMapping("/add-follower/{userId}/{followerId}")
    public void addFollower(@PathVariable Long userId, @PathVariable Long followerId){
        followerService.addFollower(userId, followerId);
    }

    @DeleteMapping("/delete-follower/{userId}/{followerId}")
    public void deleteFollower(@PathVariable Long userId, @PathVariable Long followerId){
        followerService.deleteFollower(userId, followerId);
    }

    @GetMapping("/get-followers/{userId}")
    public Set<Long> getFollowersId(@PathVariable Long userId){
        return followerService.getFollowersId(userId);
    }
}
