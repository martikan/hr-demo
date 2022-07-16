package com.martikan.carrental.service;

import java.util.Collections;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.martikan.carrental.domain.role.RoleName;
import com.martikan.carrental.domain.user.User;
import com.martikan.carrental.dto.auth.SignUpDTO;
import com.martikan.carrental.dto.user.EmailAvailabilityDTO;
import com.martikan.carrental.dto.user.UserDTO;
import com.martikan.carrental.exception.BadRequestException;
import com.martikan.carrental.mapper.UserMapper;
import com.martikan.carrental.repository.UserRepository;
import com.martikan.carrental.security.UserDetails;

import lombok.RequiredArgsConstructor;

/**
 * Service for {@link com.martikan.restaurantapi.domain.user.User} and authentication's stuffs.
 */
@RequiredArgsConstructor
@Service
@Transactional
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;

    private final RoleService roleService;

    private final BCryptPasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    /**
     * Load user for sign in process.
     * @param username Email address
     * @return {@link UserDetails}
     * @throws UsernameNotFoundException exception if user not found
     */
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        final var user = userRepository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return new UserDetails(user);
    }

    /**
     * Check email availability.
     * @param email String
     * @return {@link EmailAvailabilityDTO}
     */
    public EmailAvailabilityDTO checkEmailAvailability(final String email) {
        return EmailAvailabilityDTO.builder()
                .emailAvailable(!userRepository.existsUserByEmail(email))
                .build();
    }

    /**
     * Sign-up process. Only student can register in her/his own.
     * The teachers have to be added by admin or other teacher.
     * @param signUpRequest {@link SignUpDTO}
     * @return {@link UserDTO}
     */
    public UserDTO signUp(final SignUpDTO signUpRequest) {

        if (userRepository.existsUserByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address is already exist.");
        }

        // Set up default role for user - `ROLE_USER`
        final var role = roleService.findRoleByName(RoleName.ROLE_USER);

        final var newUser = new User();
        newUser.setEmail(signUpRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        newUser.setRoles(Collections.singleton(role));
        newUser.setFirstName(signUpRequest.getFirstName());
        newUser.setLastName(signUpRequest.getLastName());
        newUser.setPhone(signUpRequest.getPhoneNumber());

        return userMapper.toDTO(userRepository.save(newUser));
    }

}