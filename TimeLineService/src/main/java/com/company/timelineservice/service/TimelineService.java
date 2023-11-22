package com.company.timelineservice.service;

import com.company.timelineservice.client.SocialGraphClient;
import com.company.timelineservice.client.TweetClient;
import com.company.timelineservice.client.UserClient;
import com.company.timelineservice.model.TimelineTweet;
import com.company.timelineservice.model.dto.response.TweetResponseDTO;
import com.company.timelineservice.model.dto.response.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TimelineService {

    private final SocialGraphClient socialGraphClient;
    private final TweetClient tweetClient;
    private final UserClient userClient;
    private final Logger logger = LoggerFactory.getLogger(TimelineService.class);

    public Set<TimelineTweet> getTimelineTweets(Long userId) {
        Set<Long> followingsId = getFollowingsId(userId);
        if (followingsId.equals(null))
            return null;
        Set<TweetResponseDTO> tweets = getTweetByUserId(followingsId);
        Set<UserResponseDTO> users = getUsersById(followingsId);
        return getTimelineTweets(followingsId, tweets, users);
    }

    public Set<TimelineTweet> getRecommendedTimelineTweets(Long userId) {
        Set<Long> recommendedUsers = getRecommendedUsersId(userId);
        if (recommendedUsers.equals(null))
            return null;
        Set<TweetResponseDTO> tweets = getTweetByUserId(recommendedUsers);
        Set<UserResponseDTO> users = getUsersById(recommendedUsers);
        return getTimelineTweets(recommendedUsers, tweets, users);
    }

    public Set<Long> getRecommendedUsersId(Long userId) {
        Set<Long> recommendedUsersId = socialGraphClient.getRecommendedUsersId(userId);
        logger.info("The getRecommendedUsersId method on socialGraphClient vas called");
        return recommendedUsersId;
    }

    public Set<Long> getFollowingsId(Long userId) {
        Set<Long> followingsId = socialGraphClient.getFollowingsId(userId);
        logger.info("The getFollowings method on socialGraphClient was called");
        return followingsId;
    }

    private Set<TweetResponseDTO> getTweetByUserId(Set<Long> usersId) {
        logger.info("usersId: {}", usersId);
        Set<TweetResponseDTO> tweets = tweetClient.getTweetsByUser(usersId);
        logger.info("The getTweetsByUser method on tweetClient was called");
        return tweets;
    }

    private Set<UserResponseDTO> getUsersById(Set<Long> usersId) {
        Set<UserResponseDTO> users = userClient.getUsersById(usersId);
        logger.info("The getUsersById method on userClient was called");
        return users;
    }

    private Set<TimelineTweet> getTimelineTweets(Set<Long> usersId,
                                                 Set<TweetResponseDTO> tweets,
                                                 Set<UserResponseDTO> users) {
        Set<TimelineTweet> timelineTweets = new HashSet<>();
        usersId.forEach(id ->
                timelineTweets.add(new TimelineTweet(
                        (UserResponseDTO) users.stream().filter(user -> id.equals(user.getUserId())),
                        (Set<TweetResponseDTO>) tweets.stream().filter(tweet -> id.equals(tweet.getUserId()))
                ))
        );
        return timelineTweets;
    }
}
