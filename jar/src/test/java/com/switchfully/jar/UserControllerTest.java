package com.switchfully.jar;

import com.google.common.collect.Lists;
import com.switchfully.domain.user.builders.UserBuilder;
import com.switchfully.service.user.dto.CreateUserDto;
import com.switchfully.service.user.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.google.common.collect.Lists.newArrayList;
import static com.switchfully.jar.testbuilders.TestUserBuilder.testUserBuilder;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class UserControllerTest {

    @Test
    void name() {
        WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:8080")
                .defaultHeaders(header -> header.setBasicAuth("admin@order.com", "root"))
                .defaultHeaders(header -> header.setAccept(newArrayList(APPLICATION_JSON)))
                .defaultHeaders(header -> header.setContentType(APPLICATION_JSON))
                .build()
                .post()
                .uri("/customers")
                .bodyValue("{\"password\":\"somePassword\" }")
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().valueEquals("Content-Type", "application/json")
                .expectBody().jsonPath("$.id").isNotEmpty();
    }


//    CreateUserDto newCustomer = new CreateUserDto(testUserBuilder());
//    UserDto userDto = new UserDto(testUserBuilder().buildCustomer());

//    @Mock
//    UserService userService;
//
//    @InjectMocks
//    UserController userController;
//
//    @Test
//    void registeringANewCustomer_returnsDtoHoldingTheSameData() {
//        when(userController.register(newCustomer)).thenReturn(userDto);
//        assertThat(userController.register(newCustomer)).isEqualTo(userDto);
//    }
}