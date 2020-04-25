package com.switchfully.domain.user;

import com.switchfully.domain.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static com.switchfully.domain.testbuilders.TestAddressBuilder.testAddressBuilder;
import static com.switchfully.domain.testbuilders.TestUserBuilder.testUserBuilder;
import static com.switchfully.domain.user.feature.UserRole.ADMIN;
import static com.switchfully.domain.user.feature.UserRole.CUSTOMER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ComponentScan(basePackages = "com.switchfully.domain.user")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;

   @Test
   void contextLoads_repoIsThere() {
       assertThat(userRepository).isNotNull();
   }

    @Test
    void createUser() {
        User user = testUserBuilder().buildCustomer();
        userRepository.save(user);
        User actual = userRepository.findById(1L).get();
        assertThat(actual).isEqualTo(user);
    }

    @Test
    @Sql("multipleUsers.sql")
    void findAll() {
       Address adminAddress = addressRepository.findById(1L).get();
       Address customerAddress = addressRepository.findById(2L).get();
       User admin = testUserBuilder().withAddress(adminAddress).buildAdmin();
       User customer = testUserBuilder().withAddress(customerAddress).buildCustomer();
       List<User> users = List.of(customer, admin);
       userRepository.saveAll(users);
       List<User> actual = userRepository.findAll();
       assertThat(actual).hasSameElementsAs(users);
    }

    //    @Test
//    void iCanRegisterAsaCustomer_andANewUser_withTheSameValues_isReturned() {
//        User actualUser = userRepository.registerAsCustomer(testUserBuilder().buildCustomer());
//        assertEquals(testUserBuilder().buildCustomer(), actualUser);
//    }
//
//    @Test
//    void iCanRegisterAsanAdmin_andANewUser_withTheSameValues_isReturned() {
//        User actualAdmin = userRepository.registerAsAdmin(testUserBuilder().buildAdmin());
//        assertEquals(testUserBuilder().buildAdmin(), actualAdmin);
//    }
//
//    @Test
//    void oneDefaultAdmin_isAlreadyPresentInTheSystem() {
//        assertThatCode(() -> userRepository.getAllUsers().stream().filter(user -> user.getRole().equals(ADMIN)).findFirst())
//                .doesNotThrowAnyException();
//    }
//
//    @Test
//    void viewAllCustomers_returnsACollectionOfAllCustomersPresent_withoutAdmins() {
//        assertThat(userRepository.getAllCustomers()).filteredOn(user -> user.getRole().equals(CUSTOMER)).isNotEmpty();
//        assertThat(userRepository.getAllCustomers()).filteredOn(user -> user.getRole().equals(ADMIN)).isEmpty();
//    }
//
//    @Test
//    void viewAllUsers_returnsACollectionOfAllCustomersAndAdminsPresent() {
//        assertThat(userRepository.getAllUsers()).filteredOn(user -> user.getRole().equals(CUSTOMER)).isNotEmpty();
//        assertThat(userRepository.getAllUsers()).filteredOn(user -> user.getRole().equals(ADMIN)).isNotEmpty();
//    }
//
//    @Test
//    void searchingForAUser_withWrongIdThrowsException() {
//        assertThrows(UserNotFoundException.class, () -> userRepository.getById("non_existing_id"));
//    }
}