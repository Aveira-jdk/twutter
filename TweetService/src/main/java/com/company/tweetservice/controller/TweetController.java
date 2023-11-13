package com.company.tweetservice.controller;

import com.company.tweetservice.model.request.TweetRequest;
import com.company.tweetservice.model.response.TweetLikeResponse;
import com.company.tweetservice.model.response.TweetResponse;
import com.company.tweetservice.service.TweetLikeService;
import com.company.tweetservice.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/twitter/tweets")
@RequiredArgsConstructor
public class TweetController {
    private final TweetService tweetService;
    private final TweetLikeService tweetLikeService;

    @PostMapping("/add-tweet/user-id/{user-id}")
    public void createTweet(@PathVariable("user-id") Long userId,
                            @RequestBody TweetRequest tweetRequest) {
        tweetService.createTweet(userId, tweetRequest);
    }

    @GetMapping("/user-id")
    public TweetResponse listTweetsByUser(@RequestParam("userId") Long userId) {
        return tweetService.listTweetsByUser(userId);
    }

    @GetMapping("/tweet-id/{id}")
    public TweetResponse getTweet(@PathVariable Long id) {
        return tweetService.getTweet(id);
    }

    @DeleteMapping("/delete-tweet/tweet-id/{id}")
    public void deleteTweet(@PathVariable Long id) {
        tweetService.deleteTweet(id);
    }

    @PatchMapping("/like")
    public void tweetLike(@RequestParam("user-id")Long userId,
                          @RequestParam("tweet-id")Long tweetId) {
        tweetLikeService.tweetLike(userId,tweetId);
    }

    @GetMapping("/{tweet-id}/likes")
    public List<TweetLikeResponse> getLikes(@PathVariable("tweet-id") Long tweetId) {
        return tweetLikeService.getLikes(tweetId);
    }


    @DeleteMapping("/delete-like/user-id/{user-id}")
    public void deleteLike(@PathVariable("user-id") Long userId) {
        tweetLikeService.deleteLike(userId);
    }

}

