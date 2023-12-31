package com.company.socialgraphservice.controller;

import com.company.socialgraphservice.service.FollowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/twutter/followings")
@RequiredArgsConstructor
public class FollowingController {

    private final FollowingService followingService;

    @PostMapping("/add-following/{userId}/{followingId}")
    public void addFollowing(@PathVariable Long userId, @PathVariable Long followingId){
        followingService.addFollowing(userId, followingId);
    }

    @DeleteMapping("/delete-following/{userId}/{followingId}")
    public void deleteFollowing(@PathVariable Long userId, @PathVariable Long followingId){
        followingService.deleteFollowing(userId, followingId);
    }

    @GetMapping("/get-followings/{userId}")
    public Set<Long> getFollowingsId(@PathVariable Long userId){
        return followingService.getFollowingsId(userId);
    }

    @GetMapping("/get-recommended/{userId}")
    public Set<Long> getRecommendedUsersId(@PathVariable Long userId){
        return followingService.getRecommendedUsersId(userId);
    }
}
