package com.company.tweetservice.model.mapper;

import com.company.tweetservice.entity.ReviewLike;
import com.company.tweetservice.entity.TweetLike;
import com.company.tweetservice.model.response.ReviewLikeResponse;
import com.company.tweetservice.model.response.TweetLikeResponse;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface LikeMapper {
    List<TweetLikeResponse> toTweetLikeResponse(List<TweetLike> likes);
    List<ReviewLikeResponse> toReviewLikeResponse(List<ReviewLike> likes);

}

