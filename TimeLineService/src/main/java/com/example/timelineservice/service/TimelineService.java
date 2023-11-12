package com.example.timelineservice.service;

import com.example.timelineservice.client.SocialGraphClient;
import com.example.timelineservice.client.TweetClient;
import com.example.timelineservice.client.UserClient;
import com.example.timelineservice.model.TimelineTweet;
import com.example.timelineservice.model.dto.response.TweetResponseDTO;
import com.example.timelineservice.model.dto.response.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TimelineService {

    private final SocialGraphClient socialGraphClient;
    private final TweetClient tweetClient;
    private final UserClient userClient;

    public Set<TimelineTweet> getTimelineTweets(Long userId){
        Set<Long> followingsId = getFollowingsId(userId);
        Set<TweetResponseDTO> tweets = getTweetByUserId(followingsId);
        Set<UserResponseDTO> users = getUsersById(followingsId);
        return getTimelineTweets(followingsId, tweets, users);
    }

    private Set<Long> getFollowingsId(Long userId){
        Set<Long> set = socialGraphClient.getFollowingsId(userId);
        return set;
    }

    private Set<TweetResponseDTO> getTweetByUserId(Set<Long> usersId){
        return tweetClient.getTweetsByUser(usersId);
    }

    private Set<UserResponseDTO> getUsersById(Set<Long> usersId){
        return userClient.getUsersById(usersId);
    }

    private Set<TimelineTweet> getTimelineTweets(Set<Long> usersId,
                                               Set<TweetResponseDTO> tweets,
                                               Set<UserResponseDTO> users){
        Set<TimelineTweet> timelineTweets = new HashSet<>();
        usersId.forEach(id ->
            timelineTweets.add(new TimelineTweet(
                    (UserResponseDTO) users.stream().filter(user -> id.equals(user.getUserId())),
                    (TweetResponseDTO) tweets.stream().filter(tweet -> id.equals(tweet.getUserId()))
            ))
        );
        return timelineTweets;
    }
}
