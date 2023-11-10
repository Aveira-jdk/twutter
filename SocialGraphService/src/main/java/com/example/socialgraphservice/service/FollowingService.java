package com.example.socialgraphservice.service;

import com.example.socialgraphservice.repository.FollowingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FollowingService {

    private final FollowingRepository followingRepository;

    public void addFollowing(Long userId, Long followingId){
        followingRepository.addFollowing(userId, followingId);
    }

    public void deleteFollowing(Long followingId){
        followingRepository.deleteFollowing(followingId);
    }

    public Set<Long> getFollowingsId(Long userId){
        return followingRepository.getFollowingsId(userId);
    }

    public Set<Long> getRecommendedUsersId(Long userId){
        Set<Long> recommendedUsersId = new HashSet<>();
        Set<Long> followingUsersId = followingRepository.getFollowingsId(userId);
        followingUsersId.forEach(id -> recommendedUsersId.addAll(followingRepository.getFollowingsId(id)));
        recommendedUsersId.removeAll(followingUsersId);
        return recommendedUsersId;
    }
}
