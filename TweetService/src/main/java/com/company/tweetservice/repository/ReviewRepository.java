package com.company.tweetservice.repository;

import com.company.tweetservice.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    @Query(value = "select * from reviews where tweet_id=:tweetId", nativeQuery = true)
    Optional<List<Review>> getReviews(Long tweetId);

    @Query(value = "select * from reviews where id=:reviewId", nativeQuery = true)
    Optional<Review> findReviewById(Long reviewId);

    @Query(value = "select * from reviews r where r.tweet_id=:tweetId and r.id=:id", nativeQuery = true)
    Optional<Review> findByTweetIdAndReviewId(Long tweetId, Long id);
}
