package com.switchfully.domain.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryD extends CrudRepository<User, Long> {

}
