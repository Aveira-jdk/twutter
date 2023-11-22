package com.company.timelineservice.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TweetResponseDTO {
    String tweet;
    long likeCount;
    LocalDateTime tweetDate;
    Long userId;
}
