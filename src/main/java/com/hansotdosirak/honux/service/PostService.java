package com.hansotdosirak.honux.service;

import com.hansotdosirak.honux.domain.Post;
import com.hansotdosirak.honux.repository.PostJdbcRepository;
import com.hansotdosirak.honux.repository.PostLockJpaRepository;
import com.hansotdosirak.honux.repository.PostAtomicJpqlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PostService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final PostAtomicJpqlRepository postAtomicJpqlRepository;
    private final PostLockJpaRepository postLockJpaRepository;
    private final PostJdbcRepository postJdbcRepository;

    public PostService(PostAtomicJpqlRepository postAtomicJpqlRepository, PostLockJpaRepository postLockJpaRepository, PostJdbcRepository postJdbcRepository) {
        this.postAtomicJpqlRepository = postAtomicJpqlRepository;
        this.postLockJpaRepository = postLockJpaRepository;
        this.postJdbcRepository = postJdbcRepository;
    }

    @Transactional
    public void updateLikeCountBaseLine(Long postId) {
        Optional<Post> byId = postAtomicJpqlRepository.findById(postId);
        Post post = byId.get();
        post.likeCountUp();
    }

    @Transactional
    public void updateLikeCountV1LockOnly(Long postId) {
        Optional<Post> byId = postLockJpaRepository.findById(postId);
        Post post = byId.get();
        post.likeCountUp();
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void updateLikeCountV2IsolationOnly(Long postId) {
        Optional<Post> byId = postAtomicJpqlRepository.findById(postId);
        Post post = byId.get();
        post.likeCountUp();
    }

    @Transactional
    public void updateLikeCountV3Atomic(Long postId) {
        //postRepository.updateAtomic(postId);
        postJdbcRepository.update(postId);
    }

    //@Transactional
    public void updateLikeCountV4NoTrans(Long postId) {
        //postRepository.updateAtomic(postId);
        postJdbcRepository.update(postId);
    }
}
