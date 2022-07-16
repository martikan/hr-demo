package com.martikan.carrental.dto.user;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link com.martikan.restaurantapi.domain.user.User} entity.
 */
@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1537744993974704025L;

    @EqualsAndHashCode.Include
    private Long id;

    @Size(max = 255, message = "Email address cannot be grater then 255 characters.")
    @NotBlank(message = "Email address cannot be empty.")
    @Email(message = "Not valid email format.")
    private String email;

    @Size(max = 100, message = "First name cannot be grater then 100 characters.")
    @NotBlank(message = "First name cannot be empty.")
    private String firstName;

    @Size(max = 100, message = "Last name cannot be grater then 100 characters.")
    @NotBlank(message = "Last name cannot be empty.")
    private String lastName;

    @Size(max = 20, message = "Phone number cannot be grater then 20 characters.")
    private String phone;

}