package com.hansotdosirak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@SpringBootApplication
//@PropertySource("classpath:application-${spring.profiles.active}.properties")
@PropertySource("classpath:application-${spring.config.activate.on-profile}.properties")
public class DosirakApplication {
    @Value("${spring.config.activate.on-profile}")
    String curProfile;

    @PostConstruct
    public void printConfigFileLocation() {
        System.out.println(curProfile);
    }

    public static void main(String[] args) {
        System.out.println("Hello");
        SpringApplication.run(DosirakApplication.class, args);
    }
}