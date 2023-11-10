package com.example.socialgraphservice.service;

import com.example.socialgraphservice.repository.BlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlockService {

    private final BlockRepository blockRepository;

    public void addBlock(Long userId, Long blockId){
        blockRepository.addBlock(userId, blockId);
    }

    public void deleteBlock(Long blockId){
        blockRepository.deleteBlock(blockId);
    }
}
