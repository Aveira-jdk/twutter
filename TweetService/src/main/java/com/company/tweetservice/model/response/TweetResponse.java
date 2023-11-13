package com.company.tweetservice.model.response;

import java.time.LocalDateTime;

public record TweetResponse(
        String tweet,
        long likeCount,
        LocalDateTime tweetDate,
        Long userId
) {
}
