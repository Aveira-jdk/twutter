package com.company.tweetservice.model.mapper;

import com.company.tweetservice.entity.Tweet;
import com.company.tweetservice.model.response.TweetResponse;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface TweetMapper {
    TweetResponse toTweetResponse(Tweet tweet);
    List<TweetResponse> toTweetResponse(List<Tweet> tweet);
    Set<TweetResponse> toTweetResponseSet(Set<Tweet> tweet);
}

