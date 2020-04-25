package com.switchfully.api.security.authentication;

import com.switchfully.domain.user.User;
import com.switchfully.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService {
    private final List<User> usersList = new ArrayList<>();

    @Autowired
    public AuthenticationService(UserRepository userRepository) {
        usersList.addAll(userRepository.findAll());
    }

    public User getUser(String email, String password) {
        return usersList.stream()
                .filter(user -> user.getEmail().equals(email))
                .filter(user -> verifyHash(password, user.getPassword()))
                .findFirst()
                .orElse(null);
    }

    public boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
