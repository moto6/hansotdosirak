package com.hansotdosirak;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.util.Arrays;


@SpringBootApplication
//@PropertySource("classpath:application-${spring.profiles.active}.properties")
@PropertySource("classpath:application-${spring.config.activate.on-profile}.properties")
public class DosirakApplication {

    private static final Logger log = LoggerFactory.getLogger("DosirakApplication");

    @Value("${spring.config.activate.on-profile}")
    String profile;

    @PostConstruct
    public void printConfigFileLocation() {
        //System.getenv().values().stream().forEach(log::info);
        log.info("profile=[{}]",profile);
    }

    public static void main(String[] args) {
        //log.info("args=[{}]",Arrays.toString(args));
        SpringApplication.run(DosirakApplication.class, args);
    }
}