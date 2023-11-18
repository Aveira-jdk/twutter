package com.company.tweetservice.model.response;

import java.time.LocalDateTime;

public record ReviewResponse (
        String review,
        int likeCount,
        LocalDateTime reviewDate,
        Long userId
){
}

