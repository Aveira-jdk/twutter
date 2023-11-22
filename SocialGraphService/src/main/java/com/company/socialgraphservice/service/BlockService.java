package com.company.socialgraphservice.service;

import com.company.socialgraphservice.client.AuthClient;
import com.company.socialgraphservice.repository.BlockRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class BlockService {

    private final BlockRepository blockRepository;
    private final FollowingService followingService;
    Logger logger = LoggerFactory.getLogger(BlockService.class);

    public void addBlock(Long userId, Long blockId){
        blockRepository.addBlock(userId, blockId);
        logger.info(String.format("User id: %d and block id: %d was added", userId, blockId));
        followingService.deleteFollower(userId, blockId);
        logger.info(String.format("User id: %d and follower id: %d was deleted", userId, blockId));
        followingService.deleteFollowing(userId, blockId);
        logger.info(String.format("User id: %d and following id: %d was deleted", userId, blockId));
    }

    public void deleteBlock(Long userId, Long blockId){
        blockRepository.deleteBlock(userId, blockId);
        logger.info(String.format("User id: %d and block id: %d was deleted", userId, blockId));
    }

    public Set<Long> getBlocksId(Long userId){
        Set<Long> blocksId = blockRepository.getBlocksId(userId);
        if (blocksId.equals(null))
            logger.warn(String.format("User with this id: %d did not block anyone", userId));
        return blocksId;
    }
}
