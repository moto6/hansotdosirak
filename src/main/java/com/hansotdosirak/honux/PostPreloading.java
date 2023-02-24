package com.hansotdosirak.honux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Configuration
public class PostPreloading {
    static private final Logger log = LoggerFactory.getLogger("PostPreloading");
    @Autowired
    private PostRepository postRepository;
    @Bean
    public CommandLineRunner run(PostRepository postRepository) {
        return args -> {
            postRepository.save(new Post("hi", "베이스라인"));
            postRepository.save(new Post("hola", "락"));
            postRepository.save(new Post("aloha", "리피터블리드"));
        };
    }

    @PostConstruct
    public void init() throws InterruptedException {
        Thread.sleep(100);
    }
}
