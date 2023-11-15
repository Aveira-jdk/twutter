package com.company.tweetservice.service;

import com.company.tweetservice.entity.Review;
import com.company.tweetservice.entity.ReviewLike;
import com.company.tweetservice.exception.AlreadyExistsException;
import com.company.tweetservice.exception.NotFoundException;
import com.company.tweetservice.model.mapper.LikeMapper;
import com.company.tweetservice.model.response.ReviewLikeResponse;
import com.company.tweetservice.repository.ReviewLikeRepository;
import com.company.tweetservice.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewLikeService {
    private final ReviewLikeRepository reviewLikeRepository;
    private final ReviewRepository reviewRepository;
    private final LikeMapper likeMapper;

    public List<ReviewLikeResponse> getLikes(Long reviewId) {
        List<ReviewLike> likes = reviewLikeRepository.getLikes(reviewId);

        return likeMapper.toReviewLikeResponse(likes);
    }

    @Transactional
    public void reviewLike(Long userId, Long reviewId) {
        Optional<ReviewLike> reviewLike = reviewLikeRepository.findByUserIdAndReviewId(userId, reviewId);
        if (reviewLike.isPresent()) {
            throw new AlreadyExistsException("You liked this review " + reviewId);
        } else {
            Review review = reviewRepository.findReviewById(reviewId)
                    .orElseThrow(() -> new NotFoundException("Review not found - review id " + reviewId));

            ReviewLike newLike = new ReviewLike();
            newLike.setUserId(userId);
            newLike.setReview(review);
            reviewLikeRepository.save(newLike);
        }
    }

    @Transactional
    public void deleteLikeByReviewId(Long reviewId) {
        reviewLikeRepository.deleteLikeByReviewId(reviewId);
    }


    public void deleteLikeByUserIdAndReviewId(Long userId, Long reviewId) {
        Optional<ReviewLike> reviewLike = reviewLikeRepository.findByUserIdAndReviewId(userId, reviewId);
        reviewLike.ifPresent(reviewLikeRepository::delete);
    }
}
