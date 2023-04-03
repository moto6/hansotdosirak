package com.hansotdosirak.honux.repository;

import com.hansotdosirak.honux.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface PostLockJpaRepository extends JpaRepository<Post,Long> {
    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<Post> findById(Long postId);
}
