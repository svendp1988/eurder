package com.switchfully.domain.user;

import com.switchfully.domain.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.switchfully.domain.testbuilders.TestUserBuilder.testUserBuilder;
import static com.switchfully.domain.user.feature.UserRole.ADMIN;
import static com.switchfully.domain.user.feature.UserRole.CUSTOMER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserRepositoryTest {
    UserRepository userRepository;

    @BeforeEach
    void init() {
        userRepository = new UserRepository();
    }

    @Test
    void iCanRegisterAsaCustomer_andANewUser_withTheSameValues_isReturned() {
        User actualUser = userRepository.registerAsCustomer(testUserBuilder().buildCustomer());
        assertEquals(testUserBuilder().buildCustomer(), actualUser);
    }

    @Test
    void iCanRegisterAsanAdmin_andANewUser_withTheSameValues_isReturned() {
        User actualAdmin = userRepository.registerAsAdmin(testUserBuilder().buildAdmin());
        assertEquals(testUserBuilder().buildAdmin(), actualAdmin);
    }

    @Test
    void oneDefaultAdmin_isAlreadyPresentInTheSystem() {
        assertThatCode(() -> userRepository.getAllUsers().stream().filter(user -> user.getRole().equals(ADMIN)).findFirst())
                .doesNotThrowAnyException();
    }

    @Test
    void viewAllCustomers_returnsACollectionOfAllCustomersPresent_withoutAdmins() {
        assertThat(userRepository.getAllCustomers()).filteredOn(user -> user.getRole().equals(CUSTOMER)).isNotEmpty();
        assertThat(userRepository.getAllCustomers()).filteredOn(user -> user.getRole().equals(ADMIN)).isEmpty();
    }

    @Test
    void viewAllUsers_returnsACollectionOfAllCustomersAndAdminsPresent() {
        assertThat(userRepository.getAllUsers()).filteredOn(user -> user.getRole().equals(CUSTOMER)).isNotEmpty();
        assertThat(userRepository.getAllUsers()).filteredOn(user -> user.getRole().equals(ADMIN)).isNotEmpty();
    }

    @Test
    void searchingForAUser_withWrongIdThrowsException() {
        assertThrows(UserNotFoundException.class, () -> userRepository.getById("non_existing_id"));
    }
}