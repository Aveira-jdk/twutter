package com.company.tweetservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "retweets")
public class Retweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    Tweet tweet;

    @Column(name = "retweet_date",columnDefinition = "timestamp default now()")
    LocalDateTime retweetDate;

    @Column(name = "user_id",nullable = false)
    Long userId;
}


