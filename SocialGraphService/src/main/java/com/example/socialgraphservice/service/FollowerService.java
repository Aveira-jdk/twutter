package com.example.socialgraphservice.service;

import com.example.socialgraphservice.repository.FollowerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class FollowerService {

    private final FollowerRepository followerRepository;
    private final Logger logger = LoggerFactory.getLogger(FollowingService.class);

    public void addFollower(Long userId, Long followerId){
        followerRepository.addFollower(userId, followerId);
        logger.info(String.format("User id: %d and follower id: %d was added", userId, followerId));
    }

    public void deleteFollower(Long userId, Long followerId){
        followerRepository.deleteFollower(userId, followerId);
        logger.info(String.format("User id: %d and follower id: %d was deleted", userId, followerId));
    }

    public Set<Long> getFollowersId(Long userId){
        Set<Long> followersId = followerRepository.getFollowersId(userId);
        if (followersId.equals(null))
            logger.warn(String.format("User with this id: %d has no followers", userId));
        return followersId;
    }
}
