package com.company.tweetservice.model.mapper;

import com.company.tweetservice.entity.Review;
import com.company.tweetservice.model.request.ReviewRequest;
import com.company.tweetservice.model.response.ReviewResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Review requestToEntity(ReviewRequest reviewRequest);
    ReviewResponse toReviewResponse(Review review);

    List<ReviewResponse> toTweetResponse(List<Review> reviews);
}

