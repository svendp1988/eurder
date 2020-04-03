package com.switchfully.jar;

import com.switchfully.domain.user.User;
import com.switchfully.service.user.dto.CreateUserDto;
import com.switchfully.service.user.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static com.google.common.collect.Lists.newArrayList;
import static com.switchfully.domain.user.builders.AddressBuilder.addressBuilder;
import static com.switchfully.domain.user.builders.UserBuilder.userBuilder;
import static com.switchfully.service.address.AddressDtoBuilder.addressDtoBuilder;
import static com.switchfully.service.user.dto.UserDtoBuilder.userDtoBuilder;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ApplicationTests {
    CreateUserDto createUserDto = userDtoBuilder()
            .withFirstName("firstName")
            .withLastName("lastName")
            .withEmail("email")
            .withPassword("password")
            .withAddress(addressDtoBuilder()
                    .withStreet("street")
                    .withStreetNumber("1A")
                    .withPostalCode("1000")
                    .withCity("Brussel")
                    .build())
            .buildCreateUserDto();

    @Test
    void contextLoads() {
    }

    @Test
    void registeringAsNewCustomer_returnsUserDto() {
        WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:8080")
                .defaultHeaders(header -> header.setBasicAuth("admin@order.com", "root"))
                .defaultHeaders(header -> header.setAccept(newArrayList(APPLICATION_JSON)))
                .defaultHeaders(header -> header.setContentType(APPLICATION_JSON))
                .build()
                .post()
                .uri("/customers")
                .body(Mono.just(createUserDto), CreateUserDto.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().valueEquals("Content-Type", "application/json")
                .expectBody(UserDto.class);
    }
}
