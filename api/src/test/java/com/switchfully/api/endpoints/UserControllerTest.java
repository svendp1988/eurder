package com.switchfully.api.endpoints;

import com.switchfully.domain.user.UserRepository;
import com.switchfully.domain.user.builders.UserBuilder;
import com.switchfully.service.user.CreateUserDto;
import com.switchfully.service.user.UserDto;
import com.switchfully.service.user.UserMapper;
import com.switchfully.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.switchfully.api.testbuilders.TestUserBuilder.testUserBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@TestComponent
class UserControllerTest {
    
//    @Autowired
//    private ApplicationContext context;
//
//    WebTestClient testClient = WebTestClient.bindToApplicationContext(context)
//            .build();
//
//    @Test
//    void name() {
//        WebTestClient
//                .bindToServer()
//                .baseUrl("http://localhost:8080")
//                .build()
//                .post()
//                .uri("/customers")
//                .bodyValue(CreateUserDto.class)
//                .exchange()
//                .expectStatus().isCreated()
//                .expectHeader().valueEquals("Content-Type", "application/json")
//                .expectBody().equals(UserDto.class);
//    }

//    UserMapper userMapper = new UserMapper();
//    CreateUserDto newCustomer = new CreateUserDto(testUserBuilder());
//    UserDto userDto = new UserDto(testUserBuilder().buildCustomer());
//
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