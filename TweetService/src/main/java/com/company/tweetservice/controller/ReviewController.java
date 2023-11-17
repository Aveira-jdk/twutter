package com.company.tweetservice.controller;

import com.company.tweetservice.model.request.ReviewRequest;
import com.company.tweetservice.model.response.ReviewLikeResponse;
import com.company.tweetservice.model.response.ReviewResponse;
import com.company.tweetservice.service.ReviewLikeService;
import com.company.tweetservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/twutter/TWEET/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewLikeService reviewLikeService;

    @GetMapping("/tweet-id")
    public List<ReviewResponse> getReviews(@RequestParam("tweetId") Long tweetId) {
        return reviewService.getReviews(tweetId);
    }

    @PostMapping("/add-review/tweet-id/{id}")
    public void createReview(@PathVariable Long id,
                             @RequestBody ReviewRequest reviewRequest) {
        reviewService.createReview(id, reviewRequest);
    }

    @DeleteMapping("/delete-review/tweet-id/{tweet-id}/")
    public void deleteReview(@PathVariable("tweet-id") Long tweetId,
                             @RequestParam("review-id") Long id) {
        reviewService.deleteReview(tweetId,id);
    }

    @PatchMapping("/like")
    public void reviewLike(@RequestParam("user-id") Long userId,
                           @RequestParam("tweet-id") Long reviewId) {
        reviewLikeService.reviewLike(userId,reviewId);
    }

    @GetMapping("/{review-id}/likes")
    public List<ReviewLikeResponse> getLikes(@PathVariable("review-id") Long reviewId) {
        return reviewLikeService.getLikes(reviewId);
    }

    @DeleteMapping("/delete-like/user-id/{user-id}/review-id/{review-id}")
    public void deleteLikeByReviewIdAndUserId(@PathVariable("user-id") Long userId,
                                              @PathVariable("review-id") Long reviewId) {
        reviewLikeService.deleteLikeByUserIdAndReviewId(userId,reviewId);
    }

}

