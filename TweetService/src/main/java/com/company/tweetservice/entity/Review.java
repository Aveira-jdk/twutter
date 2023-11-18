package com.company.tweetservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "review")
    String review;

    @Column(name = "like_count",columnDefinition = "int default 0")
    int likeCount;

    @Column(name = "review_date",columnDefinition = "timestamp default now()")
    LocalDateTime reviewDate;

    @Column(name = "user_id",nullable = false)
    Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    Tweet tweet;

    @OneToMany(mappedBy = "review")
    Set<ReviewLike> likes;
}

