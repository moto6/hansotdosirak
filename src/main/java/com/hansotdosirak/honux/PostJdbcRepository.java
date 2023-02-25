package com.hansotdosirak.honux;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public void update(Long postId) {
        jdbcTemplate.update("update post set like_count = like_count+1 where post_id =?",postId);
    }

}
