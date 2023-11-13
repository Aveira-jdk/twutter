package com.company.tweetservice.service;

import com.company.tweetservice.entity.Review;
import com.company.tweetservice.entity.Tweet;
import com.company.tweetservice.exception.NotFoundException;
import com.company.tweetservice.model.mapper.ReviewMapper;
import com.company.tweetservice.model.request.ReviewRequest;
import com.company.tweetservice.model.response.ReviewResponse;
import com.company.tweetservice.repository.ReviewRepository;
import com.company.tweetservice.repository.TweetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final TweetRepository tweetRepository;
    private final ReviewMapper reviewMapper;
    private final ReviewLikeService reviewLikeService;

    public List<ReviewResponse> getReviews(Long tweetId) {
        List<Review> reviews = reviewRepository.getReviews(tweetId)
                .orElseThrow(() -> new NotFoundException("Review not found - review id " + tweetId));
        return reviewMapper.toTweetResponse(reviews);
    }

    @Transactional
    public void createReview(Long id, ReviewRequest reviewRequest) {
        Tweet tweet = tweetRepository.findById(id).get();

        Review review = reviewMapper.requestToEntity(reviewRequest);
        review.setUserId(reviewRequest.getUserId());
        review.setReview(reviewRequest.getReview());
        review.setReviewDate(LocalDateTime.now());
        review.setTweet(tweet);

        reviewRepository.save(review);
    }

    @Transactional
    public void deleteReview(Long tweetId,Long id) {
        Review review = reviewRepository.findByTweetIdAndReviewId(tweetId,id)
                .orElseThrow(() -> new NotFoundException("Review not found - review id " + id));
        reviewLikeService.deleteLikeByReviewId(id);
        reviewRepository.delete(review);
    }
}


