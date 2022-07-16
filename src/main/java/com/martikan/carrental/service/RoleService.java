package com.martikan.carrental.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.martikan.carrental.domain.role.Role;
import com.martikan.carrental.domain.role.RoleName;
import com.martikan.carrental.exception.ResourceNotFoundException;
import com.martikan.carrental.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service for {@link com.martikan.restaurantapi.domain.role.Role} and authentication's stuffs.
 */
@RequiredArgsConstructor
@Service
@Transactional
public class RoleService {

	private final RoleRepository roleRepository;

    /**
     * Find role by name.
     * @param name {@link RoleName}
     * @return {@link Role}
     */
    public Role findRoleByName(final RoleName name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(Role.class.getSimpleName(), "name", name));
    }

}