package com.example.socialgraphservice.controller;

import com.example.socialgraphservice.service.BlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/twutter/blocks")
@RequiredArgsConstructor
public class BlockController {

    private final BlockService blockService;

    @PostMapping("/add-block/{userId}/{blockId}")
    public void addBlock(@PathVariable Long userId, @PathVariable Long blockId){
        blockService.addBlock(userId, blockId);
    }

    @DeleteMapping("/delete-block/{userId}/{blockId}")
    public void deleteBlock(@PathVariable Long userId, @PathVariable Long blockId){
        blockService.deleteBlock(userId, blockId);
    }

    @GetMapping("/get-blocks/{blockId}")
    public Set<Long> getBlocksId(@PathVariable Long blockId){
        return blockService.getBlocksId(blockId);
    }
}
