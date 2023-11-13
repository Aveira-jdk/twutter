package com.company.tweetservice.model.response;

import java.time.LocalDateTime;

public record ErrorResponse(
        int statusCode,
        String message,
        LocalDateTime localDateTime
) {
}
