package com.example.timelineservice.model;

import com.example.timelineservice.model.dto.response.TweetResponseDTO;
import com.example.timelineservice.model.dto.response.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TimelineTweet {
    UserResponseDTO userResponseDTO;
    TweetResponseDTO tweetResponseDTO;
}
