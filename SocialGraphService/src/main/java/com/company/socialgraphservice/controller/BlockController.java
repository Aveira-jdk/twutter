package com.company.socialgraphservice.controller;

import com.company.socialgraphservice.client.AuthClient;
import com.company.socialgraphservice.service.BlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/twutter/SOCIAL-GRAPH/blocks")
@RequiredArgsConstructor
public class BlockController {

    private final BlockService blockService;
    private final AuthClient authClient;

    @PostMapping("/add-block/{blockId}")
    public void addBlock(@RequestHeader(name = "Authorization") String authorizationHeader,
                          @PathVariable Long blockId){
        String token = authorizationHeader.replace("Bearer ", "");
        Long userId = authClient.extractId(token);
        blockService.addBlock(userId, blockId);
    }

    @DeleteMapping("/delete-block/{blockId}")
    public void deleteBlock(@RequestHeader(name = "Authorization") String authorizationHeader,
                            @PathVariable Long blockId){
        String token = authorizationHeader.replace("Bearer ", "");
        Long userId = authClient.extractId(token);
        blockService.deleteBlock(userId, blockId);
    }

    @GetMapping("/get-blocks")
    public Set<Long> getBlocksId(@RequestHeader(name = "Authorization") String authorizationHeader){
        String token = authorizationHeader.replace("Bearer ", "");
        Long userId = authClient.extractId(token);
        return blockService.getBlocksId(userId);
    }
}
