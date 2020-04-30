package com.switchfully.domain.user;


import com.switchfully.domain.user.feature.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    public List<User> findAll();
    List<User> findAllByRole(UserRole role);
}
