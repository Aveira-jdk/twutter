package com.example.socialgraphservice.repository;

import com.example.socialgraphservice.entity.RootEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


@Repository
public interface FollowerRepository extends JpaRepository<RootEntity, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO users_followers (user_id, follower_id) VALUES (:userId, :followerId)",
            nativeQuery = true)
    void addFollower( Long userId, Long followerId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM users_followers WHERE follower_id =:followerId", nativeQuery = true)
    void deleteFollower(Long followerId);

    @Transactional
    @Modifying
    @Query(value = "SELECT follower_id FROM users_followers WHERE user_id =:userId", nativeQuery = true)
    Set<Long> getFollowersId(Long userId);
}
