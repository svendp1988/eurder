package com.switchfully.jar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.switchfully")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
