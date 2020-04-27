package com.switchfully.domain.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static com.switchfully.domain.testbuilders.TestUserBuilder.testUserBuilder;
import static com.switchfully.domain.user.feature.UserRole.CUSTOMER;
import static org.assertj.core.api.Assertions.assertThat;
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
    @Sql("userDependencies.sql")
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

    @Test
    @Sql("userDependencies.sql")
    void findByUserRole_returnsOnlyUsersWithCorrectRole() {
       Address address = addressRepository.findById(1L).get();
        User admin = testUserBuilder().withAddress(address).buildAdmin();
        userRepository.saveAll(List.of(admin, testUserBuilder().withAddress(address).buildCustomer(), testUserBuilder().withAddress(address).buildCustomer()));
       assertThat(userRepository.findAllByRole(CUSTOMER)).hasSize(2).doesNotContain(admin);
    }
}