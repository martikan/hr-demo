package com.martikan.carrental.dto.auth;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * DTO for {@link com.martikan.restaurantapi.domain.user.User} login.
 */
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SignInDTO implements Serializable {

    private static final long serialVersionUID = 3215027194921711180L;

    @EqualsAndHashCode.Include
    @Size(max = 255, message = "Email address cannot be grater then 255 characters.")
    @NotBlank(message = "Email address cannot be empty.")
    @Email(message = "Not valid email format.")
    private String email;

    @ToString.Exclude
    @Size(min = 6, max = 16, message = "Password length must be grater then 5 characters and less then 16 characters.")
    private String password;

}