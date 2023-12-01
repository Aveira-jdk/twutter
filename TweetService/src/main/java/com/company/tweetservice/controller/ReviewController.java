package com.company.tweetservice.controller;

import com.company.tweetservice.client.AuthClient;
import com.company.tweetservice.model.request.ReviewRequest;
import com.company.tweetservice.model.response.ReviewLikeResponse;
import com.company.tweetservice.model.response.ReviewResponse;
import com.company.tweetservice.service.ReviewLikeService;
import com.company.tweetservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/twitter/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewLikeService reviewLikeService;
    private final AuthClient authClient;

    @GetMapping("/tweet-id")
    public List<ReviewResponse> getReviews(@RequestParam("tweetId") Long tweetId) {
        return reviewService.getReviews(tweetId);
    }

    @PostMapping("/add-review/{tweet-id}")
    public void createReview(@PathVariable("tweet-id") Long id,
                             @RequestBody ReviewRequest reviewRequest) {
        reviewService.createReview(id, reviewRequest);
    }

    @DeleteMapping("/delete-review/{review-id}/")
    public void deleteReview(@PathVariable("review-id") Long id) {
        reviewService.deleteReview(id);
    }

    @GetMapping("/{review-id}/likes")
    public List<ReviewLikeResponse> getLikes(@PathVariable("review-id") Long reviewId) {
        return reviewLikeService.getLikes(reviewId);
    }

    @PatchMapping("/like")
    public void reviewLike(@RequestHeader(name = "Authorization") String authorizationHeader,
                           @RequestParam("tweet-id") Long reviewId) {
        String token = authorizationHeader.replace("Bearer ", "");
        Long userId = authClient.extractId(token);
        reviewLikeService.reviewLike(userId,reviewId);
    }

    @DeleteMapping("/delete-like/review-id/{review-id}")
    public void deleteLikeByReviewIdAndUserId(@RequestHeader(name = "Authorization") String authorizationHeader,
                                              @PathVariable("review-id") Long reviewId) {
        String token = authorizationHeader.replace("Bearer ", "");
        Long userId = authClient.extractId(token);
        reviewLikeService.deleteLikeByUserIdAndReviewId(userId,reviewId);
    }
}

