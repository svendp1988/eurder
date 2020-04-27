//package com.switchfully.api.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.Collections;
//
//@Configuration
//@EnableSwagger2
//public class SpringFoxConfig {
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.switchfully.api.endpoints"))
//                .build()
//                .apiInfo(apiDetails());
//    }
//
//    private ApiInfo apiDetails() {
//        return new ApiInfo(
//                "Örder API",
//                "Switchfully Örder project",
//                "1.0",
//                "Free to use",
//                new springfox.documentation.service.Contact("Sven", "http://switchfully.com", "contact@switchfully.com"),
//                "API License",
//                "www.switchfully.com",
//                Collections.emptyList()
//        );
//    }
//}