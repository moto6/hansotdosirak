package com.hansotdosirak.honux.endpoint;

import com.hansotdosirak.honux.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/{postId}")
    public ResponseEntity<?> updateLike(@PathVariable Long postId) {
        postService.updateLikeCountBaseLine(postId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/v1/{postId}")
    public ResponseEntity<?> updateLikeV1(@PathVariable Long postId) {
        postService.updateLikeCountV1LockOnly(postId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/v2/{postId}")
    public ResponseEntity<?> updateLikeV2(@PathVariable Long postId) {
        postService.updateLikeCountV2IsolationOnly(postId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/v3/{postId}")
    public ResponseEntity<?> updateLikeV3(@PathVariable Long postId) {
        postService.updateLikeCountV3Atomic(postId);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/v4/{postId}")
    public ResponseEntity<?> updateLikeV4(@PathVariable Long postId) {
        postService.updateLikeCountV4NoTrans(postId);
        return ResponseEntity.ok().build();
    }
//
//    @PostMapping("/v5/{postId}")
//    public ResponseEntity<?> updateLikeV5(@PathVariable Long postId) {
//        postService.updateLikeCountV4NoTrans(postId);
//        return ResponseEntity.ok().build();
//    }




}
