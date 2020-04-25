package com.switchfully.domain.user;

//import com.switchfully.domain.exceptions.EmailAlreadyRegisteredException; //TODO: check exceptions

import com.switchfully.domain.exceptions.UserNotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static com.switchfully.domain.user.builders.AddressBuilder.addressBuilder;
import static com.switchfully.domain.user.builders.UserBuilder.userBuilder;
import static com.switchfully.domain.user.feature.UserRole.CUSTOMER;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    public List<User> findAll();

//    public boolean isEmailAvailable(String email) {
//        if (userRepository.values().stream()
//                .anyMatch(member -> member.getEmail().equals(email))) {
//            throw new EmailAlreadyRegisteredException(email);
//        }
//        return true;
//    }
}
