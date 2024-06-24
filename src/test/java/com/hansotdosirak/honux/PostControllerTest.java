package com.hansotdosirak.honux;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

class PostControllerTest {

    @DisplayName("")
    @Test
    public void run() {
        call();
        //given
        //when
        //then
    }
    public void call() {
        WebClient webClient = WebClient.create();
        Mono<String> result = webClient.get()
                .uri("https://example.com/data")
                .retrieve()
                .bodyToMono(String.class);

        result.subscribe(
                data -> System.out.println(data),
                error -> System.err.println("Request error: " + error.getMessage()),
                () -> System.out.println("Request complete.")
        );

    }
}