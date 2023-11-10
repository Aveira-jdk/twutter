package com.example.socialgraphservice.service;

import com.example.socialgraphservice.repository.FollowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowerService {

    private final FollowerRepository followerRepository;

    public void addFollower(Long userId, Long followerId){
        followerRepository.addFollower(userId, followerId);
    }

    public void deleteFollower(Long followerId){
        followerRepository.deleteFollower(followerId);
    }
}
