package com.switchfully.domain.user;

import com.switchfully.domain.exceptions.UserNotFoundException;
import com.switchfully.domain.testbuilders.TestUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestComponent;

import static com.switchfully.domain.testbuilders.TestUserBuilder.testUserBuilder;
import static com.switchfully.domain.user.feature.UserRole.ADMIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestComponent
class UserRepositoryTest {
    TestUserRepository testUserRepository;

    @BeforeEach
    void init() {
        testUserRepository = new TestUserRepository();
    }

    @Test
    void iCanRegisterAsaCustomer_andANewUser_withTheSameValues_isReturned() {
        User actualUser = testUserRepository.registerAsCustomer(testUserBuilder().buildCustomer());
        assertEquals(testUserBuilder().buildCustomer(), actualUser);
    }

    @Test
    void iCanRegisterAsanAdmin_andANewUser_withTheSameValues_isReturned() {
        User actualAdmin = testUserRepository.registerAsAdmin(testUserBuilder().buildAdmin());
        assertEquals(testUserBuilder().buildAdmin(), actualAdmin);
    }

    @Test
    void oneDefaultAdmin_isAlreadyPresentInTheSystem() {
        assertThatCode(() -> testUserRepository.getAllUsers().stream().filter(user -> user.getRole().equals(ADMIN)).findFirst())
                .doesNotThrowAnyException();
    }

    @Test
    void viewAllCustomers_returnsACollectionOfAllCustomersPresent_withoutAdmins() {
        testUserRepository.populateRepository();
        assertThat(testUserRepository.getAllCustomers()).containsExactly(testUserBuilder().buildCustomer());
    }

    @Test
    void viewAllUsers_returnsACollectionOfAllCustomersAndAdminsPresent() {
        testUserRepository.populateRepository();
        assertThat(testUserRepository.getAllUsers()).contains(testUserBuilder().buildCustomer(), testUserBuilder().buildAdmin());
    }

    @Test
    void searchingForAUser_withWrongIdThrowsException() {
        assertThrows(UserNotFoundException.class, () -> testUserRepository.getById("non_existing_id"));

    }
}