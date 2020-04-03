package com.switchfully.api.security.authentication;

import com.switchfully.domain.user.User;
import com.switchfully.domain.user.UserRepository;
import org.junit.jupiter.api.Test;

import static com.switchfully.domain.user.builders.AddressBuilder.addressBuilder;
import static com.switchfully.domain.user.builders.UserBuilder.userBuilder;
import static org.assertj.core.api.Assertions.assertThat;

class AuthenticationServiceTest {
    UserRepository userRepository = new UserRepository();
    AuthenticationService authenticationService = new AuthenticationService(userRepository);
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