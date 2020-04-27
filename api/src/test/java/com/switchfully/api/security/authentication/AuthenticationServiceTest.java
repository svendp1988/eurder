package com.switchfully.api.security.authentication;

import com.switchfully.domain.user.User;
import com.switchfully.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import static com.switchfully.domain.user.builders.AddressBuilder.addressBuilder;
import static com.switchfully.domain.user.builders.UserBuilder.userBuilder;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {AuthenticationService.class, UserRepository.class})
@ComponentScan(basePackages = "com.switchfully")
class AuthenticationServiceTest {

    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    AuthenticationService authenticationService;

    @Test
    void whenProvidedWithCorrectEmail_andPassword_returnsCorrectUser() {
        String email = "admin@order.com";
        String password = "root";
        User user = userBuilder()
                .withFirstName("admin")
                .withLastName("istrator")
                .withEmail(email)
                .withLastName("Admin")
                .withPassword(password)
                .withAddress(addressBuilder()
                        .withStreet("street")
                        .withStreetNumber("1")
                        .withPostalCode("1000")
                        .withCity("city")
                        .build())
                .buildAdmin();
        User actualUser = authenticationService.getUser(email, password);
        assertThat(actualUser).isEqualTo(user);
    }
}