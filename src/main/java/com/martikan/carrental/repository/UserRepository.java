package com.martikan.carrental.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.martikan.carrental.domain.user.User;

/**
 * Repository for {@link User} entity.
 */
@Repository
public interface UserRepository extends BaseRepository<User> {

    @Query("from User u " +
            "left join fetch u.roles r " +
            "where u.email = :email")
    Optional<User> findUserByEmail(String email);

    boolean existsUserByEmail(String email);
}