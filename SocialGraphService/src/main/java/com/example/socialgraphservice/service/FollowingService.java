package com.example.socialgraphservice.service;

import com.example.socialgraphservice.repository.FollowingRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FollowingService {

    private final FollowingRepository followingRepository;
    private final Logger logger = LoggerFactory.getLogger(FollowingService.class);

    public void addFollowing(Long userId, Long followingId){
        followingRepository.addFollowing(userId, followingId);
    }

    public void deleteFollowing(Long followingId){
        followingRepository.deleteFollowing(followingId);
    }

    public Set<Long> getFollowingsId(Long userId){
        Set<Long> followingsId = followingRepository.getFollowingsId(userId);
        if (followingsId.equals(null))
            logger.warn(String.format("User with this id: %d is not following anyone", userId));
        return followingsId;
    }

    public Set<Long> getRecommendedUsersId(Long userId){
        Set<Long> recommendedUsersId = new HashSet<>();
        Set<Long> followingUsersId = followingRepository.getFollowingsId(userId);
        followingUsersId.forEach(id -> recommendedUsersId.addAll(followingRepository.getFollowingsId(id)));
        recommendedUsersId.removeAll(followingUsersId);
        return recommendedUsersId;
    }
}
