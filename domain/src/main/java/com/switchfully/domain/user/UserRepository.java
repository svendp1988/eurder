package com.switchfully.domain.user;

//import com.switchfully.domain.exceptions.EmailAlreadyRegisteredException; //TODO: check exceptions

import com.switchfully.domain.exceptions.UserNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static com.switchfully.domain.user.builders.UserBuilder.userBuilder;
import static com.switchfully.domain.user.feature.UserRole.CUSTOMER;

@Repository
public class UserRepository {
    private final Map<String, User> userDatabase;

    public UserRepository() {
        this.userDatabase = new ConcurrentHashMap<>();
        createDefaultData();
    }

//    public boolean isEmailAvailable(String email) {
//        if (userRepository.values().stream()
//                .anyMatch(member -> member.getEmail().equals(email))) {
//            throw new EmailAlreadyRegisteredException(email);
//        }
//        return true;
//    }

    public User registerAsCustomer(User newCustomer) {
        userDatabase.put(newCustomer.getId(), newCustomer);
        return newCustomer;
    }

    public User registerAsAdmin(User newAdmin) {
        userDatabase.put(newAdmin.getId(), newAdmin);
        return newAdmin;
    }


    public Collection<User> getAllCustomers() {
        return userDatabase.values().stream()
                .filter(user -> user.getRole().equals(CUSTOMER))
                .collect(Collectors.toList());
    }

    public Collection<User> getAllUsers() {
        return userDatabase.values();
    }

    public User getById(String id) {
        var foundUser = userDatabase.get(id);
        if (foundUser == null) {
            throw new UserNotFoundException();
        }
        return foundUser;
    }

    private void createDefaultData() {
        User defaultAdmin = userBuilder()
                .withEmail("admin@order.com")
                .withLastName("Admin")
                .withPassword("root")
                .buildAdmin();
        registerAsAdmin(defaultAdmin);
    }
}
