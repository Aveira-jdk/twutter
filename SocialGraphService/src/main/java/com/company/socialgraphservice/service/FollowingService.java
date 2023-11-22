package com.company.socialgraphservice.service;

import com.company.socialgraphservice.repository.FollowingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class FollowingService {

    private final FollowingRepository followingRepository;
    private final BlockService blockService;
    private final Logger logger = LoggerFactory.getLogger(FollowingService.class);

    public FollowingService(FollowingRepository followingRepository, @Lazy BlockService blockService) {
        this.followingRepository = followingRepository;
        this.blockService = blockService;
    }

    public void addFollowing(Long userId, Long followingId){
        followingRepository.addFollowing(userId, followingId);
        logger.info(String.format("User :id %d and following :id %d was added", userId, followingId));
    }

    public void deleteFollowing(Long userId, Long followingId){
        followingRepository.deleteFollowing(userId, followingId);
        logger.info(String.format("User id: %d and following id: %d was deleted", userId, followingId));
    }

    public void deleteFollower(Long userId, Long followerId){
        followingRepository.deleteFollower(userId, followerId);
        logger.info(String.format("User id: %d and follower id: %d was deleted", userId, followerId));
    }

    public Set<Long> getFollowingsId(Long userId){
        Set<Long> followingsId = followingRepository.getFollowingsId(userId);
        if (followingsId.equals(null))
            logger.warn(String.format("User with this id: %d is not following anyone", userId));
        return followingsId;
    }

    public Set<Long> getFollowersId(Long userId){
        Set<Long> followersId = followingRepository.getFollowersId(userId);
        return followersId;
    }

    public Set<Long> getRecommendedUsersId(Long userId){
        Set<Long> recommendedUsersId = new HashSet<>();
        Set<Long> followingsId = getFollowingsId(userId);
        followingsId.forEach(id -> recommendedUsersId.addAll(getFollowingsId(id)));
        recommendedUsersId.removeAll(followingsId);
        Set<Long> blocksId = blockService.getBlocksId(userId);
        recommendedUsersId.removeAll(blocksId);
        return recommendedUsersId;
    }
}
