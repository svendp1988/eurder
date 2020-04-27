package com.switchfully.domain.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;

import static com.switchfully.domain.testbuilders.TestUserBuilder.testUserBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@DataJpaTest
@AutoConfigureTestDatabase
@ComponentScan(basePackages = "com.switchfully")
class UserRepositoryDTest {

    @Autowired
    private UserRepositoryD userRepositoryD;

    @Test
    void weExist() { assertThat(userRepositoryD).isNotNull(); }

    @Test
    void createUser() {
        User user = testUserBuilder().buildCustomer();

        userRepositoryD.save(user);

        User actual = userRepositoryD.findById(1L).get();

        assertThat(actual).isEqualTo(user);
    }


}