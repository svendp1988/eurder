package com.switchfully;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.switchfully.domain")
@EntityScan(basePackages = "com.switchfully.domain")
//@PropertySource(name = "application.properties", value = "../../../resources/application.properties")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
