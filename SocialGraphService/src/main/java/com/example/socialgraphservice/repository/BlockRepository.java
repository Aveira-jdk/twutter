package com.example.socialgraphservice.repository;

import com.example.socialgraphservice.entity.RootEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public interface BlockRepository extends JpaRepository<RootEntity, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO users_followers (user_id, block_id) VALUES (:userId, :blockId)",
            nativeQuery = true)
    void addBlock(Long userId, Long blockId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM users_followers WHERE block_id =:blockId", nativeQuery = true)
    void deleteBlock(Long blockId);

    @Transactional
    @Modifying
    @Query(value = "SELECT block_id FROM users_blocks WHERE user_id =:userId", nativeQuery = true)
    Set<Long> getBlocksId(Long userId);
}
