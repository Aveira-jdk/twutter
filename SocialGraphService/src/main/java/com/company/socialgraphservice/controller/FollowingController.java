package com.company.socialgraphservice.controller;

import com.company.socialgraphservice.client.AuthClient;
import com.company.socialgraphservice.service.FollowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/twutter/SOCIAL-GRAPH/followings")
@RequiredArgsConstructor
public class FollowingController {

    private final FollowingService followingService;
    private final AuthClient authClient;

    @PostMapping("/add-following/{followingId}")
    public void addFollowing(@RequestHeader(name = "Authorization") String authorizationHeader,
                             @PathVariable Long followingId){
        String token = authorizationHeader.replace("Bearer ", "");
        Long userId = authClient.extractId(token);
        followingService.addFollowing(userId, followingId);
    }

    @DeleteMapping("/delete-following/{followingId}")
    public void deleteFollowing(@RequestHeader(name = "Authorization") String authorizationHeader,
                                @PathVariable Long followingId){
        String token = authorizationHeader.replace("Bearer ", "");
        Long userId = authClient.extractId(token);
        followingService.deleteFollowing(userId, followingId);
    }

    @DeleteMapping("/delete-follower/{followerId}")
    public void deleteFollower(@RequestHeader(name = "Authorization") String authorizationHeader,
                               @PathVariable Long followerId){
        String token = authorizationHeader.replace("Bearer ", "");
        Long userId = authClient.extractId(token);
        followingService.deleteFollower(userId, followerId);
    }

    @GetMapping("/get-followings")
    public Set<Long> getFollowingsId(@RequestHeader(name = "Authorization") String authorizationHeader){
        String token = authorizationHeader.replace("Bearer ", "");
        Long userId = authClient.extractId(token);
        return followingService.getFollowingsId(userId);
    }

    @GetMapping("/get-followers")
    public Set<Long> getFollowersId(@RequestHeader(name = "Authorization") String authorizationHeader){
        String token = authorizationHeader.replace("Bearer ", "");
        Long userId = authClient.extractId(token);
        return followingService.getFollowersId(userId);
    }

    @GetMapping("/get-recommended")
    public Set<Long> getRecommendedUsersId(@RequestHeader(name = "Authorization") String authorizationHeader){
        String token = authorizationHeader.replace("Bearer ", "");
        Long userId = authClient.extractId(token);
        return followingService.getRecommendedUsersId(userId);
    }

    @GetMapping("/get-recommended/{userId}")
    public Set<Long> getRecommendedUsersId(@PathVariable Long userId){
        return followingService.getRecommendedUsersId(userId);
    }

    @GetMapping("/get-followings/{userId}")
    public Set<Long> getFollowingsId(@PathVariable Long userId){
        return followingService.getFollowingsId(userId);
    }
}
