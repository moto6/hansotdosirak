package com.hansotdosirak.honux;

import com.hansotdosirak.honux.domain.Post;
import com.hansotdosirak.honux.repository.PostAtomicJpqlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class PostPreloading {
    static private final Logger log = LoggerFactory.getLogger("PostPreloading");
    @Autowired
    private PostAtomicJpqlRepository postAtomicJpqlRepository;

    @Bean
    public CommandLineRunner run(PostAtomicJpqlRepository postAtomicJpqlRepository) {
        return args -> {
            postAtomicJpqlRepository.save(new Post("hi", "베이스라인"));
            postAtomicJpqlRepository.save(new Post("hola", "락"));
            postAtomicJpqlRepository.save(new Post("aloha", "리피터블리드"));
            postAtomicJpqlRepository.save(new Post("KKKK", "아토믹하게+트랜잭션"));
            postAtomicJpqlRepository.save(new Post("어거스트", "아토믹하게+NO 트랜"));

            postAtomicJpqlRepository.save(new Post("hi", "베이스라인"));
            postAtomicJpqlRepository.save(new Post("hola", "락"));
            postAtomicJpqlRepository.save(new Post("aloha", "리피터블리드"));
            postAtomicJpqlRepository.save(new Post("KKKK", "아토믹하게+트랜잭션"));
            postAtomicJpqlRepository.save(new Post("어거스트", "아토믹하게+NO 트랜"));
        };
    }

    @PostConstruct
    public void init() throws InterruptedException {
        Thread.sleep(100);
    }
}
