package com.martikan.carrental.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.martikan.carrental.domain.role.Role;
import com.martikan.carrental.domain.role.RoleName;

/**
 * Repository for {@link Role} entity.
 */
@Repository
public interface RoleRepository extends BaseRepository<Role> {

    Optional<Role> findByName(RoleName roleName);

}