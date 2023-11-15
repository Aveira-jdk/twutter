package com.company.tweetservice.model.mapper;

import com.company.tweetservice.entity.Tweet;
import com.company.tweetservice.model.response.TweetResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TweetMapper {
    TweetResponse toTweetResponse(Tweet tweet);
}

