package com.company.tweetservice.model.response;

import java.time.LocalDateTime;

public record ReviewResponse (
        String review,
        long likeCount,
        LocalDateTime reviewDate,
        Long userId
){
}

