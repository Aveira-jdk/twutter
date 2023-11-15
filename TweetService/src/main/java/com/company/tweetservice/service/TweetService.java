package com.company.tweetservice.service;

import com.company.tweetservice.entity.Review;
import com.company.tweetservice.entity.Tweet;
import com.company.tweetservice.exception.NotFoundException;
import com.company.tweetservice.model.mapper.TweetMapper;
import com.company.tweetservice.model.request.TweetRequest;
import com.company.tweetservice.model.response.TweetResponse;
import com.company.tweetservice.repository.ReviewRepository;
import com.company.tweetservice.repository.TweetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TweetService {
    private final TweetLikeService tweetLikeService;
    private final ReviewService reviewService;
    private final ReviewLikeService reviewLikeService;
    private final TweetRepository tweetRepository;
    private final TweetMapper tweetMapper;

    public TweetResponse listTweetsByUser(Long userId) {
        Tweet tweets = tweetRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Tweets not found - userId id " + userId));
        return tweetMapper.toTweetResponse(tweets);
    }

    public TweetResponse getTweet(Long id) {
        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tweets not found - tweet id " + id));
        return tweetMapper.toTweetResponse(tweet);
    }

    public void createTweet(Long userId, TweetRequest tweetRequest) {
        Tweet tweet = Tweet.builder()
                .tweet(tweetRequest.getTweet())
                .tweetDate(LocalDateTime.now())
                .userId(userId)
                .build();
        tweetRepository.save(tweet);
    }

    public void deleteTweet(Long id) {
        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tweet not found - tweet id " + id));
        tweetLikeService.deleteLikeByTweetId(id);
        reviewService.deleteReviewsByTweetId(id);
        tweet.setReviews(null);
        tweet.setLikes(null);

        tweetRepository.delete(tweet);
    }

}


