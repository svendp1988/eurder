package com.switchfully.api.endpoints.authentication;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.switchfully.domain.user.User;
import com.switchfully.domain.user.UserRepository;
import com.switchfully.domain.user.feature.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final List<com.switchfully.domain.user.User> usersList = new ArrayList<>();

    @Autowired
    public JwtUserDetailsService(UserRepository userRepository) {
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
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User foundUser = usersList.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        Collection<SimpleGrantedAuthority> grantedAuthorities = List.of(new SimpleGrantedAuthority(foundUser.getRole().toString()));
        return new org.springframework.security.core.userdetails.User(foundUser.getEmail(), foundUser.getPassword(), grantedAuthorities);
    }
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if ("javainuse".equals(username)) {
//            return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
//                    new ArrayList<>());
//        } else {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//    }
}

