package com.company.tweetservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tweets")
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "tweet",nullable = false)
    String tweet;

    @Column(name = "like_count",columnDefinition = "int default 0")
    int likeCount;

    @Column(name = "tweet_date",columnDefinition = "timestamp default now()")
    LocalDateTime tweetDate;

    @Column(name = "user_id",nullable = false)
    Long userId;

    @OneToMany(mappedBy = "tweet")
    List<Review>reviews;

    @OneToMany(mappedBy = "tweet")
    Set<TweetLike> likes;
}

