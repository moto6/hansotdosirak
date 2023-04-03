package com.hansotdosirak.honux.repository;

import com.hansotdosirak.honux.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostAtomicJpqlRepository extends JpaRepository<Post,Long> {
    Optional<Post> findById(Long postId);

    @Modifying
    @Query(value = "update Post set likeCount = likeCount+1 where id =:postId")
    void updateAtomic(@Param(value = "postId") Long postId);
}
