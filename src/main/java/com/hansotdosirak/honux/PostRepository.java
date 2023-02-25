package com.hansotdosirak.honux;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
    Optional<Post> findById(Long postId);

    @Modifying
    @Query(value = "update Post set likeCount = likeCount+1")
    void updateAtomic(Long postId);
}
