package com.api.milktea.repository;

import com.api.milktea.models.User;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories

public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findByUsername(String username);


    List<User> findByRoleId(long id);


}
