package com.switchfully.jar;

import com.switchfully.service.item.dto.CreateItemDto;
import com.switchfully.service.item.dto.ItemDto;
import com.switchfully.service.user.dto.CreateUserDto;
import com.switchfully.service.user.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static com.google.common.collect.Lists.newArrayList;
import static com.switchfully.service.address.AddressDtoBuilder.addressDtoBuilder;
import static com.switchfully.service.user.dto.UserDtoBuilder.userDtoBuilder;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Nested
    @DisplayName("UserControllerIntegrationTest")
    class UserControllerIntegrationTest {

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
        @DisplayName("RegisterUserIntegrationTest")
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

    @Nested
    @DisplayName("SecurityIntegrationTest")
    class SecurityIntegrationTest {

        @Test
        @DisplayName("View all customers without correct authentication")
        void tryingToViewAllCustomers_withoutCorrectAuthentication_shouldResultInError() {
            WebTestClient
                    .bindToServer()
                    .baseUrl("http://localhost:8080")
                    .defaultHeaders(header -> header.setBasicAuth("incorrect_email", "incorrect_password"))
                    .defaultHeaders(header -> header.setAccept(newArrayList(APPLICATION_JSON)))
                    .defaultHeaders(header -> header.setContentType(APPLICATION_JSON))
                    .build()
                    .get()
                    .uri("/customers")
                    .exchange()
                    .expectStatus().isForbidden()
                    .expectHeader().valueEquals("Content-Type", "application/json")
                    .expectBody().json("You don't have the rights to do that.");
        }

    }

    @Nested
    @DisplayName("ItemControllerIntegrationTest")
    class ItemControllerIntegrationTest {
        CreateItemDto createItemDto = new CreateItemDto("name", "description", 0.0);

        @Test
        @DisplayName("Add items as admin.")
        void adminCanAddItems() {
            WebTestClient
                    .bindToServer()
                    .baseUrl("http://localhost:8080")
                    .defaultHeaders(header -> header.setBasicAuth("admin@order.com", "root"))
                    .defaultHeaders(header -> header.setAccept(newArrayList(APPLICATION_JSON)))
                    .defaultHeaders(header -> header.setContentType(APPLICATION_JSON))
                    .build()
                    .post()
                    .uri("/items")
                    .body(Mono.just(createItemDto), CreateItemDto.class)
                    .exchange()
                    .expectStatus().isCreated()
                    .expectHeader().valueEquals("Content-Type", "application/json")
                    .expectBody(ItemDto.class);
        }

    }
}
