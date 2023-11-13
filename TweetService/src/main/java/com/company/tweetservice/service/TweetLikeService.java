package com.company.tweetservice.service;

import com.company.tweetservice.entity.Tweet;
import com.company.tweetservice.entity.TweetLike;
import com.company.tweetservice.exception.AlreadyExistsException;
import com.company.tweetservice.exception.NotFoundException;
import com.company.tweetservice.model.mapper.LikeMapper;
import com.company.tweetservice.model.response.TweetLikeResponse;
import com.company.tweetservice.repository.TweetLikeRepository;
import com.company.tweetservice.repository.TweetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TweetLikeService {
    private final TweetRepository tweetRepository;
    private final TweetLikeRepository tweetLikeRepository;
    private final LikeMapper likeMapper;

    public List<TweetLikeResponse> getLikes(Long tweetId) {
        List<TweetLike> likes = tweetLikeRepository.getLikes(tweetId);
        return likeMapper.toTweetLikeResponse(likes);
    }


    public void tweetLike(Long userId, Long tweetId) {
        Optional<TweetLike> tweetLike = tweetLikeRepository.findByUserIdAndTweetId(userId, tweetId);

        if (tweetLike.isPresent()) {
            throw new AlreadyExistsException("You liked this tweet " + tweetId);
        } else {
            Tweet tweet = tweetRepository.getTweetByTweetId(tweetId)
                    .orElseThrow(() -> new NotFoundException("Tweet not found - tweet id " + tweetId));

            TweetLike newLike = new TweetLike();
            newLike.setUserId(userId);
            newLike.setTweet(tweet);
            tweetLikeRepository.save(newLike);
        }
    }

    @Transactional
    public void deleteLike(Long userId) {
        tweetLikeRepository.deleteLike(userId);
    }

    @Transactional
    public void deleteLikeByTweetId(Long tweetId) {
        tweetLikeRepository.deleteLikeByTweetId(tweetId);
    }
}

