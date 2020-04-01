package com.switchfully.domain.testbuilders;

import com.switchfully.domain.user.User;
import com.switchfully.domain.user.UserRepository;
import org.springframework.boot.test.context.TestComponent;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static com.switchfully.domain.testbuilders.TestUserBuilder.testUserBuilder;
import static com.switchfully.domain.user.feature.UserRole.CUSTOMER;

@TestComponent
public class TestUserRepository extends UserRepository {
    private final Map<String, User> userDatabase;

    public TestUserRepository() {
        this.userDatabase = new ConcurrentHashMap<>();
    }


    public void populateRepository() {
        registerAsCustomer(testUserBuilder().buildCustomer());
        registerAsAdmin(testUserBuilder().buildAdmin());
    }

    public Map<String, User> getUserDatabase() {
        return userDatabase;
    }
}
