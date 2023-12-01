package com.company.tweetservice.controller;

import com.company.tweetservice.client.AuthClient;
import com.company.tweetservice.model.request.TweetRequest;
import com.company.tweetservice.model.response.TweetLikeResponse;
import com.company.tweetservice.model.response.TweetResponse;
import com.company.tweetservice.service.TweetLikeService;
import com.company.tweetservice.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/twitter/tweets")
@RequiredArgsConstructor
public class TweetController {
    private final TweetService tweetService;
    private final TweetLikeService tweetLikeService;
    private final AuthClient authClient;

    @PostMapping("/add-tweet")
    public void createTweet(@RequestHeader(name = "Authorization") String authorizationHeader,
                            @RequestBody TweetRequest tweetRequest) {
        String token = authorizationHeader.replace("Bearer ", "");
        Long userId = authClient.extractId(token);
        tweetService.createTweet(userId, tweetRequest);
    }

    @GetMapping("/{user-id}")
    public List<TweetResponse> tweetsByUser(@PathVariable(name = "user-id") Long userId) {
        return tweetService.tweetsByUser(userId);
    }

    @PostMapping("/get-tweets-by-user")
    public Set<TweetResponse> listTweetsByUser(@RequestBody Set<Long> userId) {
        Set<Long> set = new HashSet<>(userId);
        LoggerFactory.getLogger(TweetController.class).info("userId: {}", userId);
        return tweetService.listTweetsByUser(set);
    }

    @GetMapping("/{id}")
    public TweetResponse getTweet(@PathVariable Long id) {
        return tweetService.getTweet(id);
    }

    //LOOK AT
    @DeleteMapping("/delete-tweet/{id}")
    public void deleteTweet(@PathVariable Long id) {
        tweetService.deleteTweet(id);
    }

    @PatchMapping("/like")
    public void tweetLike(@RequestHeader(name = "Authorization") String authorizationHeader,
                          @RequestParam("tweet-id")Long tweetId) {
        String token = authorizationHeader.replace("Bearer ", "");
        Long userId = authClient.extractId(token);
        tweetLikeService.tweetLike(userId,tweetId);
    }

    @GetMapping("/likes/{tweet-id}")
    public List<TweetLikeResponse> getLikes(@PathVariable("tweet-id") Long tweetId) {
        return tweetLikeService.getLikes(tweetId);
    }


    @DeleteMapping("/delete-like/{user-id}")
    public void deleteLike(@PathVariable("user-id") Long userId) {
        tweetLikeService.deleteLike(userId);
    }
}

