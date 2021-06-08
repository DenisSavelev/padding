package com.diplom.padding;

import org.springframework.boot.SpringApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableScheduling
public class PaddingApp {
    public static void main(String[] args) {
        SpringApplication.run(PaddingApp.class, args);
    }
}