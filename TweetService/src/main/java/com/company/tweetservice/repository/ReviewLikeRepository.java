package com.company.tweetservice.repository;

import com.company.tweetservice.entity.ReviewLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {
    @Modifying
    @Query("delete from ReviewLike r where r.review.id = :reviewId")
    void deleteLikeByReviewId(@Param("reviewId") Long reviewId);

    @Query(value = "select * from review_likes r where r.review_id=:reviewId", nativeQuery = true)
    List<ReviewLike> getLikes(@Param("reviewId") Long reviewId);

    @Query(value = "select * from review_likes r where r.user_id=:userId and r.review_id=:reviewId", nativeQuery = true)
    Optional<ReviewLike> findByUserIdAndReviewId(Long userId,Long reviewId);
}

