package com.switchfully;
//
//import com.switchfully.domain.item.Item;
//import com.switchfully.domain.item.ItemRepository;
//import com.switchfully.service.item.dto.CreateItemDto;
//import com.switchfully.service.item.dto.ItemDto;
//import com.switchfully.service.order.OrderDto;
//import com.switchfully.service.order.OrderRequestDto;
//import com.switchfully.service.user.dto.CreateUserDto;
//import com.switchfully.service.user.dto.UserDto;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.switchfully.service.item.dto.CreateItemDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import org.springframework.test.web.reactive.server.EntityExchangeResult;
//import org.springframework.test.web.reactive.server.WebTestClient;
//import reactor.core.publisher.Mono;
//
//import static com.google.common.collect.Lists.newArrayList;
//import static com.switchfully.domain.item.builders.ItemBuilder.itemBuilder;
//import static com.switchfully.service.address.AddressDtoBuilder.addressDtoBuilder;
//import static com.switchfully.service.user.dto.UserDtoBuilder.userDtoBuilder;
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class ApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    void contextLoads() {
    }
//
//    @Nested
//    @DisplayName("UserControllerIntegrationTest")
//    class UserControllerIntegrationTest {
//
//        CreateUserDto createUserDto = userDtoBuilder()
//                .withFirstName("firstName")
//                .withLastName("lastName")
//                .withEmail("email")
//                .withPassword("password")
//                .withAddress(addressDtoBuilder()
//                        .withStreet("street")
//                        .withStreetNumber("1A")
//                        .withPostalCode("1000")
//                        .withCity("Brussel")
//                        .build())
//                .buildCreateUserDto();
//
//        @Test
//        @DisplayName("RegisterUserIntegrationTest")
//        void registeringAsNewCustomer_returnsUserDto() {
//            WebTestClient
//                    .bindToServer()
//                    .baseUrl("http://localhost:8080")
//                    .defaultHeaders(header -> header.setBasicAuth("admin@order.com", "root"))
//                    .defaultHeaders(header -> header.setAccept(newArrayList(APPLICATION_JSON)))
//                    .defaultHeaders(header -> header.setContentType(APPLICATION_JSON))
//                    .build()
//                    .post()
//                    .uri("/customers")
//                    .body(Mono.just(createUserDto), CreateUserDto.class)
//                    .exchange()
//                    .expectStatus().isCreated()
//                    .expectHeader().valueEquals("Content-Type", "application/json")
//                    .expectBody(UserDto.class);
//        }
//
//    }

    @Nested
    @DisplayName("SecurityIntegrationTest")
    class SecurityIntegrationTest {

        @Test
        @DisplayName("View all customers without correct authentication")
        void tryingToViewAllCustomers_withoutCorrectAuthentication_shouldResultInError() throws Exception {
            String expected = "You don't have the rights to do that.";
            mockMvc.
                    perform(get("/customers")
                            .with(user("sven@order.com").password("customer"))
                            .contentType(APPLICATION_JSON))
                    .andExpect(status().isForbidden())
                    .andExpect(status().reason(expected));
        }

        @Test
        @DisplayName("View a single customers with correct authentication")
        @WithMockUser(authorities = "VIEW_CUSTOMERS")
        void tryingToViewASingleCustomers_withoutCorrectAuthentication_shouldResultInError() throws Exception {
            String expected = "[{\"id\":1,\"firstName\":\"Customer\",\"lastName\":\"New\",\"email\":\"customer@order.com\"}," +
                    "{\"id\":1,\"firstName\":\"Other\",\"lastName\":\"Customer\",\"email\":\"test@mail.com\"}]";
            String actual =
                    mockMvc
                            .perform(get("/customers")
                                    .contentType(APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andReturn()
                            .getResponse()
                            .getContentAsString();
//            System.out.println(actual);
            assertEquals(expected, actual, new CustomComparator(JSONCompareMode.LENIENT,
                    new Customization("$.id", (o1, o2) -> true)));
        }

    }

    @Nested
    @DisplayName("ItemControllerIntegrationTest")
    class ItemControllerIntegrationTest {
        CreateItemDto createItemDto = new CreateItemDto("name", "description", 0.0, 2);

        @Test
        @DisplayName("Add items as admin.")
        @WithMockUser(authorities = "ADD_ITEM")
        void adminCanAddItems() throws Exception {
            String actual = mockMvc
                    .perform(post("/items")
                            .contentType(APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(createItemDto)))
                    .andExpect(status().isCreated())
                    .andReturn()
                    .getResponse()
                    .getContentAsString();
            String expected = "{\"id\":1,\"name\":\"name\",\"description\":\"description\",\"price\":0.0}";
            assertEquals(expected, actual, new CustomComparator(JSONCompareMode.LENIENT,
                    new Customization("id", (o1, o2) -> true)));
        }
    }
////    @Nested
////    @DisplayName("OrderControllerIntegrationTest")
////    class OrderControllerIntegrationTest {
////        ItemRepository itemRepository = new ItemRepository();
////        Item item = itemBuilder()
////                .withName("name")
////                .withDescription("description")
////                .withPrice(2.5)
////                .build();
////        String id = item.getId();
////
////        @Test
////        @DisplayName("Add order as customer.")
////        void customerCanOrderItems() {
////            itemRepository.addItem(item);
////            OrderRequestDto request = new OrderRequestDto(id, 1);
////            WebTestClient
////                    .bindToServer()
////                    .baseUrl("http://localhost:8080")
////                    .defaultHeaders(header -> header.setBasicAuth("sven@order.com", "awesome"))
////                    .defaultHeaders(header -> header.setAccept(newArrayList(APPLICATION_JSON)))
////                    .defaultHeaders(header -> header.setContentType(APPLICATION_JSON))
////                    .build()
////                    .post()
////                    .uri("/orders")
////                    .body(Mono.just(request), OrderRequestDto.class)
////                    .exchange()
////                    .expectStatus().isCreated()
////                    .expectHeader().valueEquals("Content-Type", "application/json")
////                    .expectBody(OrderDto.class);
////        }
////
////    }
}
