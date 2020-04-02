package com.switchfully.api.endpoints;

import org.springframework.boot.test.context.TestComponent;

import static org.assertj.core.api.Assertions.assertThat;

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