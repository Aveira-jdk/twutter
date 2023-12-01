package com.company.tweetservice.repository;

import com.company.tweetservice.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
    @Query("select t from Tweet t where t.userId=:userId")
    Optional<List<Tweet>> findByUserId(@Param("userId") Long userId);

    @Query("select t from Tweet t where t.id=:tweetId")
    Optional<Tweet> getTweetByTweetId(Long tweetId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE tweets SET like_count = like_count + 1 WHERE id =:id", nativeQuery = true)
    void increaseLikeCount(Long id);

    @Query("select t from Tweet t where t.userId=:userId")
    Set<Tweet> getByUserId(@Param("userId") Long userId);
}

