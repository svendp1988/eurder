package com.switchfully.api.testbuilders;

import com.switchfully.domain.user.User;
import com.switchfully.domain.user.UserRepository;
import org.springframework.boot.test.context.TestComponent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.switchfully.domain.user.builders.UserBuilder.userBuilder;

@TestComponent
public class TestUserRepository extends UserRepository {
    private final Map<String, User> userDatabase;

    public TestUserRepository() {
        this.userDatabase = new ConcurrentHashMap<>();
    }


    public void populateRepository() {
        registerAsCustomer(userBuilder().buildCustomer());
        registerAsAdmin(userBuilder().buildAdmin());
    }

    public Map<String, User> getUserDatabase() {
        return userDatabase;
    }
}
