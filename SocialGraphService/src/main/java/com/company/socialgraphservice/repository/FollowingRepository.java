package com.company.socialgraphservice.repository;

import com.company.socialgraphservice.entity.RootEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public interface FollowingRepository extends JpaRepository<RootEntity, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO users_followings (user_id,following_id) VALUES (:userId, :followingId)",
            nativeQuery = true)
    void addFollowing(Long userId, Long followingId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM users_followings WHERE user_id =:userId AND following_id =:followingId", nativeQuery = true)
    void deleteFollowing(Long userId, Long followingId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM users_followings WHERE user_id =:followerId AND following_id =:userId", nativeQuery = true)
    void deleteFollower(Long userId, Long followerId);

    @Transactional
    @Modifying
    @Query(value = "SELECT following_id FROM users_followings WHERE user_id =:userId", nativeQuery = true)
    Set<Long> getFollowingsId(Long userId);

    @Transactional
    @Modifying
    @Query(value = "SELECT user_id FROM users_followings WHERE following_id =:userId", nativeQuery = true)
    Set<Long> getFollowersId(Long userId);
}
