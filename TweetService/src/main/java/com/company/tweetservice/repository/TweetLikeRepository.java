package com.company.tweetservice.repository;


import com.company.tweetservice.entity.TweetLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TweetLikeRepository extends JpaRepository<TweetLike, Long> {
    @Query(value = "select * from tweet_likes t where t.tweet_id=:tweetId", nativeQuery = true)
    List<TweetLike> getLikes(@Param("tweetId") Long tweetId);

    @Modifying
    @Query("delete from TweetLike t where t.tweet.id =:tweetId")
    void deleteLikeByTweetId(@Param("tweetId") Long tweetId);

    @Modifying
    @Query("delete from TweetLike t where t.userId =:userId")
    void deleteLike(@Param("userId") Long userId);

    @Query(value = "select * from tweet_likes t where t.user_id=:userId and t.tweet_id=:tweetId", nativeQuery = true)
    Optional<TweetLike> findByUserIdAndTweetId(Long userId, Long tweetId);
}

